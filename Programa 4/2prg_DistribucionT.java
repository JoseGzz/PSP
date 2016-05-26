/*
 * DistribucionT
 * 
 * Programa que calcula la integral de la distribucion T por medio de la relga de Simpson
 * @author Jose Gonzalez	A01036121
 * @date 15/4/2016
 * @version 1.0 
 * */

//&p-DistribucionT
import java.util.Scanner;

//&b=3
public class DistribucionT {
	// Error aceptable
	static double dE = 0.0000001;
	// Grados de libertad para aproximar la integral
	static double iDof = 0;
	static// Límite superior de la integral
	double dX = 0;

	// &i
	public static void main(String[] args) {
		// Calcula la integral de la distribución T
		Integral intI = new Integral();
		// Contiene el resultado de la integral
		double dP = 0;
		// Número de segmentos
		int iNum_seg = 10;
		// leer del teclado
		leer();
		// integrar
		dP = integrar(intI, iNum_seg);
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
			System.out
					.println("Ingresar límite superior para la integral (x): ");
			dX = scnKey.nextDouble();
			if (dX < 0) {
				System.out.println("Debe ser mayor o igual a cero");
			}

		} while (dX < 0);

		do {
			System.out.println("Ingresar grados de libertad: ");
			try {
				iDof = scnKey.nextDouble();
				if (iDof < 1) {
					System.out.println("Debe ser mayor a cero");
				}
			} catch (Exception e) {
				System.out.println("Ingrese un valor numérico.");
				iDof = -1;
				break;
			}
			if (iDof != Math.round(iDof)) {
				System.out.println("Ingrese un valor entero.");
				iDof = -10;
			}

		} while (iDof < 1);

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
			dRes1 = intI.integrar(dX, iDof, iNum_seg);
			dRes2 = intI.integrar(dX, iDof, 2 * iNum_seg);

			if (Math.abs(dRes1 - dRes2) > dE) {
				iNum_seg = iNum_seg * 2;
			}
		} while (Math.abs(dRes1 - dRes2) > dE);

		return dRes2;
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
		System.out.printf("  x = %.5f \n", dX); // &m
		System.out.println("dof = " + (int) iDof); // &m
		System.out.printf("  p = %.5f \n", intI.getResultado()); // &m
	}

}
