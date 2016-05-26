
/*
 * Files
 * 
 * Clase que contiene todas las caracteristicas necesarias para analizar el
 * contenido de un archivo
 * 
 * @author Jose Gonzalez	A01036121
 * @date 2/2/2016
 * @version 1.0 
 * */

//&p-Files
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	//&i
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
	 */
	//&i
	void analyze() {

		String sLineaActual;

		try {
			while ((sLineaActual = brBuffer.readLine()) != null) {
				if (sLineaActual.trim().isEmpty()) {
					iEmptyLines ++;
					iTotalEmpty ++;
				} else {
					iInfoLines ++;
					iTotalInfo ++;
				}
			}
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
	//&i
	public int compareTo(Files flsOtherFile) {
		int iQuantity = ((Files) flsOtherFile).iInfoLines;

		return this.iInfoLines - iQuantity;
	}

}
