
package objetos;

import logic.GameObject;
import logic.GameObjectList;
import logic.IAttack;

public class Slayer extends GameObject{

	public final static int DEFAULT_VIDA = 3;
	private final static int ATAQUE = 1;
	private int posx;
	private int posy;
	//private int vida;
	//private GameObjectList list;
	

	public Slayer(int posx, int posy) { //Constructor solo con posx y posy
		this(posx, posy, DEFAULT_VIDA);
	}
	public Slayer(int posx, int posy, int vida) { //Constructor completo
		super(posx, posy, vida);
	}
	
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	
	
	/*public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
	this.vida = vida;
	}*/
	
	public String toString() {
		int vida = this.getVida();
		return "S ["+ vida +"]";
	}
	@Override
	public void attack(IAttack other) {
		//Solo atacamos si el otro esta en su misma fila
		other.receiveSlayerAttack(ATAQUE, posy, posx);
		System.out.println("He atacado");
	}
	
}