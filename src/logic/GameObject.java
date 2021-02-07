package logic;

public abstract class GameObject implements IAttack{

	protected Game game;
	private int vida;
	protected int posx;
	protected int posy;
	protected String letra;
	//constante de cuanto daño hace el objeto(hasta ahora es 1 para ambos)
	protected static int HARM = 1;
		
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
	
	public boolean coincidesPosiciones(int x, int y) {
		if (posx == x && posy == y) {
			return true;
		}
		return false;
	}
	
	public abstract void move(boolean ocupado);

	public abstract String serialize(String serialize);

	
}
