/*
 * Gauss
 * 
 * Resuelve un sistema de ecuaciones de acuerdo
 * al metodo de Gauss y sustitucion hacia atras.
 * 
 * @author Jose Gonzalez	A01036121
 * @date 24/4/2016
 * @version 1.0 
 * */

//&p-Gauss
public class Gauss {
	
	/*
	 * Gauss
	 * 
	 * Constructor de la clase Gauss
	 */
	public Gauss() {
	}
	
	/*
	 * reducir
	 * 
	 * metodo con todo el procedimiento de reduccion y sustitucion
	 * 
	 * @param dMat es el valor <code>double[][]</code> que contiene la
	 * matriz a reducir (dimensiones de 4 X 5)
	 * @return un valor <code>double[]</code> que representa los valores de las betas
	 */
	//&i
	public double[] reducir(double[][] dMatValores) {

		// el procedimiento se ejecuta 3 veces, una por pivote
		for (int iK = 0; iK < 3; iK++) {

			// buscar posicion con el no. mayor del pivote
			int iPos = 0;
			double dAux = dMatValores[iK][iK];
			for (int iI = 0; iI < 4; iI++) {
				if (Math.abs(dMatValores[iI][iK]) > Math.abs(dAux)) {
					dAux = dMatValores[iI][iK];
					iPos = iI;
				}
			}
			// si todos los numeros en la columna son cero, no hay solucion
			if (dMatValores[iPos][iK] == 0) {
				System.out.println("No hay solución.");
				System.exit(0);
			}

			// si se encontro algun numero mayor
			dAux = 0d;
			if (iPos > 0) {
				// realizar el cambio de renglones
				for (int iI = iK; iI < 5; iI++) {
					dAux = dMatValores[iPos][iI];
					dMatValores[iPos][iI] = dMatValores[iK][iI];
					dMatValores[iK][iI] = dAux;
				}
			}

			// dividir renglon actual entre el reciproco del primer termino
			double dDivisor = dMatValores[iK][iK];
			for (int iI = iK; iI < 5; iI++) {
				dMatValores[iK][iI] /= dDivisor;
			}

			// se generan los ceros debajo del pivote
			
			for (int iI = 1; iI < 4 - iK; iI++) {
				double dFactor = dMatValores[iI + iK][iK];
				for (int iJ = 0; iJ < 5 - iK; iJ++) {
					dMatValores[iI + iK][iJ + iK] += dMatValores[iK][iJ + iK] * (-1) * dFactor;
				}
			}
		}

		// despeje de variables
		double[] dVars = new double[5];
		int iN = 4;
		 // variable para el resultado de las restas por despejes
		double dVal = 0d;
		 // directamente se  obtiene el valor de la ultima variable
		dVars[0] = dMatValores[iN - 1][iN] / dMatValores[iN - 1][iN - 1];
		 // a partir del penultimo renglon (por que se acaba de otener Xn sigue Xn-1)
		for (int iI = 2; iI <= iN; iI++) {
			// se obtiene el valor del vector de resultados correspondiente	
			dVal = dMatValores[iN - iI][iN]; 
			// se realizan las restas en el despeje de cada coeficiente		
			for (int iJ = 0; iJ < iI - 1; iJ++) { 
			// multiplicando cada uno por los valores de sus respectivas variables
				dVal -= dMatValores[iN - iI][iN - iJ - 1] * dVars[iJ];
			}
			// division entre el coeficiente que acompaña a la variable que se desea despejar
			dVars[iI - 1] = dVal / dMatValores[iN - iI][iN - iI]; 
		}
		
		// se inicializa un arreglo con basura para las betas
		double[] dBetas = new double[] {1, 2, 3, 4};
		
		for (int i = 0; i < iN; i++) {
			dBetas[i] = dVars[iN - i - 1];
		}
		// se regresan los valores de las betas despejadas
		return dBetas;
	}

}
