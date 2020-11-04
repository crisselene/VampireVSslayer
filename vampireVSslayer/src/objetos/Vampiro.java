package objetos;

public class Vampiro {

	private int posx;
	private int posy;
	private int vida;
	private int ciclos = 0;
	//private Game game;
	
	
	
	/**CONSTRUCTOR
	 * @param posx
	 * @param posy
	 * @param vida
	 */
	public Vampiro(int posy, int posx, int vida) {
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
	//Metodo que avanza al vampiro
	public void avanza() {
		if(ciclos == 2) {//Si le toca avanzar
			posx--;
			ciclos = 0;
		}
		else {
			ciclos++;
		}
	}
}
