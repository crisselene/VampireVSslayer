package objetos;

import logic.VampireList;

public class Vampiro {

	private int posx;
	private int posy;
	private int vida;
	private int ciclosAvance;
	private boolean ataque; //variable que guarda si le toca atacar a un vampiro
	//private Game game;
	
	
	
	/**CONSTRUCTOR
	 * @param posx
	 * @param posy
	 * @param vida
	 */
	public Vampiro(int posy, int posx, int vida, int ciclosAvance,boolean ataque) {
		super();
		this.posx = posx;
		this.posy = posy;
		this.vida = vida;
		this.ciclosAvance = ciclosAvance;
		this.ataque = ataque;
	}
	
	//pos x
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}

	//cambiar el vampiro a atacar(true) o no atacar (false)
	public void setAtaque(boolean ataque) {
		this.ataque = ataque;
	}

	//pos y
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}

	//getters y setters de avanzar
	public int getCiclosAvance() {
		return ciclosAvance;
	}
	public void setCiclosAvance(int ciclosAvance) {
		this.ciclosAvance = ciclosAvance;
	}

	//vida
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}


	//Escribir el vampiro
	public String toString() {
		return "V [" + vida + "]";
	}


	//TODO: metodo que comprueba si tiene un slayer delante y muerde
	public boolean morder() {
		return false;
		
	}
	//Metodo que avanza al vampiro
	public static void avanza(VampireList vampireList) {
	//recorro el array de vampiros y compruebo si alguno tiene los ciclos a 2, 
		//entonces le tocará avanzar
		Vampiro[] arrayVamp = vampireList.getArrayVamp();
		for (int i = 0; i < arrayVamp.length; i++) {
			//compruebo que no ha atacado, porque si ataca, no avanza
			//if(arrayVamp[i].ataque == false) {
				if(arrayVamp[i].ciclosAvance == 2 && arrayVamp[i].ataque == false) {//Si le toca avanzar...
					int posXv = arrayVamp[i].getPosx();
					posXv--; //avanza hacia los negativos en el eje x
					arrayVamp[i].setPosx(posXv);
					arrayVamp[i].setCiclosAvance(0); //sus ciclos se reinician 
							
				}
				//si no avanza, el ciclo del vampiro aumenta
				else {
					int cicloVamp = arrayVamp[i].getCiclosAvance();
					//si el ciclo es distinto a 2 (2 es el max)
					if(cicloVamp!=2)cicloVamp++;
					arrayVamp[i].setCiclosAvance(cicloVamp);
					//si el vampiro ha atacado (ataque a true) entonces le ponemos ataque a false
					arrayVamp[i].setAtaque(false);
					//****************PRUEBAS*******************
				}
			//si no avanza es porque ha atacado ya, volvemos a poner ataque a false
			//}else arrayVamp[i].setAtaque(false);	
		}
		
	}
}
