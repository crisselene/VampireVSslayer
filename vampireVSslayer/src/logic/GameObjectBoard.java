package logic;

import java.util.Random;

public class GameObjectBoard {

	private Level level;
	private GameObjectList obList;
	private int vampRestantes; 
	private int vampEnTablero = 0; //variable gestion de vampiros en tablero

	public GameObjectBoard(Level level) {
		this.level = level; 
		obList = new GameObjectList();
		vampRestantes = level.numberOfVampires();

	}
	
	public int getVampRestantes() {
		return vampRestantes;
	}
	public void setVampRestantes(int restantes) {
		vampRestantes = restantes;
	}
	public int getVampEnTablero() {
		return vampEnTablero;
	}

	public void setVampEnTablero(int vampEnTablero) {
		this.vampEnTablero = vampEnTablero;
	}

	public void addObject(GameObject obj) {
		obList.anadirObjeto(obj);
		
	}

	public boolean frecuenciaLimiteVamps(double random) {
		boolean crear=false;
		double levelFreq = level.getVampireFrequency();
		
		if(vampRestantes > 0) 
			if (random <= levelFreq ) crear= true;
		return crear;
	}

	public GameObject getAttackableInPosition(int posx, int posy) {
		GameObject object;
		object = obList.getAttackableInPosition(posx,posy);
		return object;
		
	}

	public boolean dentroTablero(int fila, int columna) {
		return(fila < level.getDimy() && columna < level.getDimx() &&
				fila >= 0 && columna >= 0); 
	}

	public void attack() {
		obList.attack();
		
	}

	public void move() {
		obList.move();
		
	}

	public String toString(int x, int y) {
		return obList.toString(x, y);
	}

	public void removeDead() {
		obList.removeDead();		
	}

	public boolean userVictory() {
		return (vampRestantes == 0 && vampEnTablero == 0);
	}
	
	public int addVampire(int columna, double freq, Random random) {
		
		boolean crear = this.frecuenciaLimiteVamps(freq);
		if(crear) {
			int filaAleatoria = random.nextInt(level.getDimy());
			boolean ocupado = obList.buscarObjeto(filaAleatoria, columna);
			if(!ocupado) {
					//vampRestantes--;
					this.reducirVampirosRestantes();
					this.aumentarVampirosTablero();
					return filaAleatoria;					
			}
		}	
		return -1;//Cuando no se puede añadir devolvemos -1
	}

	public void pushVampires() {
		obList.pushVampires();
		
	}

	public boolean buscarObjeto(int posx, int posy) {
		return obList.buscarObjeto(posy, posx);
	}

	public void lightVampires() {
		obList.lightVampires();
	}

	public void reducirVampirosRestantes() {
		vampRestantes--;	
	}

	public void aumentarVampirosTablero() {
		vampEnTablero++;
	}

	public void reducirVampirosTablero() {
		vampEnTablero--;
	}

	public String serializeList() {
		return obList.serialize();
	}

	
	


}
