package logic;

public abstract class GameObject implements IAttack{
//tendrá los atributos y métodos básicos para controlar la posición en el tablero y una referencia a la clase Game.
	protected Game game;
	protected GameObjectList list;
	private int vida;
	protected int posx;
	protected int posy;
	protected String letra;
		
	public GameObject(int posx, int posy, int vida, Game game) {
		this.posx = posx;
		this.posy = posy;
		this.vida=vida;
		this.game = game;
	}
	
	public String toString() {
		return letra + "["+ vida +"]";
	}

	protected void setVida(int vida) {
		this.vida = vida;
	}
	
	public int getVida() {
		return vida;
	}

	protected void setPosX(int posx) {
		this.posx = posx;
	}
	
	protected int getposx() {
		return posx;
	}
	
	protected void setPosY(int posy) {
		this.posy = posy;
	}
	
	public int getposy() {
		return posy;
	}
	
	protected boolean isAlive() {
		if(this.getVida()>0) return true;
		else return false;
	}
	

	//constante de cuanto daño hace el objeto(hasta ahora es 1 para ambos)
	protected static int HARM = 1;

	public boolean coincidesPosiciones(int x, int y) {
		if (posx == x && posy == y) {
			return true;
		}
		return false;
	}
	
	public abstract void move(boolean ocupado);
	//public abstract boolean contarVamp();
	public abstract boolean noHayVenLafila(boolean crear, int fila);
	public abstract boolean llegoFinal();

	
}
