package objetos;

import java.util.Random;

public class Player {
	
	public static final int DEFAULT_MONEDAS = 50;
	private int monedas;
	Random random;
	
	public Player(long seed) {
		monedas = DEFAULT_MONEDAS;
		random = new Random(seed);
	}
	
	public int getMonedas() {
		return monedas;
	}
	public void setMonedas(int monedas) {
		this.monedas=monedas;
	}
	
	public void ganaMonedas() {
		
		double ganaMonedas = random.nextFloat();
		if (ganaMonedas <= 0.5) {
			monedas += 10;
		}
	}
}