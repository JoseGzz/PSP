/*
 * Printer
 * 
 * Clase que imprime los resultados del analisis de los archivos en orden ascendente
 * 
 * @author Jose Gonzalez	A01036121
 * @date 2/2/2016
 * @version 1.0 
 * */

//&p-Printer
import java.util.Arrays;

public class Printer {

	/*
	 * Printer
	 * 
	 * Constructor de la clase Printer
	 */
	public Printer() {
	}
	
	/*
	 * print
	 * 
	 * imprimir los resultados finales del analisis de cada archivo
	 * 
	 * @param flsArrFiles es un arreglo de <code>Files</code> con los archivos
	 * analizados
	 * 
	 * @param iN un valor <code>int</code> que representa la cantidad de
	 * archivos
	 */
	//&i
	void print(Files[] flsArrFiles, int iN) {

		/*
		 * se ordenan los archivos en base a la cantidad de lineas con
		 * informacion de manera ascendente
		 */
		Arrays.sort(flsArrFiles);

		System.out.println();

		// se despliega la informacion correspondiente a cada archivo
		for (int iI = 0; iI < iN; iI++) {

			System.out.println("Nombre del archivo: "
					+ flsArrFiles[iI].getNombre());
			System.out.println("Cantidad de líneas en blanco: "
					+ flsArrFiles[iI].getEmptyLines());
			System.out.println("Cantidad de líneas con información: "
					+ flsArrFiles[iI].getInfoLines());
			if (iI < (iN - 1)) {
				System.out
						.println("--------------------------------------------");
			}
		}

		// se imprimen los resultados del analisis completo
		System.out.println("--------------------------------------------");
		System.out.println("TOTALES:");
		System.out.println("Cantidad de archivos: " + iN);
		System.out.println("Cantidad total de líneas en blanco: "
				+ Files.iTotalEmpty);
		System.out.println("Cantidad total de líneas con información: "
				+ Files.iTotalInfo);

	}

}
