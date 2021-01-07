package objetos;

import logic.Game;
import logic.IAttack;

public class Dracula extends Vampiro implements IAttack {

	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 0; 
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
		int vida= this.getVida();
		vida = vida - damage;
		this.setVida(vida);	
		//En caso de que muera el dracula puede resucitar
		if(!isAlive()) {
			draculaOnBoard=false;
			this.morir();
		}
		return true;
	}
	
	public  boolean receiveGarlicPush() {
		//Si tiene alguien detras no retrocede y si esta al final muere
		//if(game.estaAlFinal(posx)) { *****************************************+NECESARIO??************ 
		if(game.getLevelDimX()-1 == posx) {	
			this.morir();
			draculaOnBoard=false;
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
	
	@Override
	public String serialize(String serialize) {
		return serialize + "D;" + posx + ";" + posy + ";" + getVida() + ";" + ciclosAvance;	
	}
	
	
	

}
