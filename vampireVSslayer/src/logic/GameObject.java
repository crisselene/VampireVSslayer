package logic;

public abstract class GameObject implements IAttack{
//tendrá los atributos y métodos básicos para controlar la posición en el tablero y una referencia a la clase Game.
	protected Game game;
	protected GameObjectList list;
	protected int vida;
	protected int posx;
	protected int posy;
	//constante de cuanto daño hace el objeto
	protected static int HARM;
	
	
	public abstract void crearObject(GameObject object);
	
}
