package logic;

public abstract class GameObject {
//tendrá los atributos y métodos básicos para controlar la posición en el tablero y una referencia a la clase Game.
	
	public interface IAttack {
		void attack();
		default boolean receiveSlayerAttack(int damage) {return false;};
		default boolean receiveVampireAttack(int damage) {return false;};
		default boolean receiveLightFlash() {return false;};
		default boolean receiveGarlicPush() {return false;};
		default boolean receiveDraculaAttack(){return false;};
		}
}
