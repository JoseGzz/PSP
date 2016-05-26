/*
 * Orgnizador
 * 
 * Clasifica los datos leídos de un archivo 
 * 
 * @author Jose Gonzalez	A01036121
 * @date 1/3/2016
 * @version 1.0 
 * */
//&p-Organizador
import java.util.ArrayList;
import java.util.List;

//&b=46
public class Organizador {
	// variables que se despliegan o calcula de manera trivial
	int iXk;
	int iN;
	// listas para clasificar los elementos de los pares
	List<Double> iX;
	List<Double> iY;

	/*
	 * Organizador
	 * 
	 * Constructor de la clase Organizador
	 * */
	public Organizador() {
		// inicializacioón de variables
		iX = new ArrayList<Double>();
		iY = new ArrayList<Double>();
		iXk = 0;
		iN = 0;
	}

	/*
	 * organizar
	 * 
	 * metodo sobreescrito de la interface Comparable que compara un atributo
	 * entre dos objetos para ser usado posteriormente para ordenar objetos,
	 * regresando la diferencia entre las cantidades
	 * 
	 * @param sInfo es el valor <code>ArrayList</code> que contiene los strings
	 * 	leídos del teclado
	 */
	//&i
	public void organizar(ArrayList<String> sInfo) {
		iXk = Integer.parseInt(sInfo.get(0));
		if (iXk < 0) {
			System.out.println("\nEl primer número debe ser mayor o igual a cero.");
			System.out.println("Corrija el archivo y vuelva a ejecutar la herramienta.");
			System.exit(0);
		}
		iN = sInfo.size() - 1;
		// arreglo para recibir los datos leídos línea por línea
		String[] sAux = null;
		// arreglo que recibe cada dato de las parejas de manera individual
		ArrayList<String> arrDatos = new ArrayList<String>();
		
		// se lee de manera intercalada los componentes X y los componentes Y
		for (int iI = 1; iI < sInfo.size(); iI++) {
			
			if (!sInfo.get(iI).contains(",")) {
				System.out.println("\nEl formato es incorrecto.\nEl archivo debe contener parejas de valores,");
				System.out.println("estos valores deben estar separados por una coma (,). \nCorrija el archivo y vuelva a ejecutar la herramienta.");
				System.exit(0);
			}
			// cuando se encuentra la coma es cuando se sabe que hay un segundo dato
			sAux = sInfo.get(iI).split(",");
			if (Double.parseDouble(sAux[0]) < 0 || Double.parseDouble(sAux[1]) < 0) {
				System.out.println("\nUno o más datos son negativos, deben ser mayores o iguales a cero.");
				System.out.println("Corrija el archivo y vuelva a ejecutar la herramienta.");
				System.exit(0);
			}
			arrDatos.add(sAux[0]);
			arrDatos.add(sAux[1]);
		}

		// se llenan las listas de X y de Y
		for (int iI = 0; iI < (sInfo.size() - 1) * 2; iI++) {
			if (iI % 2 == 0) {
				iX.add(Double.parseDouble(arrDatos.get(iI)));
			} else {
				iY.add(Double.parseDouble(arrDatos.get(iI)));
			}
		}
	}
	
	/*
	 * getX
	 * 
	 * metodo que regresa la lista de las X's
	 */
	public List<Double> getX() {
		return iX;
	}

	/*
	 * getY
	 * 
	 * metodo que regresa la lista de las Y's
	 */
	public List<Double> getY() {
		return iY;
	}

	/*
	 * getXk
	 * 
	 * metodo que regresa el valor de Xk
	 */
	public double getXk() {
		return iXk;
	}

	/*
	 * getN
	 * 
	 * metodo que regresa la cantidad de parejas
	 */
	public int getN() {
		return iN;
	}
}
