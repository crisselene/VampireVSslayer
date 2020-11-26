package logic;

public interface IAttack {
	
	void attack(IAttack other);
	default boolean receiveSlayerAttack(int damage, int posy, int posx) {return false;};
	default boolean receiveVampireAttack(int damage) {return false;};
	default boolean receiveLightFlash() {return false;};
	default boolean receiveGarlicPush() {return false;};
	default boolean receiveDraculaAttack(){return false;};
	
}
