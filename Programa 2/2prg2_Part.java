/*
 * Part
 * 
 * Clase que contiene los atributos b�sicos de una parte seg�n el PSP
 * 
 * @author Jose Gonzalez	A01036121
 * @date 14/2/2016
 * @version 1.0 
 * */

//&p-Part
public class Part {
	// base, deleted, modifyed, total, items
	int iB, iD, iM, iT, iI, iA; 
	StringBuilder strName;
	String strCategory;
	
	public Part() {
		strName = new StringBuilder();
		iB = iD = iM = iT = iI = 0;
		strCategory = new String();
	}
	
	/*
	 * clear
	 * 
	 * borra los valores actuales de cada atributo
	 * 
	 */
	//&i
	public void clear() {
		iB = iD = iM = iT = iI = 0;
	}
	
	/*
	 * calcA
	 * 
	 * calcula la cantidad de l�neas agregadas (A) de una parte
	 * 
	 * @param iT es el valor <code>int</code> con la cantidad de l�neas totales
	 * @param iB es el valor <code>int</code> con la cantidad de l�neas base
	 * @param iD es el valor <code>int</code> con la cantidad de l�neas eliminadas
	 */
	//&i
	public int calcA(int iT, int iB, int iD) {
		return iT - iB - iD;
	}
	
}
