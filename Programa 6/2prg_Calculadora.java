/*
 * Calculadora
 * 
 * Utiliza las f�rmulas de PSP para obtener los factores de correlaci�n
 * 
 * @author Jose Gonzalez	A01036121
 * @date 1/3/2016
 * @version 1.0 
 * */
//&p-Calculadora
//&b=65
import java.util.List;

public class Calculadora {

	// objetos para llamar a los metodos de integracion y calculo de la T
	Integral intIntegral = new Integral();
	DistribucionT distT = new DistribucionT();
	// cantidad de parejas
	int iN;
	// factor para calcular predicci�n mejorada
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
	 * getXSig
	 * 
	 * Metodo que calcula la X usada en la significancia
	 */
	public double getXSig() {
		return (Math.abs(getR()) * Math.sqrt(iN - 2)) / Math.sqrt(1 - getR2());
	}

	/*
	 * getSig()
	 * 
	 * Metodo que calcula la significancia
	 */
	public double getSig() {
		distT.dX = getXSig();
		distT.dDof = iN - 2;

		return 1 - 2 * distT.integrar(intIntegral, 10);
	}

	/*
	 * getSig()
	 * 
	 * Metodo que calcula la significancia
	 */
	public double getRango() {
		distT.dP = 0.35;
		distT.dDof = iN - 2;

		return distT.calcularX() * getSigma() * getFactorRango();
	}

	/*
	 * getSigma()
	 * 
	 * Metodo que calcula la desviacion estandar
	 */
	public double getSigma() {

		double dAcumulado = 0;

		for (int iI = 0; iI < iN; iI++) {
			dAcumulado += Math.pow(
					arrY.get(iI) - getB0() - getB1() * arrX.get(iI), 2);
		}

		return Math.sqrt((1.0 / (iN - 2)) * dAcumulado);
	}

	/*
	 * getFactorRango()
	 * 
	 * Metodo que calcula el tercer factor de la formula del rango
	 */
	public double getFactorRango() {
		double dAcum = 0;

		for (int iI = 0; iI < iN; iI++) {
			dAcum += Math.pow(arrX.get(iI) - xAvg(), 2);
		}

		return Math
				.sqrt(1.0 + 1.0 / iN + (Math.pow((dXk - xAvg()), 2) / dAcum));
	}

	/*
	 * getUPI()
	 * 
	 * Metodo que calcula el limite superior de la estimacion
	 * 
	 */

	public double getUPI() {
		return getYk() + getRango();
	}

	/*
	 * getLPI()
	 * 
	 * Metodo que calcula el limite inferior de la estimacion
	 */

	public double getLPI() {
		return getYk() - getRango();
	}

	/*
	 * getR
	 * 
	 * Regresa el valor del coeficiente de correlaci�n 'r^2'
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
	 * Regresa el valor del coeficiente de correlaci�n 'r'
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
	 * M�todo que regresa el par�metro de regresi�n lineal 'b0'
	 */
	// &i
	public double getB0() {
		return yAvg() - getB1() * xAvg();
	}

	/*
	 * getB1
	 * 
	 * M�todo que regresa el par�metro de regresi�n lineal 'b1'
	 */
	// &i
	public double getB1() {
		return (sumXY() - iN * xAvg() * yAvg())
				/ (sumX2() - iN * Math.pow(xAvg(), 2));
	}

	/*
	 * getB0
	 * 
	 * M�todo que regresa el valor de la 'predicci�n mejorada'
	 */
	// &i
	public double getYk() {
		return getB0() + getB1() * dXk;
	}

	/*
	 * sumX
	 * 
	 * M�todo que regresa la suma de las X's
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
	 * M�todo que regresa la suma de los cuadrados de las X's
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
	 * M�todo que regresa la suma de las Y's
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
	 * M�todo que regresa la suma de los cuadrados de las Y's
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
	 * M�todo que regresa el promedio de los valores de Y
	 */
	public double yAvg() {
		return sumY() / iN;
	}

	/*
	 * xAvg
	 * 
	 * M�todo que regresa el promedio de los valores de X
	 */
	// &i
	public double xAvg() {
		return sumX() / iN;
	}

	/*
	 * sumXY
	 * 
	 * M�todo que regresa la suma de los productos de las X's y las Y's
	 */
	// &i
	public double sumXY() {
		sumXY = 0;
		for (int iI = 0; iI < iN; iI++) {
			sumXY += arrY.get(iI) * arrX.get(iI);
		}
		return sumXY;
	}

}
