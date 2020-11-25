package logic;

public abstract class GameObject {
//tendrá los atributos y métodos básicos para controlar la posición en el tablero y una referencia a la clase Game.
	protected Game game;
	protected GameObjectList list;
	
	
	public interface IAttack {
		void attack();
		default boolean receiveSlayerAttack(int damage) {return false;};
		default boolean receiveVampireAttack(int damage) {return false;};
		default boolean receiveLightFlash() {return false;};
		default boolean receiveGarlicPush() {return false;};
		default boolean receiveDraculaAttack(){return false;};
		}
	
	public abstract void crearObject(int posx,int posy);
	
}
