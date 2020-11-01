
package objetos;

public class Slayer {

	public final static int DEFAULT_VIDA = 3;
	private int posx;
	private int posy;
	private int vida;
	

	public Slayer(int posx, int posy) { //Constructor solo con posx y posy
		this(posx, posy, DEFAULT_VIDA);
	}
	public Slayer(int posx, int posy, int vida) { //Constructor completo
		this.posx = posx;
		this.posy = posy;
		this.vida = vida;
	}
	
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	
	
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
	this.vida = vida;
	}
	
	public String toString() {
		return "S ["+ vida +"]";
	}
	
	public void atack() {
		
	}
}