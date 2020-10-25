package objetos;

import logic.Game;

public class Vampiro {

	private int posx;
	private int posy;
	private int vida;
	//private Game game;
	
	
	
	/**CONSTRUCTOR
	 * @param posx
	 * @param posy
	 * @param vida
	 */
	public Vampiro(int posx, int posy, int vida) {
		super();
		this.posx = posx;
		this.posy = posy;
		this.vida = vida;
	}
	
	//pos x
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}


	//pos y
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
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
}
