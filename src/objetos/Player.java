package objetos;

public class Player {
	
	public static final int DEFAULT_MONEDAS = 50;
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

	public void restarMonedas(int monedas2) {
		monedas = monedas - monedas2;
		
	}

	public boolean tieneMonedas(int monedas) {
		return(this.monedas >= monedas);
	}

	public void reintegroBanco(int ganancia) {
		monedas = monedas + ganancia;		
	}
}