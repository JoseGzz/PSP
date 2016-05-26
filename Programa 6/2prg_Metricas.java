/*
 * Metricas
 * 
 * Programa que calcula métricas de correlación de acuerdo al PSP
 * 
 * @author Jose Gonzalez	A01036121
 * @date 17/04/2016
 * @version 1.0 
 * */

//&p-Metricas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

//&b=64
public class Metricas {
	/*
	 * main
	 * 
	 * Método principal del programa
	 * 
	 * @param args es el valor <code>String</code> con la entrada del teclado
	 */
	// &i
	public static void main(String[] args) {
		// objeto para organizar los datos leídos
		Organizador orgOrganizador = new Organizador();
		// nombre del archivo
		String sNombre;
		// linea actual que se esta leyendo
		String sLinea;
		// estructura con las lineas leidas
		ArrayList<String> arrLineas = new ArrayList<String>();
		// variable para leer linea por linea
		BufferedReader brBuffer;
		// scanner para el nombre del archivo
		Scanner scrReader = new Scanner(System.in);
		// cantidad de parejas
		int iN;
		// primer renglón
		double dXk;
		// coeficientes de correlacion
		double dR;
		double dR2;
		// parámetros de regresión lineal
		double dB0;
		double dB1;
		// predicción mejorada
		double dYk;

		do {
			// se pide el nombre del archivo
			System.out
					.println("Introduzca el nombre de un archivo que exista: ");
			// se lee
			sNombre = scrReader.nextLine();
			// se verifica que exista
		} while (!buscaArchivo(sNombre));

		try {
			brBuffer = new BufferedReader(new FileReader(sNombre));
			// se lee linea por linea
			while ((sLinea = brBuffer.readLine()) != null) {
				// si no esta vacia
				if (!sLinea.trim().isEmpty()) {
					// se añade a la estructura
					arrLineas.add(sLinea);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// se llama al método para organizar los datos leídos del archivo
		orgOrganizador.organizar(arrLineas);
		// se crea un objeto de tipo calculadora para realizar todos los
		// cálculos
		Calculadora calc = new Calculadora(orgOrganizador.iX,
				orgOrganizador.iY, arrLineas.size() - 1, orgOrganizador.getXk());
		// se recuperan los datos calculados
		iN = calc.getN();
		dXk = orgOrganizador.getXk();
		dR = calc.getR();
		dR2 = calc.getR2();
		dB0 = calc.getB0();
		dB1 = calc.getB1();
		dYk = calc.getYk();
		double dSig;
		dSig = calc.getSig();
		// se establece un formato de impresión para números de los cuales no se
		// sabe si serán enteros o de punto flotante
		DecimalFormat df = new DecimalFormat("#.#####");
		df.setRoundingMode(RoundingMode.CEILING);

		// se imrpimen los resultados
		System.out.println("N   = " + iN); // &m
		System.out.println("xk  = " + df.format(dXk)); // &m
		System.out.printf("r   = %.5f \n", dR); // &m
		System.out.printf("r2  = %.5f \n", dR2); // &m
		System.out.printf("b0  = %.5f \n", dB0); // &m
		System.out.printf("b1  = %.5f \n", dB1); // &m
		System.out.printf("yk  = %.5f \n", dYk); // &m
		System.out.printf("sig = %.10f \n", dSig);
		System.out.printf("ran = %.5f \n", calc.getRango());
		System.out.printf("LS  = %.5f \n", calc.getUPI());
		System.out.printf("LI  = %.5f \n", (calc.getLPI() > 0) ? calc.getLPI() : 0);

		scrReader.close();

	}

	/*
	 * buscaArchivo
	 * 
	 * busca que el archivo cuyo nombre ha sido especificado exista en el
	 * sistema regresa verdadero si se ha encontrado o bien falso en el caso
	 * contrario.
	 * 
	 * @param sNombre es el valor <code>String</code> con el nombre del archivo
	 * que se desea leer
	 */
	// &i
	public static boolean buscaArchivo(String sNombre) {
		// se busca leer el archivo con el nombre especificado, si no se
		// encuentra regresa falso
		try {
			BufferedReader brBuffer;
			brBuffer = new BufferedReader(new FileReader(sNombre));
			brBuffer.close();
		} catch (Exception eE) {
			return false;
		}
		return true;
	}

}
