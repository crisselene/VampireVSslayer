
package objetos;

import logic.Game;
import logic.GameObject;
import logic.IAttack;

public class Slayer extends GameObject implements IAttack{

	public final static int DEFAULT_VIDA = 3;
	private final static int ATAQUE = 1;
	private int posx;
	private int posy;
	private Game game;
	
	public Slayer(int posx, int posy, Game game) { //Constructor solo con posx y posy
		this(posx, posy, DEFAULT_VIDA, game);
		this.posx=posx;
		this.posy= posy;
		this.game = game;
		this.letra = "S ";
	}
	public Slayer(int posx, int posy, int vida, Game game) { //Constructor completo
		super(posx, posy, vida, game);
		this.posx=posx;
		this.posy= posy;
		this.game = game;
	}
	@Override
	public void attack() {
		// Solo atacamos si el otro esta en su misma fila
		if (isAlive()) {
			// desde la posición del slayer en adelante miramos si hay un atacable
			for (int i = this.posx + 1; i <= game.getLevelDimX(); i++) {
				IAttack other = game.getAttackableInPosition(i, this.posy);
				if (other != null) {
					if(other.receiveSlayerAttack(ATAQUE)) {
						break;
					}					
				}
			}
		}
	}
	
	public boolean receiveVampireAttack(int harm) {
		int vida= this.getVida();
		vida = vida - harm;
		this.setVida(vida);		
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		this.setVida(0);
		return true;
	}
	
	@Override
	public void move(boolean ocupado) { // los slayers no se mueven
	}

	@Override
	public String serialize(String serialize) {		
		return serialize + "S;" + posx + ";" + posy + ";" + getVida();
	}
	
}