package logic;

import java.util.Random;


public class GameObjectBoard {

	private Level level;
	private GameObjectList obList;

	public GameObjectBoard(Level level,GameObjectList obList) {
		this.level = level; 
		this.obList = obList;
	}
	
	public void addVampire(GameObject obj,int filaAleatoria, int columna, Random random) {
		boolean ocupado = obList.buscarObjeto(filaAleatoria, columna);
		if(!ocupado) {
			boolean crear = this.frecuenciaLimiteVamps(random);
			boolean filaLibre = this.noHayVenLafila(filaAleatoria); //FILAAAA****
			if(crear) {
				if(filaLibre) {
					this.addObject(obj);System.out.println("se crea vamp");
				}else System.out.println("NO se crea Vamp");
			}else System.out.println("NO se crea Vamp");
		}
	}

	//comprueba que no haya vampiros en la fila (no puede haber dos vampiros en la misma fila)
	private boolean noHayVenLafila(int fila) {
		boolean filaLibre = obList.noHayVenLafila(fila);
		return filaLibre;
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

	public GameObject getAttackableInPosition(int posx, int posy) {
		GameObject object;
		object = obList.getAttackableInPosition(posx,posy);
		return object;
		
	}

	public void attack() {
		obList.attack(); //TODO ATTACKKKKK****************************
		
	}
}
