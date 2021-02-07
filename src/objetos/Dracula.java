package objetos;

import logic.Game;
import logic.IAttack;

public class Dracula extends Vampiro implements IAttack {

	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 1; 
	public static boolean draculaOnBoard = false;
	
	public Dracula(int posx, int posy, Game game) {
		super(posx, posy, DEFAULT_VIDA, DEFAULT_CICLOS, game);
		this.letra="D ";
		Dracula.draculaOnBoard=true;
	}
	
	@Override
	public void attack() {
		if(isAlive()) {
			IAttack other = game.getAttackableInPosition(this.posx-1, this.posy);
			if (other != null ) {
				other.receiveDraculaAttack();
			} 	
		}
	}
	
	public boolean receiveSlayerAttack(int damage) {
		boolean atacado = super.receiveSlayerAttack(damage);
		//En caso de que muera el dracula puede resucitar
		if(!isAlive()) {
			draculaOnBoard=false;
		}
		return atacado;
		
	}
	
	public  boolean receiveGarlicPush() {
		boolean pusheado = super.receiveGarlicPush();
		if(!isAlive()) {	
			draculaOnBoard=false;
		}
		return pusheado;
	}
	
	public boolean receiveLightFlash() {//Dracula no recibe el ataque
		return false;		
	}
	
	@Override
	public String serialize(String serialize) {
		return serialize + "D;" + posx + ";" + posy + ";" + getVida() + ";" + ciclosAvance;	
	}
	
	
	

}
