package objetos;

import logic.Game;
import logic.IAttack;

public class ExplosiveVampire extends Vampiro implements IAttack {

	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 1;
	private final static int ATAQUE = 1;
	
	public ExplosiveVampire(int posx, int posy, Game game) {
		super(posx, posy, DEFAULT_VIDA , DEFAULT_CICLOS, game);	
		this.letra = "EV ";
	}
	
	public void explotar() {
		for (int i = posx-1; i <= posx+1 ; i++) {
			for (int j= posy-1; j<= posy+1 ; j++) {
				IAttack other = game.getAttackableInPosition(i, j);				
				if (other != null) {
					if( i!= posx || j!= posy) {
						other.receiveSlayerAttack(ATAQUE);
					}
				}
			}	
		} 
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		boolean atacado = super.receiveSlayerAttack(damage);
		if(!isAlive()) {
			this.explotar();
		}
		return atacado;		
	}
	
	@Override
	public String serialize(String serialize) {
		return serialize + "EV;" + posx + ";" + posy + ";" + getVida() + ";" + ciclosAvance;	
	}
}
