package objetos;

import logic.Game;
import logic.IAttack;

public class Dracula extends Vampiro implements IAttack {

	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 0; 
	
	public Dracula(int posx, int posy, Game game) {
		super(posx, posy, DEFAULT_VIDA, DEFAULT_CICLOS, game);
		this.letra="D ";
	}
	
	@Override
	public void attack() {
		if(isAlive()) {
			IAttack other = game.getAttackableInPosition(this.posx-1, this.posy);
			if (other != null ) {
				boolean recibirDamage = other.receiveDraculaAttack();
				if(recibirDamage== true) {
					if(this.ciclosAvance<2) {
						//ciclosAvance++;
					}
				}	
			} 	
		}
	}
	
	public boolean receiveSlayerAttack(int damage) {
		int vida= this.getVida();
		vida = vida - damage;
		this.setVida(vida);	
		//En caso de que muera el dracula puede resucitar
		if(!isAlive()) {
			game.setDraculaOnBoard(false);
		}
		return true;
	}
	
	public  boolean receiveGarlicPush() {
		//Si tiene alguien detras no retrocede y si esta al final muere
		if(game.estaAlFinal(posx)) {
			this.setVida(0);
			game.setDraculaOnBoard(false);
			return true;
		}
		else if(!game.buscarObjeto(posx+1, posy)) {
			posx++;
			ciclosAvance = 0;
			this.setPosX(posx);
			return true;
		}
		return false;		
	}
	
	public boolean receiveLightFlash() {//Este no recibe el ataque
		return false;		
	}
	
	
	

}
