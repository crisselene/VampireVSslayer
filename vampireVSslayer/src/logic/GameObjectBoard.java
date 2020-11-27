package logic;

import java.util.Random;


public class GameObjectBoard {

	private Level level;
	private GameObjectList obList;

	public GameObjectBoard(Level level) {
		this.level = level; 
		obList = new GameObjectList();
	}
	
	public void addVampire(GameObject obj,int filaAleatoria, int columna, Random random) {
		boolean ocupado = obList.buscarObjeto(filaAleatoria, columna);
		if(!ocupado) {
			boolean crear = this.frecuenciaLimiteVamps(random);
			boolean filaOcupada = this.noHayVenLafila(filaAleatoria); //FILAAAA****
			if(crear) {
				if(!filaOcupada) {
					this.addObject(obj);System.out.println("se crea vamp");
				}else System.out.println("NO se crea Vamp");
			}else System.out.println("NO se crea Vamp");
		}
	}

	//comprueba que no haya vampiros en la fila (no puede haber dos vampiros en la misma fila)
	private boolean noHayVenLafila(int fila) {
		boolean crear = obList.noHayVenLafila(fila);
		return crear;
	}

	public void addObject(GameObject obj) {
		obList.anadirObjeto(obj);
		
	}

	public boolean frecuenciaLimiteVamps(Random random) {
		boolean crear=false;
		double levelFreq = level.getVampireFrequency();
		double numVamp = level.numberOfVampires();
		double randomN = random.nextDouble();
		int nVampJuego = obList.contarVamp();
		
		if(nVampJuego<numVamp) 
			if (randomN <= levelFreq ) crear= true;
		return crear;
	}

	public boolean addSlayer(GameObject objeto, int fila, int columna) {
		if(!obList.buscarObjeto(fila, columna)) {//Si no hay objeto en esta columna
			addObject(objeto);
			return true;
		}
		return false;
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
}
