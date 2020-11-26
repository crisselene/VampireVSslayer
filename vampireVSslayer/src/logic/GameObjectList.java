package logic;

import java.util.ArrayList;

public class GameObjectList {
	private ArrayList<GameObject> gameobjects;
	private int vampirosSalidos;
	private int vampirosRestantes;
	
	public GameObjectList() {
		gameobjects = new ArrayList<GameObject>();
		vampirosSalidos = 0;
	}
	
	public void anadirObjeto(GameObject ob) {
		gameobjects.add(ob);
	}
	
	public void resetList() {
		gameobjects.clear();
	}

	public boolean buscarObjeto(int fila, int columna) {
		boolean ocupado = false;
		for (GameObject ob : gameobjects) {
			if(ob.posx == columna && ob.posy == fila) ocupado = true;
		    else ocupado=false;
		}
		return ocupado;
	}
	
	public String toString(int x, int y) {
		for(GameObject ob: gameobjects) {
			if(ob.coincidesPosiciones(x,y)) {
				return ob.toString();
			}
		}
		return " ";
	}

	public void attack() {
		//Hara que todos los objetos ataquen
		for(IAttack ob: gameobjects) {			
			for(IAttack other: gameobjects) {
				if(ob != other) {//Si no son el mismo
					ob.attack(other);
				}
			}
		}
		
	}
}
