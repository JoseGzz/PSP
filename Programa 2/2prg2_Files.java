/*
 * Files
 * 
 * Clase que contiene todas las caracteristicas necesarias para analizar el
 * contenido de un archivo
 * 
 * @author Jose Gonzalez	A01036121
 * @date 2/2/2016
 * @version 2.0 
 * */

//&p-Files
//&b=48
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Files implements Comparable<Files> {

	// suma de lineas vacias
	public static int iTotalEmpty = 0; 
	// suma de lineas con informacion
	public static int iTotalInfo = 0; 

	// variable para leer lineas
	private BufferedReader brBuffer; 
	// cantidad de lineas vacias
	private int iEmptyLines = 0; 
	// cantidad de lineas con informacion
	private int iInfoLines = 0; 
	// nombre del archivo
	private String sNombre; 
	// booleanas para el algoritmo de indentificación de etiquetas
	boolean bCuenta = false;
	boolean bFoundItem = false;
	boolean bInsideGetSet = false;
	boolean bPartFound = false;
	boolean bInsideComment = false;
	
	// contador para posicion en arreglo de partes
	int iCounter = 0;  
	int iLineasMetodo = 0;
	static int iOutSideLines = 0;
	StringBuilder strName;
	String sLine;
	// arreglo de partes
	ArrayList<Part> arrParts; 
	
	/*
	 * Files
	 * 
	 * Constructor de la clase Files
	 */
	public Files() {
		
	}

	/*
	 * getTotalEmpty
	 * 
	 * regresa el valor de iTotalEmpty
	 */
	public static int getTotalEmpty() {
		return iTotalEmpty;
	}

	public static void setTotalEmpty(int iTotalEmpty) {
		Files.iTotalEmpty = iTotalEmpty;
	}

	/*
	 * getTotalInfo
	 * 
	 * regresa el valor de iTotalInfo
	 */
	public static int getTotalInfo() {
		return iTotalInfo;
	}

	public static void setTotalInfo(int iTotalInfo) {
		Files.iTotalInfo = iTotalInfo;
	}

	/*
	 * getBuffer
	 * 
	 * regresa el valor de brBuffer
	 */
	public BufferedReader getBuffer() {
		return brBuffer;
	}

	public void setBuffer(BufferedReader brBuffer) {
		this.brBuffer = brBuffer;
	}

	/*
	 * getEmptyLines
	 * 
	 * regresa el valor de iEmptyLines
	 */
	public int getEmptyLines() {
		return iEmptyLines;
	}

	public void setEmptyLines(int iEmptyLines) {
		this.iEmptyLines = iEmptyLines;
	}

	/*
	 * getInfoLines
	 * 
	 * regresa el valor de iInfoLines
	 */
	public int getInfoLines() {
		return iInfoLines;
	}

	public void setInfoLines(int iInfoLines) {
		this.iInfoLines = iInfoLines;
	}

	/*
	 * getNombre
	 * 
	 * regresa el valor de sNombre
	 */
	public String getNombre() {
		return sNombre;
	}

	public void setNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	/*
	 * searchFile
	 * 
	 * busca que el archivo cuyo nombre ha sido especificado exista en el
	 * sistema regresa verdadero si se ha encontrado o bien falso en el caso
	 * contrario.
	 * 
	 * @param sNombre es el valor <code>String</code> con el nombre del archivo
	 * que se desea leer
	 */
	// &i
	boolean searchFile(String sNombre) {

		this.sNombre = sNombre;

		// se busca leer el archivo con el nombre especificado, si no se
		// encuentra regresa falso
		try {
			brBuffer = new BufferedReader(new FileReader(sNombre));

		} catch (Exception eE) {
			return false;
		}

		return true;
	}

	/*
	 * analyze
	 * 
	 * metodo que cuenta la cantidad de lineas vacias y con informcion de un
	 * archivo
	 * @param iN es el valor que contiene la cantidad de archivos que se han leído
	 */
	// &i
	void analyze(int iN) {
		 // arreglo de partes
		//&d=6
		arrParts = new ArrayList<Part>();
		Part pPart = new Part();
		
		try {
			
			while ((sLine = brBuffer.readLine()) != null) {
				// elimina todos los espacios
				sLine = sLine.replaceAll("\\s+", ""); 
				// si no hemos entrado a la clase y hay comentarios
				if (!bPartFound && sLine.contains("/*")) {
					bInsideComment = true;
				}
				if (!bPartFound && sLine.contains("*/")) {
					bInsideComment = false;
				}
				
				if(!bInsideComment && !bPartFound && !sLine.isEmpty() && sLine.charAt(0) != '/' && sLine.charAt(sLine.length() - 1) != '/') {
					iOutSideLines ++;
				}
				
				 // si está la etiqueta &p- en su lugar
				if (sLine.contains("&p-") && sLine.charAt(2) == '&'
						&& sLine.charAt(3) == 'p' && sLine.charAt(4) == '-') {
					
					if (iCounter > 0) {
						if (pPart.iB > 0 && (pPart.iM > 0 || pPart.iD > 0 || pPart.calcA(pPart.iT, pPart.iB, pPart.iD) > 0)) {
							pPart.strCategory = "base";
						} else if (pPart.iB == 0 && pPart.iM == 0 && pPart.iD == 0 && pPart.calcA(pPart.iT, pPart.iB, pPart.iD) > 0) {
							pPart.strCategory = "nueva";
						} else if (pPart.iB > 0 && pPart.iM == 0 && pPart.iD == 0 && pPart.calcA(pPart.iT, pPart.iB, pPart.iD) > 0) {
							pPart.strCategory = "reusada";
						} else {
							System.out.println("Hay errores en la contabilización del archivo: " + pPart.strName + 
									", por favor, corrígalo y ejecute de nuevo la herramienta.");	
						}
						arrParts.add(pPart);
					}
					Part partaux = new Part();
					pPart = partaux;
					iCounter++;
					pPart.clear();
					pPart.strName = new StringBuilder(sLine); 
					
					// extraer el nombre
					for (int i = 0; i < 5; i++) { 
						pPart.strName.deleteCharAt(0);
					}
					bPartFound = true;
					bCuenta = true;	
				}
				
				// si sé que comienza el comentario múltiple en la linea
				if (bCuenta && sLine.contains("/*")) { 
					// y está primero
					if (sLine.charAt(0) == '/' && sLine.charAt(1) == '*') { 
						// pero no tiene cierre
						if (!sLine.contains("*/")) { 
							// entonces no cuentes sino hasta que encuentres el cierre
							bCuenta = false; 
						}
						// si no es lo primero y lo primero no es abre o cierra entonces
					} else if (sLine.charAt(0) != '{' && sLine.charAt(0) != '}'
						&& sLine.charAt(0) != '{') { 
						pPart.iT ++;

						// si no tiene cierre
						if (!sLine.contains("*/")) { 
							// entonces no cuentes por ahora
							bCuenta = false; 
						} else {
							pPart.iT ++;
						}
					}
					// si encuentro el fin de un comentario múltiple
				} else if (!bCuenta && sLine.contains("*/") && bPartFound) { 
					bCuenta = true;
					// si */no es lo ultimo y no es { ni }
					if ((sLine.charAt(sLine.length() - 2) != '*' && sLine.charAt(sLine.length() - 1) != '/')
							&& (sLine.charAt(sLine.length() - 1) != '}' && sLine.charAt(sLine.length() - 1) != '{')) { 
							
						pPart.iT ++;
						
						// si no hay un fin de comentario múltiple al final entonces significa que es codigo
					} else if (sLine.charAt(sLine.length()-1) != '/' && sLine.charAt(sLine.length()-2) != '*') {
						pPart.iT ++;
						
					}
					// si econtramos comentario sencillo puede ser un comentario, una etiqueta o una instruccion con un comentario
				} else if(bCuenta && sLine.contains("//")) { 
					// si no es el primer elemento y no es ni { ni }
					if(sLine.charAt(0) != '/' && sLine.charAt(1) != '/' &&  sLine.charAt(0) != '}' && sLine.charAt(0) != '{' ) { 
						// entonces es codigo con comentario 
						pPart.iT ++; 
					}
					// si está la etiqueta de modificadas
					if (sLine.contains("&m") ) { 
						// buscar el comentario sencillo
						int index = sLine.indexOf("//"); 
						// y si luego luego está &m y despues ya no hay nada más entonces es modificada
						if(sLine.charAt(index+2) == '&' && sLine.charAt(index+3) == 'm' && sLine.length() == index+4) {
							
							pPart.iM++;  
						}
						 // is hay un &i y el tamaño es de 4 //&i
					} else if (sLine.contains("&i") && sLine.length() == 4 && sLine.charAt(2) == '&' &&  sLine.charAt(3) == 'i') { 
							
							// entonces es la etiqueta de un item
							pPart.iI++; 
							// prendemos la bandera de item para usarlo en la identifiacion de get y set y poder saltarlos
							bFoundItem = true; 
							// si hay un &b
					} else if  (sLine.contains("&b") && sLine.charAt(2) == '&' &&  sLine.charAt(3) == 'b' ) { 
						// sacamos el numero de ahi
						Pattern p = Pattern.compile("-?\\d+"); 
						Matcher m = p.matcher(sLine);
						while (m.find()) {
							// obtenemos el numero
							 // y sumamos su longitud con 5: //&b=, y si ese es el tamaño total
							if(sLine.length() == m.group().length() + 5) { 
								// es una etiqueta de base
			  					pPart.iB += Integer.parseInt(m.group()); 
							}
						}
						 // igual que el caso anterior
					} else if (sLine.contains("&d") && sLine.charAt(2) == '&' &&  sLine.charAt(3) == 'd') {
						Pattern p = Pattern.compile("-?\\d+");
						Matcher m = p.matcher(sLine);
						
						while (m.find()) {
							// obtenemos el numero
							// y sumamos su longitud con 5: //&b=, y si ese es el tamaño total			
							if(sLine.length() == m.group().length() + 5) {  
								// es una etiqueta de base
			  					pPart.iD += Integer.parseInt(m.group()); 
							}
						}
					}
					// si la linea actual es mayor que uno, o sea no es ni } ni {
				} else if (bCuenta && sLine.length() > 1 && sLine.charAt(0) != '@') { 
					pPart.iT ++; 
						iLineasMetodo = pPart.iT;
					}
			}

			if (pPart.iB > 0 && (pPart.iM > 0 || pPart.iD > 0 || pPart.calcA(pPart.iT, pPart.iB, pPart.iD) > 0)) {
				pPart.strCategory = "base";
			} else if (pPart.iB == 0 && pPart.iM == 0 && pPart.iD == 0 && pPart.calcA(pPart.iT, pPart.iB, pPart.iD) > 0) {
				pPart.strCategory = "nueva";
			} else if (pPart.iB > 0 && pPart.iM == 0 && pPart.iD == 0 && pPart.calcA(pPart.iT, pPart.iB, pPart.iD) == 0) {
				pPart.strCategory = "reusada";
			} else {
				System.out.println("Hay errores en la contabilización del archivo: " + pPart.strName + 
						", por favor, corríjalo y ejecute de nuevo la herramienta.");	
			}
			
			arrParts.add(pPart);
		} catch (IOException eE) {
			eE.printStackTrace();
		}
	}


	@Override
	/*
	 * compareTo
	 * 
	 * metodo sobreescrito de la interface Comparable que compara un atributo
	 * entre dos objetos para ser usado posteriormente para ordenar objetos,
	 * regresando la diferencia entre las cantidades
	 * 
	 * @param flsOtherFile es el valor <code>Files</code> con el primer archivo
	 * a comparar
	 */
	// &i
	public int compareTo(Files flsOtherFile) {
		int iQuantity = ((Files) flsOtherFile).iInfoLines;

		return this.iInfoLines - iQuantity;
	}

}
