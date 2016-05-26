/*
 * Prediccion
 * 
 * Clase que lee un archivo con datos y despliega reultados.
 * 
 * @author Jose Gonzalez	A01036121
 * @date 24/4/2016
 * @version 1.0 
 * 
 * */

//&p-Prediccion
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prediccion {

	/*
	 * main
	 * 
	 * metodo main del programa
	 * @param args es el valor <code>String[]</code> que contiene
	 * argumentos leídos del teclado a la hora de compilar
	 */
	//&i
	public static void main(String[] args) {

		// objeto para clasificar los datos leídos
		Clasificador clasC = new Clasificador();
		// arreglo con las betas para calcular la prediccion mejorada
		double[] dBetas = null;
		// objeto para reducir la matriz y obtener las betas
		Gauss gasMat = new Gauss();
		// nombre del archivo
		String sNombre;
		// linea actual que se esta leyendo
		String sLinea;
		// lista con las lineas leidas
		ArrayList<String> arrLineas = new ArrayList<String>();
		// variable para leer cada linea
		BufferedReader brBuffer;
		// scanner para leer el archivo
		Scanner scrReader = new Scanner(System.in);
	
		// hacer los siguiente mintras el clasificador no encuentre anomalias 
		do {
			// se limpia en cada iteracion la lista
			arrLineas.clear();
			// mientras no se encuentre el archivo, seguir preguntado
			do {
				// se pide el nombre del archivo
				System.out.println("Introduzca el nombre del archivo: ");
				// se lee
				sNombre = scrReader.nextLine();
				// se verifica que exista
				if (!buscaArchivo(sNombre)) {
					System.out.println("No se encontró el archivo.");
				}

			} while (!buscaArchivo(sNombre));

			try {
				brBuffer = new BufferedReader(new FileReader(sNombre));
				// se lee linea por linea
				while ((sLinea = brBuffer.readLine()) != null) {
					// si no esta vacia
					if (!sLinea.trim().isEmpty()) {
						// se añade a la lista
						arrLineas.add(sLinea);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} while (!clasC.clasificar(arrLineas));
		// se cierra el scanner
		scrReader.close();
		// se reduce la matriz y se despejan las betas
		dBetas = gasMat.reducir(clasC.getMat());

		// imprimir resultados con 5 decimales
		System.out.println("N  = " + clasC.getN());
		System.out.printf("wk = %.5f\n", clasC.getWk());
		System.out.printf("xk = %.5f\n", clasC.getXk());
		System.out.printf("yk = %.5f\n", clasC.getYk());

		System.out.println("------------------");

		System.out.printf("b0 = %.5f\n", dBetas[0]);
		System.out.printf("b1 = %.5f\n", dBetas[1]);
		System.out.printf("b2 = %.5f\n", dBetas[2]);
		System.out.printf("b3 = %.5f\n", dBetas[3]);

		System.out.println("------------------");

		System.out.printf("zk = %.5f", (dBetas[0] + clasC.getWk() * dBetas[1]
				+ clasC.getXk() * dBetas[2] + clasC.getYk() * dBetas[3]));

	}

	/*
	 * buscaArchivo
	 * 
	 * busca que el archivo exista en el sistema actual de archivos
	 * 
	 * @param sNombre es el valor <code>String</code> con el nombre del archivo
	 * @return un valor <code>boolean</code> que regresa true si se encontró y false al contrario
	 */
	// &i
	public static boolean buscaArchivo(String sArch) {
		try {
			BufferedReader brBuffer;
			brBuffer = new BufferedReader(new FileReader(sArch));
			brBuffer.close();
		} catch (Exception eE) {
			return false;
		}
		return true;
	}
}
