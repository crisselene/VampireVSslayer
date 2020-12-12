package objetos;

import logic.Game;
import logic.IAttack;

public class Dracula extends Vampiro implements IAttack {

	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 0; 
	public static boolean draculaOnBoard = false; 
	
	public Dracula(int posx, int posy, Game game) {
		super(posx, posy, DEFAULT_VIDA, DEFAULT_CICLOS, game);
	}
	
	public String toString() {
		int vida = this.getVida();
		return "D [" + vida + "]";
	}
	
	@Override
	public void attack() {
		if(isAlive()) {
			IAttack other = game.getAttackableInPosition(this.posx-1, this.posy);
			if (other != null ) {
				boolean recibirDamage = other.receiveDraculaAttack();
				if(recibirDamage== true) {
					if(this.ciclosAvance<2) {
						ciclosAvance++;
					}
				}	
			} 	
		}
	}

	public static boolean isDraculaOnBoard() {
		return draculaOnBoard;
	}
	
	public boolean receiveLightFlash() {//Este no recibe el ataque
		return false;		
	}

	public static void setDraculaOnBoard(boolean draculaOnBoard) {
		Dracula.draculaOnBoard = draculaOnBoard;
	}
	
	
	

}
