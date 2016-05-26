/*
 * ContadorLineas
 * 
 * Programa que analiza la cantidad de lineas en blanco y lineas con informacion
 * de N cantidad de documentos
 * 
 * @author Jose Gonzalez	A01036121
 * @date 2/2/2016
 * @version 1.0 
 * */

//&p-ContadorLineas
import java.util.Scanner;

public class ContadorLineas {
	//&i
	public static void main(String[] args) {
		// variable para leer del teclado
		Scanner scrReader = new Scanner(System.in);
		String sName = null; 
		// variable para la cantidad de archivos
		int iN = 0;
		// controla si se ha encontrado el archivo
		boolean bFound; 
		// variable para manipular el archivo
		Files flsFile; 
		// arreglo de todos los archivos leidos
		Files[] flsArrFiles; 
		// objeto para imprimir los resultados del analisis
		Printer ptrPrinter = new Printer(); 

		// se pide la cantidad de archivos
		System.out.print("¿Cuantos archivos desea leer? ");

		// se valida que la entrada sea de tipo numerico
		while (!scrReader.hasNextInt()) {
			scrReader.next();
			System.out.println("Introduzca un numero: ");
		}

		// se lee la cantidad de archivos
		iN = scrReader.nextInt(); 
		sName = scrReader.nextLine();

		flsArrFiles = new Files[iN];

		// para cada archivo
		for (int iI = 0; iI < iN; iI++) {
			flsFile = new Files();
			// se pide su nombre
			System.out.println("Nombre del archivo " + (iI + 1) + ": ");
			sName = scrReader.nextLine();
			// se busca que el archivo exista en el sistema
			bFound = flsFile.searchFile(sName);
			// se valida que el archivo exista
			while (!(bFound)) {
				System.out
						.println("No se encontró el archivo, intente de nuevo.");
				System.out.println("Nombre del archivo: ");
				sName = scrReader.nextLine();
				bFound = flsFile.searchFile(sName);
			}
			// se analiza la composicion del archivo
			flsFile.analyze();
			// se agrega al arreglo de archivos
			flsArrFiles[iI] = flsFile;
		}

		// se imprimen los resultados del analisis
		ptrPrinter.print(flsArrFiles, iN);

		scrReader.close();
	}

}
