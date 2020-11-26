package objetos;

import java.util.Random;

public class Player {
	
	public static final int DEFAULT_MONEDAS = 50;
	public static final int COSTE_SLAYER = 50;
	private int monedas;
	
	public Player() {
		monedas = DEFAULT_MONEDAS;
	}
	
	public int getMonedas() {
		return monedas;
	}
	public void setMonedas(int monedas) {
		this.monedas=monedas;
	}
	
	public void ganaMonedas(float ganaMonedas) {		
		if (ganaMonedas >= 0.5) {
			monedas += 10;
		}
	}

	public boolean tieneMonedas() {
		if (monedas >= 50) return true;
		return false;
	}

	public void restarMonedas() {
		monedas = monedas - COSTE_SLAYER;
		
	}
}