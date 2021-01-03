package objetos;

import logic.Game;
import logic.GameObject;
import logic.IAttack;

public class BloodBank extends GameObject implements IAttack{

	public final static int DEFAULT_VIDA = 1;
	private int posx;
	private int posy;
	private int z;
	private Game game;
	
	public BloodBank(int posx, int posy, int z, Game game) {
		super(posx, posy, DEFAULT_VIDA, game);
		this.posx=posx;
		this.posy=posy;
		this.game=game;
		this.z=z;
		
	}
	
	@Override
	public String toString() {
		int banco = this.z;
		return "B ["+ banco +"]";
	}

	@Override
	public void attack() {}//No ataca
	
	//Es fulminado por cualquier ataque
	public boolean receiveVampireAttack(int harm) {
		this.setVida(0);	
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		this.setVida(0);
		return true;
	}

	@Override
	public void move(boolean ocupado) {
		//Usamos el move para usar el banco, pero sin utilizar la variable ocupado que es la usada para los vampiros
		if(isAlive()) {//Si esta vivo
			int ganancia = (int) (z * 0.1);
			game.reintegroBanco(ganancia);
		}
	}

	@Override
	public boolean noHayVenLafila(boolean filaLibre,int fila) {return filaLibre;}//No es un vampiro	

//	@Override
//	public boolean llegoFinal() {return false;} //No es un vampiro	

}
