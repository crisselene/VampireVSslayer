
package objetos;

import logic.GameObject;
import logic.GameObjectList;
import logic.IAttack;

public class Slayer extends GameObject implements IAttack{

	public final static int DEFAULT_VIDA = 3;
	private final static int ATAQUE = 1;
	private int posx;
	private int posy;
	//private int vida;
	//private GameObjectList list;
	

	public Slayer(int posx, int posy) { //Constructor solo con posx y posy
		this(posx, posy, DEFAULT_VIDA);
		this.posx=posx;
		this.posy= posy;
	}
	public Slayer(int posx, int posy, int vida) { //Constructor completo
		super(posx, posy, vida);
		this.posx=posx;
		this.posy= posy;
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
	public void attack() {
		//Solo atacamos si el otro esta en su misma fila
		if(isAlive()) {
			IAttack other = game.getAttackableInPosition(this.posx, this.posy);
			other.receiveSlayerAttack(ATAQUE, posy, posx);
			System.out.println("He atacado");
		}
	}
	
	public boolean receiveVampireAttack(int harm,int posy,int posx) {
		if(this.posy==posy) {//Si estan en la misma fila
			if(this.posx+1 == posx) {
				int vida= this.getVida();
				vida = vida - harm;
				this.setVida(vida);
			}
		}
		return false;
	}
	
	//métodos en los que los slayers no actuan:
	@Override
	public void move() { // los slayers no se mueven
	}
	@Override
	public boolean contarVamp() {
		// no se cuenta porque no es vampiro
		return false;
	}
	@Override
	public boolean noHayVenLafila(boolean filaLibre,int fila) {return filaLibre;}
	
	
}