/*
 * DistribucionT
 * 
 * Programa que calcula la integral de la distribucion T por medio de la relga de Simpson
 * @author Jose Gonzalez	A01036121
 * @date 17/04/2016
 * @version 2.0 
 * */

//&p-DistribucionT
//&b=70
public class DistribucionT {
	// Error aceptable
	static double dE = 0.00000000000001;
	// Grados de libertad para aproximar la integral
	double dDof = 0.0;
	String sDof = new String();
	// Límite superior de la integral 
	double dX = 1.0;
	// valor de la integral
	double dP = -1.0;
	static String sP = new String();

	// &i
	public double calcularX() { //&m
		// Calcula la integral de la distribución T
		Integral intI = new Integral();
		// Contiene el resultado de la integral
		double dRes = 0.0; 
		// Número de segmentos
		int iNum_seg = 10;
		// leer del teclado
		//&d=1

		double dCambio = 0.5;
		boolean bSign = false;
		boolean bSignAux = true;
		
		do {
			// se integra
			dRes = integrar(intI, iNum_seg); // &m
			// si no se cumple con el error y no nos hemos pasado
			if ((dP - dRes) > dE) {
				// continua
				bSign = true;
				// si me habia pasado y me regrese entonces cambio la cantidad en aue me desplazo
				if (bSign != bSignAux) {
					dCambio /= 2.0;
					bSignAux = true;
				}
				// se agrega esa cantidad a la x estimada
				dX += dCambio;
				// si me pase
			} else if ((dP - dRes) < -1.0 * dE) {
				
				bSignAux = false;
				// y si en la iteracion anterior no me habia pasado
				if (bSign != bSignAux) {
					// reduzco la cantidad de cambio en la x
					dCambio /= 2.0;
					bSign = false;
				}
				// y se la resto para ahora seguir hacia el otro lado
				dX -= dCambio;
			}
		} while (Math.abs(dRes - dP) > dE);
		
		// establecer resultado
		return dX; //&m
		
		
	}
	
	//&d=25
	
	/*
	 * integrar
	 * 
	 * llevar a cabo la integracion
	 * 
	 * @param intI es el valor <code>Integral</code> con el objeto integral para
	 * usar los metodos de integracion.
	 * 
	 * @param iNum_seg es el valor <code>Int</code> con la cantidad de segmentos
	 * a dividir la distribucion
	 */
	// &i
	public double integrar(Integral intI, int iNum_seg)	{

		// variables para los resultados de las integrales
		double dRes1;
		double dRes2;

		// repetir hasta tener un resultado menor al error tolerable
		do {
			dRes1 = intI.integrar(dX, dDof, iNum_seg);
			dRes2 = intI.integrar(dX, dDof, 2 * iNum_seg);

			if (Math.abs(dRes1 - dRes2) > dE) {
				iNum_seg = iNum_seg * 2;
			}
		} while (Math.abs(dRes1 - dRes2) > dE);

		return dRes2;
	}

	//&d=4
}
