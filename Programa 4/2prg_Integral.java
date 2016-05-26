/*
 * Integral
 * 
 * Clase que calcula integrales usando el metodo de Simpson
 * @author Jose Gonzalez	A01036121
 * @date 15/4/2016
 * @version 1.0 
 * */
//&p-Integral
public class Integral {
	// limite superior
	double dX;
	// cantidad de segmentos
	int iNum_seg;
	// operacion interna
	double dInterno;
	// primer termino de la funcion
	double dPrimerTermino;
	// segundo termino de la funcion
	double dSegundoTermino;
	// funcion evaluada
	double dF;
	// objeto para calcular gamma
	Gamma g = new Gamma();
	// termino de multiplcacion
	int iM;
	// repuesta numerica de integral
	double dRes;

	/*
	 * integrar
	 * 
	 * metodo para obtener la integral
	 * 
	 * @param dX es el valor <code>double</code> para el limite superior de la
	 * integral
	 * 
	 * @param iDof es el valor <code>double</code> para los grados de libertad
	 * 
	 * @param iNum_seg es el valor <code>int</code> para la cantidad de
	 * segmentos
	 */
	// &i
	public double integrar(double dX, double iDof, int iNum_seg) {
		dRes = 0;
		// termino de multiplicacion
		double dW = dX / iNum_seg;
		// inicia el limite desde el valor de dW
		dX = dW;
		// se calcula el segundo termino una sola vez
		dSegundoTermino = g.calcularGamma((iDof + 1) / 2)
				/ (Math.pow(iDof * Math.PI, 1.0 / 2.0) * g
						.calcularGamma(iDof / 2));
		// se hace toda la sumatoria simulando la integral
		for (int iI = 0; iI <= iNum_seg; iI++) {
			// se actualiza el valor del limite en cada ciclo
			if (iI == 0) {
				dX = 0;
			} else {
				dX = dW * iI;
			}
			// calculo de los demas terminos
			dInterno = 1 + Math.pow(dX, 2) / iDof;
			dPrimerTermino = Math.pow(dInterno, -1 * (iDof + 1) / 2);
			dF = dPrimerTermino * dSegundoTermino;
			// seleccoin del termino de multiplicacion
			if (iI == 0 || iI == iNum_seg) {
				iM = 1;
			} else if (iI % 2 == 0) {
				iM = 2;
			} else {
				iM = 4;
			}
			// acumulado de las areas de los segmentos
			dRes += (dW / 3) * iM * dF;
		}
		return dRes;
	}

	/*
	 * getResultado
	 * 
	 * regresa el valor numerico de la integral
	 */
	public double getResultado() {
		return this.dRes;
	}

	/*
	 * setResultado
	 * 
	 * establece el valor del resultado
	 * 
	 * @param dp es el valor <code>double</code> con el reultado de una
	 * integracion
	 */
	public void setResultado(double dP) {
		this.dRes = dP;
	}

}
