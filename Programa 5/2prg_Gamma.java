/*
 * Gamma
 * 
 * Clase que calcula el valor de la funcion Gamma usado en la integral de Simpson
 * @author Jose Gonzalez	A01036121
 * @date 15/4/2016
 * @version 1.0 
 * */
//&p-Gamma
//&b=16
public class Gamma {
	/*
	 * calcularGamma
	 * 
	 * escoge la funcion adecuada para calcular gamma dependiendo de si
	 * el argumento es entero o de punto flotante
	 * 
	 * @param iDof es el valor <code>Int</code> con el valor modificado de los grados de libertad
	 */
	//&i
	public double calcularGamma(double iDof) {
		// si es entero
		if (iDof == Math.round(iDof)) { 
			// llamar a gammaEntero
			return gammaEntero(iDof);
			// si no
		} else {
			// llamar gammaDecimal
			return gammaDecimal(iDof);
		}
	}
	
	/*
	 * gammaEntero
	 * 
	 * calcula el factorial de manera iterativa
	 * 
	 * @param iDof es el valor <code>Int</code> con el valor modificado de los grados de libertad
	 */
	//&i
	public double gammaEntero(double iDof) {

		double dFact = iDof - 1;
		
		for (int iI = 1; iI < (iDof - 1); iI++) {
			dFact *= iI;
		}
		
		return dFact;
	}

	/*
	 * gammaDecimal
	 * 
	 * calcula el factorial de manera recursiva
	 * 
	 * @param iDof es el valor <code>Int</code> con el valor modificado de los grados de libertad
	 */
	//&i
	public double gammaDecimal(double dDof) {

		if (dDof == (1 / 2) || dDof == 0.5) {
			return Math.sqrt(Math.PI);
		} else {
			return (dDof - 1) * gammaDecimal(dDof - 1);
		}
	}
}
