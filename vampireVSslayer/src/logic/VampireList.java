package logic;

import objetos.Vampiro;

public class VampireList {

	//array vacio
	static Vampiro arrayVamp[];
	//longitud del array (cantidad de vampiros en el juego)
	static int longitud;
	//Vampiros que han salido en total
	static int vampSalidos;
	
	//crear array dependiendo del nivel del juego
	public void arrayVampNivel (double numberOfVampires ) {
			arrayVamp = new Vampiro[(int) numberOfVampires-1];
			//longitud es el tamaño del array
			longitud = arrayVamp.length;
			vampSalidos=0;
			
	}
	
	public static int getVampSalidos() {
		return vampSalidos;
	}
	
	//getter del array vampiros
	public static Vampiro[] getArrayVamp() {
		return arrayVamp;
	}
	
	//get long del array vampiros
	public static int getLongitud() {
		return longitud;//return longitud;
	}
	
	
	public static void addVampire(Vampiro vampiro) {
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
		//ha salido un vampiro mas
		vampSalidos++;
	}
	

	 public static void buscarVampiro() {
		//Buscamos los que han muerto
		for(int i = 0; i < longitud ; i++) {
			//Si ha muerto
			if(arrayVamp[i].getVida() == 0) {
				eliminarVampiro(i);
			}
		}
		
	}
	
	private static void eliminarVampiro(int pos) {
		//Eliminamos el vampiro
		Vampiro aux[] = new Vampiro[longitud - 1];
		for(int i = 0; i < longitud - 1; i++)
		{
			if(i < pos) {//Si esta antes del eliminado
				aux[i]=arrayVamp[i];
			}
			else {
				aux[i]=arrayVamp[i+1];
			}
		}
		//Hay un vampiro menos
		longitud--;
		arrayVamp=aux;
	}

	public static void reset() {
		arrayVamp = null;	
		longitud = 0;
		vampSalidos = 0;
	}

	public static boolean llegoAlFinal() {
		boolean llego = false;
		for (int i= 0; i< longitud; i++) {
			if(arrayVamp[i].getPosx()==-1) {
				llego = true;
			}
		}
		return llego;
	}
	



	
	
	
	
	
}
