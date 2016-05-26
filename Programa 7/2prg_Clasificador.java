/*
 * Orgnizador
 * 
 * Clasifica los datos leídos de un archivo 
 * 
 * @author Jose Gonzalez	A01036121
 * @date 24/4/2016
 * @version 1.0 
 * */

//&p-Clasificador
import java.util.ArrayList;
import java.util.List;

public class Clasificador {

	// datos del primer renglon del archivo
	double dWk;
	double dXk;
	double dYk;
	// cantidad de renglones con datos
	int iN;
	// listas para clasificar los elementos del cuadruplo
	List<Double> lstW;
	List<Double> lstX;
	List<Double> lstY;
	List<Double> lstZ;

	/*
	 * Clasificador
	 * 
	 * Constructor de la clase Clasificador
	 */
	public Clasificador() {
		// inicializacioón de variables
		
		lstX = new ArrayList<Double>();
		lstY = new ArrayList<Double>();
		lstW = new ArrayList<Double>();
		lstZ = new ArrayList<Double>();
		
		dWk = 0.0d;
		dXk = 0.0d;
		dYk = 0.0d;
		
		iN = 0;
	}

	/*
	 * clasificar
	 * 
	 * metodo para distribuir adecuadamente los datos en listas
	 * 
	 * @param sInfo es el valor <code>ArrayList</code> que contiene las lineas
	 * leidas del archivo de texto
	 * @return un valor <code>boolean</code> que representa si hubo
	 * algun error con el formato del archivo
	 */
	//&i
	public boolean clasificar(ArrayList<String> sInfo) {
		

		if (sInfo.size() == 0) {
			System.out.println("El archivo está vacío.");
			return false;
		}
		
		// arreglo para recibir los datos leídos línea por línea
		String[] sAux = null;
		// extraer elementos separados por comas
		sAux = sInfo.get(0).split("\\s*,\\s*");
		
		// validar que existan 3 datos en el primer renglon del archivo
		if (sAux.length != 3) {
			System.out.println("El primer renglón debe contener 3 datos.");
			return false;
		}
		
		// se valida que los primero 3 datos sean numericos
		try {
		// si todo sale bien, se asignan los valores
			dWk = Integer.parseInt(sAux[0]);
			dXk = Integer.parseInt(sAux[1]);
			dYk = Integer.parseInt(sAux[2]);
		} catch(Exception exE) {
			System.out.println("Todos los valores deben de ser numericos.");
			System.out.println("Asegurese tambien de que el formato de los numeros sea correcto.");
			return false;
		}
		// validar que los primeros valores sean mayores o iguales a cero
		if (dXk < 0 || dWk < 0 || dYk < 0) {
			System.out
					.println("\nLos primeros 3 datos deben ser mayores o iguales a cero.");
			return false;
		}
		
		// en el tamanio no se cuenta la primera liena
		iN = sInfo.size() - 1;

		// se lee de manera intercalada los componentes W, X, Y, Z
		for (int iI = 1; iI < sInfo.size(); iI++) {
			// se separan los elementos por comas
			sAux = sInfo.get(iI).split("\\s*,\\s*");
			// se valida que esten separados por comas y que haya 4 datos
			if (!sInfo.get(iI).contains(",") || sAux.length != 4) {
				System.out
						.println("\nEl formato es incorrecto.\nEl archivo debe contener cuádruplos a partir del segundo renglón, ");
				System.out
						.println("estos valores deben estar separados por comas.");
				return false;
			}
			
			double dTest0 = 0.0d;
			double dTest1 = 0.0d;
			double dTest2 = 0.0d;
			double dTest3 = 0.0d;

			// se valida que los valores del archivo sean numericos
			try {

				dTest0 = Double.parseDouble(sAux[0]);
				dTest1 = Double.parseDouble(sAux[1]);
				dTest2 = Double.parseDouble(sAux[2]);
				dTest3 = Double.parseDouble(sAux[3]);

			} catch (Exception exE) {
				sInfo.clear();
				lstW.clear();
				lstX.clear();
				lstY.clear();
				lstZ.clear();
				System.out.println("Todos los valores deben de ser numericos.");
				System.out
						.println("Asegurese tambien de que el formato de los numeros sea correcto.");
				return false;
			}

			// se valida que todos los valres sean positivos o cero
			if (dTest0 < 0 || dTest1 < 0 || dTest2 < 0 || dTest3 < 0) {
				sInfo.clear();
				lstW.clear();
				lstX.clear();
				lstY.clear();
				lstZ.clear();
				
				System.out
						.println("\nUno o más datos son negativos, deben ser mayores o iguales a cero.");
				return false;
			}

			// se aniaden los valores a las listas correspondientes
			try {
				lstW.add(Double.parseDouble(sAux[0]));
				lstX.add(Double.parseDouble(sAux[1]));
				lstY.add(Double.parseDouble(sAux[2]));
				lstZ.add(Double.parseDouble(sAux[3]));

			} catch (Exception exE) {
				sInfo.clear();
				lstW.clear();
				lstX.clear();
				lstY.clear();
				lstZ.clear();
				
				System.out.println("Todos los valores deben de ser numericos.");
				System.out
						.println("Asegurese tambien de que el formato de los numeros sea correcto.");
				return false;
			}
		}
		return true;
	}

	/*
	 * getX
	 * 
	 * metodo que regresa la lista de las X's
	 */
	public List<Double> getX() {
		return lstX;
	}

	/*
	 * getY
	 * 
	 * metodo que regresa la lista de las Y's
	 */
	public List<Double> getY() {
		return lstY;
	}
	
	/*
	 * getW
	 * 
	 * metodo que regresa la lista de las W's
	 */
	public List<Double> getW() {
		return lstW;
	}

	/*
	 * getZ
	 * 
	 * metodo que regresa la lista de las Z's
	 */
	public List<Double> getZ() {
		return lstZ;
	}
	
	/*
	 * getXk
	 * 
	 * metodo que regresa el valor de Xk
	 */
	public double getXk() {
		return dXk;
	}

	/*
	 * getYk
	 * 
	 * metodo que regresa el valor de Yk
	 */
	public double getYk() {
		return dYk;
	}

	/*
	 * getWk
	 * 
	 * metodo que regresa el valor de Wk
	 */
	public double getWk() {
		return dWk;
	}

	
	/*
	 * getN
	 * 
	 * metodo que regresa la cantidad de parejas
	 */
	public int getN() {
		return iN;
	}
	
	/*
	 * sum
	 * 
	 * metodo que regresa la sumatoria de los elementos de una lista
	 * 
	 * @param lstNums es el valor <code>List<></code> que contiene
	 * los elementos a sumar
	 * @return un valor <code>double</code> que es el valor de la sumatoria
	 * */
	//&i
	public double sum(List<Double> lstNums) {
		double dResultado = 0.0d;
		
		for (int iI = 0; iI < lstNums.size(); iI ++) {
			dResultado += lstNums.get(iI);
		}
		
		return dResultado;
	}
	/*
	 * sum
	 * 
	 * metodo que regresa la sumatoria de los cuadrados
	 * de los elementos de una lista
	 * 
	 * @param lstNums es el valor <code>List<></code> que contiene
	 * los elementos a sumar
	 * @return un valor <code>double</code> que es el valor de la sumatoria
	 * */
	//&i
	public double sum2(List<Double> lstNums) {
		double dResult = 0.0d;
		
		for (int iI = 0; iI < lstNums.size(); iI ++) {
			dResult += Math.pow(lstNums.get(iI), 2);
		}
		
		return dResult;
	}
	
	/*
	 * sumMult
	 * 
	 * metodo que regresa la sumatoria de los productos
	 * de los elementos de dos listas
	 * 
	 * @param lstNums1 es el valor <code>List<></code> que contiene
	 * los elementos a multiplicar de la primera lista
	 * 
	 * @param lstNums2 es el valor <code>List<></code> que contiene
	 * los elementos a multiplicar de la segunda lista
	 * 
	 * @return un valor <code>double</code> que es el valor de la sumatoria
	 * */
	//&i
	public double sumMult(List<Double> lstNums1, List<Double> lstNums2) {
		double dResultado = 0.0d;
		
		for (int iI = 0; iI < lstNums1.size(); iI ++) {
			dResultado += lstNums1.get(iI) * lstNums2.get(iI);
		}
		return dResultado;
	}
	/*
	 * getMat
	 * 
	 * metodo que regresa la matriz con los valores de acuerdo 
	 * al metodo de regresion multiple del PSP
	 * 
	 * @return un valor <code>doubleMat</code> que contiene los valores de la matriz
	 * */
	//&i
	public double[][] getMat() {
		
		double[][] dMatValores = new double[][] { 
			{getN(),      sum(getW()),             sum(getX()),             sum(getY()),             sum(getZ())}, 
			{sum(getW()), sum2(getW()),            sumMult(getW(), getX()), sumMult(getW(), getY()), sumMult(getW(), getZ())},
			{sum(getX()), sumMult(getW(), getX()), sum2(getX()),            sumMult(getX(), getY()), sumMult(getX(), getZ())}, 
			{sum(getY()), sumMult(getW(), getY()), sumMult(getX(), getY()), sum2(getY()),            sumMult(getY(), getZ())}
		};
		return dMatValores;
	}	
}
