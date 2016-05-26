/*
 * Printer
 * 
 * Clase que imprime los resultados del analisis de los archivos
 * 
 * @author Jose Gonzalez	A01036121
 * @date 2/2/2016
 * @version 2.0 
 * */

//&p-Printer
//&b=6
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Printer {
	// archivo de salida
	File fleSalida;

	/*
	 * Printer
	 * 
	 * Constructor de la clase Printer
	 */
	public Printer() {
		fleSalida = new File("ConteoLDC.txt");
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

		ArrayList<Part> arrBaseParts = new ArrayList<Part>();
		ArrayList<Part> arrNuevaParts = new ArrayList<Part>();
		ArrayList<Part> arrReusadaParts = new ArrayList<Part>();

		int iTotalLines = Files.iOutSideLines;

		for (int iI = 0; iI < iN; iI++) {
			for (int j = 0; j < flsArrFiles[iI].arrParts.size(); j++) { //&m
				if (flsArrFiles[iI].arrParts.get(j).strCategory == "base") {
					arrBaseParts.add(flsArrFiles[iI].arrParts.get(j));
				} else if (flsArrFiles[iI].arrParts.get(j).strCategory == "nueva") {
					arrNuevaParts.add(flsArrFiles[iI].arrParts.get(j));
				} else if (flsArrFiles[iI].arrParts.get(j).strCategory == "reusada") {
					arrReusadaParts.add(flsArrFiles[iI].arrParts.get(j));
				}
			}
		}
		try {

			if (!fleSalida.exists()) {
				fleSalida.createNewFile();
			}
			FileWriter fwrWriter = new FileWriter(fleSalida.getAbsoluteFile());
			BufferedWriter bfwReader = new BufferedWriter(fwrWriter);
			
			
			System.out.println("PARTES BASE:");
			bfwReader.write("PARTES BASE: \n");
			for(int iI = 0; iI < arrBaseParts.size(); iI ++) {
				System.out.print( "\t" + arrBaseParts.get(iI).strName + ": ");
				bfwReader.write("\t" + arrBaseParts.get(iI).strName + ": ");
				
				System.out.print("T="+ arrBaseParts.get(iI).iT + ", ");
				bfwReader.write("T="+ arrBaseParts.get(iI).iT + ", ");
				iTotalLines += arrBaseParts.get(iI).iT;
				
				System.out.print("I="+ arrBaseParts.get(iI).iI + ", ");
				bfwReader.write("I="+ arrBaseParts.get(iI).iI + ", ");
				
				System.out.print("B="+ arrBaseParts.get(iI).iB + ", ");
				bfwReader.write("B="+ arrBaseParts.get(iI).iB + ", ");
				
				System.out.print("D="+ arrBaseParts.get(iI).iD + ", ");
				bfwReader.write("D="+ arrBaseParts.get(iI).iD + ", ");
				
				System.out.print("M="+ arrBaseParts.get(iI).iM + ", ");
				bfwReader.write("M="+ arrBaseParts.get(iI).iM + ", ");
				
				System.out.print("A="+ (arrBaseParts.get(iI).iT -  arrBaseParts.get(iI).iB +  arrBaseParts.get(iI).iD));
				bfwReader.write("A="+ (arrBaseParts.get(iI).iT -  arrBaseParts.get(iI).iB +  arrBaseParts.get(iI).iD));
				
				System.out.println();
				bfwReader.write("\n");
			}
		
			System.out.println("-------------------------------------------------");
			bfwReader.write("-------------------------------------------------\n");
			
			System.out.println("PARTES NUEVAS:");
			bfwReader.write("PARTES NUEVAS:\n");
			
			for(int iI = 0; iI < arrNuevaParts.size(); iI ++) {
				System.out.print("\t" + arrNuevaParts.get(iI).strName + ": ");
				bfwReader.write("\t" + arrNuevaParts.get(iI).strName + ": ");
				iTotalLines += arrNuevaParts.get(iI).iT;
				
				System.out.print("T="+ arrNuevaParts.get(iI).iT + ", ");
				bfwReader.write("T="+ arrNuevaParts.get(iI).iT + ", ");
				
				System.out.print("I="+ arrNuevaParts.get(iI).iI);
				bfwReader.write("I="+ arrNuevaParts.get(iI).iI);
				
				System.out.println();
				bfwReader.write("\n");
			}
			
			System.out.println("-------------------------------------------------");
			bfwReader.write("-------------------------------------------------\n");
			
			System.out.println("PARTES REUSADAS:");
			bfwReader.write("PARTES REUSADAS:\n");
			
			for(int iI = 0; iI < arrReusadaParts.size(); iI ++) {
				System.out.print("\t" + arrReusadaParts.get(iI).strName + ": ");
				bfwReader.write("\t" + arrReusadaParts.get(iI).strName + ": ");
				
				System.out.print("T="+ arrReusadaParts.get(iI).iT + ", ");
				bfwReader.write("T="+ arrReusadaParts.get(iI).iT + ", ");
				iTotalLines += arrReusadaParts.get(iI).iT;
				
				System.out.print("I="+ arrReusadaParts.get(iI).iI + ", ");
				bfwReader.write("I="+ arrReusadaParts.get(iI).iI + ", ");
				
				System.out.print("B="+ arrReusadaParts.get(iI).iB);
				bfwReader.write("B="+ arrReusadaParts.get(iI).iB);
				
				System.out.println();
				bfwReader.write("\n");
			}
			
			System.out.println("-------------------------------------------------");
			bfwReader.write("-------------------------------------------------\n");
			
			System.out.println("Total de LDC: " + iTotalLines);
			bfwReader.write("Total de LDC: " + iTotalLines);
			
			bfwReader.close();
			
			//&d=16
		} catch (Exception excE) {
			System.out.println(excE);
		}
	}

}
