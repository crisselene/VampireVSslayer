package logic;

import java.util.ArrayList;

public class GameObjectList {
	private ArrayList<GameObject> gameobjects = new ArrayList<GameObject>();
	
	public void anadirObjeto(GameObject ob) {
		gameobjects.add(ob);
	}
	
	public void resetList() {
		gameobjects.clear();
	}

	public boolean buscarObjeto(int filaAleatoria, int columna) {
		boolean ocupado = false;
		for (GameObject ob : gameobjects) {
			if(ob.posx == columna && ob.posy == filaAleatoria) ocupado = true;
		    else ocupado=false;
		}
		return ocupado;
	}
}
