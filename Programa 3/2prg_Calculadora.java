/*
 * Calculadora
 * 
 * Utiliza las fórmulas de PSP para obtener los factores de correlación
 * 
 * @author Jose Gonzalez	A01036121
 * @date 1/3/2016
 * @version 1.0 
 * */
//&p-Calculadora
import java.util.List;

public class Calculadora {

	// cantidad de parejas
	int iN;
	// factor para calcular predicción mejorada
	double dXk;
	// listas con valores de las X's y las Y's
	List<Double> arrX;
	List<Double> arrY;
	// valores de sumas de las X's, las Y's, sus cuadrados y sus productos
	double sumX;
	double sumY;
	double sumX2;
	double sumY2;
	double sumXY;

	/*
	 * Calculadora
	 * 
	 * Contructor de la clase Calculadora.java
	 */
	public Calculadora(List<Double> iX, List<Double> iY, int iN, double dXk) {
		this.arrX = iX;
		this.arrY = iY;
		this.iN = iN;
		this.dXk = dXk;
		sumX = 0;
		sumY = 0;
		sumX2 = 0;
		sumY2 = 0;
		sumXY = 0;
	}

	/*
	 * getR
	 * 
	 * Regresa el valor del coeficiente de correlación 'r^2'
	 */
	public double getR2() {
		return Math.pow(getR(), 2);
	}

	/*
	 * getN
	 * 
	 * regresa la cantidad de parejas
	 */
	int getN() {
		return iN;
	}

	/*
	 * getR
	 * 
	 * Regresa el valor del coeficiente de correlación 'r'
	 */
	// &i
	public double getR() {
		return (iN * sumXY() - sumX() * sumY())
				/ (Math.sqrt((iN * sumX2() - Math.pow(sumX, 2))
						* (iN * sumY2() - Math.pow(sumY(), 2))));
	}

	/*
	 * getB0
	 * 
	 * Método que regresa el parámetro de regresión lineal 'b0'
	 */
	// &i
	public double getB0() {
		return yAvg() - getB1() * xAvg();
	}

	/*
	 * getB1
	 * 
	 * Método que regresa el parámetro de regresión lineal 'b1'
	 */
	// &i
	public double getB1() {
		return (sumXY() - iN * xAvg() * yAvg())
				/ (sumX2() - iN * Math.pow(xAvg(), 2));
	}

	/*
	 * getB0
	 * 
	 * Método que regresa el valor de la 'predicción mejorada'
	 */
	// &i
	public double getYk() {
		return getB0() + getB1() * dXk;
	}

	/*
	 * sumX
	 * 
	 * Método que regresa la suma de las X's
	 */
	// &i
	public double sumX() {

		sumX = 0;
		for (int iI = 0; iI < iN; iI++) {
			sumX += arrX.get(iI);
		}
		return sumX;
	}

	/*
	 * sumX2
	 * 
	 * Método que regresa la suma de los cuadrados de las X's
	 */
	// &i
	public double sumX2() {
		sumX2 = 0;
		for (int iI = 0; iI < iN; iI++) {
			sumX2 += Math.pow(arrX.get(iI), 2);
		}
		return sumX2;
	}

	/*
	 * sumY
	 * 
	 * Método que regresa la suma de las Y's
	 */
	// &i
	public double sumY() {
		sumY = 0;
		for (int iI = 0; iI < iN; iI++) {
			sumY += arrY.get(iI);
		}
		return sumY;
	}

	/*
	 * sumY2
	 * 
	 * Método que regresa la suma de los cuadrados de las Y's
	 */
	// &i
	public double sumY2() {
		sumY2 = 0;
		for (int iI = 0; iI < iN; iI++) {
			sumY2 += Math.pow(arrY.get(iI), 2);
		}
		return sumY2;
	}

	/*
	 * yAvg
	 * 
	 * Método que regresa el promedio de los valores de Y
	 */
	public double yAvg() {
		return sumY() / iN;
	}

	/*
	 * xAvg
	 * 
	 * Método que regresa el promedio de los valores de X
	 */
	//&i
	public double xAvg() {
		return sumX() / iN;
	}

	/*
	 * sumXY
	 * 
	 * Método que regresa la suma de los productos de las X's y las Y's
	 */
	//&i
	public double sumXY() {
		sumXY = 0;
		for (int iI = 0; iI < iN; iI++) {
			sumXY += arrY.get(iI) * arrX.get(iI);
		}
		return sumXY;
	}

}
