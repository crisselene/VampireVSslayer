package logic;

import objetos.Vampiro;

public class VampireList {

	//array vacio
	static Vampiro arrayVamp[];
	//longitud del array (cantidad de vampiros en el juego)
	static int longitud;
	
	//crear array dependiendo del nivel del juego
	public void arrayVampNivel (double numberOfVampires ) {
			arrayVamp = new Vampiro[(int) numberOfVampires-1];
			//longitud es el tamaño del array
			longitud = arrayVamp.length;
			
	}
	
	//getter del array vampiros
	public static Vampiro[] getArrayVamp() {
		return arrayVamp;
	}
	
	//get long del array vampiros
	public static int getLongitud() {
		return longitud;//return longitud;
	}
	
	//TODO  si se cambia a ARRAYLIST, sería más fácil añadir objeto (.add() )
	public static boolean addVampire(Vampiro vampiro) {
		//array auxiliar para copiar el que había
		Vampiro aux[] = new Vampiro[longitud+1]; 
		for (int i = 0; i < longitud ; i++) {
			aux[i] = arrayVamp[i];
		}
		//añadir el ultimo vampiro creado
		aux[longitud] = vampiro;
		
		//arrayVamp pasa a ser aux
		arrayVamp = aux;
		//ahora el array es una posición más largo
		longitud = longitud + 1;
		return true;
	}
	
	public static void avanzarVampire() {
		//recorro el array y le sumo uno a la pos x
		for (int i = 0; i < longitud ; i++) {
			int posy = arrayVamp[i].getPosy();
			posy--;
			arrayVamp[i].setPosy(posy);
		}
				
	}
	



	
	
	
	
	
}
