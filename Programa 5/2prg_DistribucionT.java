/*
 * DistribucionT
 * 
 * Programa que calcula la integral de la distribucion T por medio de la relga de Simpson
 * @author Jose Gonzalez	A01036121
 * @date 15/4/2016
 * @version 2.0 
 * */

//&p-DistribucionT
import java.util.Scanner;

//&b=44
public class DistribucionT {
	// Error aceptable
	static double dE = 0.00000000000001;
	// Grados de libertad para aproximar la integral
	static double dDof = 0.0; // &m
	static String sDof = new String();
	// Límite superior de la integral 4.604094871282727
	static double dX = 1.0;
	// valor de la integral
	static double dP = -1.0;
	static String sP = new String();

	// &i
	public static void main(String[] args) {
		// Calcula la integral de la distribución T
		Integral intI = new Integral();
		// Contiene el resultado de la integral
		double dRes = 0.0; // &m
		// Número de segmentos
		int iNum_seg = 10;
		// leer del teclado
		leer();

		double dCambio = 0.5;

		// NUEVO iterar hasta que el valor de x sea el adecuado ----
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

		// ------
		
		// establecer resultado
		intI.setResultado(dP);
		// deplegar resultado final
		desplegar(intI);
	}

	/*
	 * leer
	 * 
	 * leer datos del teclado
	 */
	// &i
	public static void leer() {
		// pide datos al usuario
		Scanner scnKey = new Scanner(System.in);
		do {
			System.out.println("Ingresar el valor de p: "); // &m
			sP = scnKey.nextLine(); // &m
			// NUEVO ------- verificar que no se ingrese un valor que no sea numerico
			try {
				dP = Double.parseDouble(sP);
			} catch (Exception e) {
				System.out.println("Ingrese un valor numérico.");
			}
			// -----

			if (dP < 0 || dP > 0.5) { // &m
				System.out.println("Debe ser un número real entre 0 y 0.5."); // &m
			}

		} while (dP < 0 || dP > 0.5); // &m

		do {
			System.out.println("Ingresar grados de libertad: ");

			sDof = scnKey.nextLine();

			// &d=5

			// NUEVO ------- verificar que no se ingrese un valor que no sea numerico
			try {
				dDof = Double.parseDouble(sDof);
			} catch (Exception e) {
				System.out.println("Ingrese un valor numérico.");
				dDof = -10;
			}
			// -----

			if (dDof < 1) {
				System.out.println("Debe ser mayor a cero.");
			}

			if (dDof != Math.round(dDof)) {
				System.out.println("Ingrese un valor entero.");
				dDof = -10;
			}

		} while (dDof < 1);

		scnKey.close();
	}

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
	public static double integrar(Integral intI, int iNum_seg) {

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
		// &d=3

	}

	/*
	 * desplegar
	 * 
	 * despliega el valor del límite superior de la integral, los grados de
	 * libertad ingresados, el resultado de la integral
	 * 
	 * @param intI es el valor <code>Integral</code> con el objeto integral para
	 * extraer el resultado que se desea leer
	 */
	// &i
	public static void desplegar(Integral intI) {
		System.out.printf("  p = %.5f \n", intI.getResultado()); //&m
		System.out.println("dof = " + (int) dDof);
		System.out.printf("  x = %.5f \n", dX); //&m
	}

}
