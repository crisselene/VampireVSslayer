package logic;

import java.util.Random;

import objetos.BloodBank;

public class GameObjectBoard {

	private Level level;
	private GameObjectList obList;
	private int vampRestantes;

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
		if(fila < level.getDimy() && columna < level.getDimx() - 1 &&
				fila >= 0 && columna >= 0) return true;
		return false;
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

	public GameObject getAttackableInLine(int posy) {
		return obList.getAttackableInLine(posy);
	}

	public void removeDead() {
		obList.removeDead();		
	}
	
	public int vampEnTablero() {
		return obList.contarVamp();
	}

	public boolean llegoFinal() {
		return obList.llegoFinal();
	}

	public boolean userVictory() {
		if(vampRestantes == 0 && vampEnTablero() == 0) {
			return true;
		}
		return false;
	}
	
	public int addVampire(int columna, double freq, Random random) {
		
		boolean crear = this.frecuenciaLimiteVamps(freq);
		if(crear) {
			int filaAleatoria = random.nextInt(level.getDimy());
			boolean ocupado = obList.buscarObjeto(filaAleatoria, columna);
			if(!ocupado) {
					vampRestantes--;
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

	public void reducirVampiros() {
		int vamp = this.getVampRestantes();
		vamp--;
		this.setVampRestantes(vamp);
		
	}

}
