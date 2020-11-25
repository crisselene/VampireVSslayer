package logic;

public abstract class GameObject implements IAttack{
//tendrá los atributos y métodos básicos para controlar la posición en el tablero y una referencia a la clase Game.
	protected Game game;
	protected GameObjectList list;
	private int vida;
	protected int posx;
	protected int posy;
	
	
	protected void setVida(int vida) {
		this.vida = vida;
	}
	
	protected int getVida() {
		return vida;
	}

	
	protected boolean isAlive() {
		if(this.getVida()!=0) return true;
		else return false;
	}
	

	//constante de cuanto daño hace el objeto
	protected static int HARM;
	
	
	
}
