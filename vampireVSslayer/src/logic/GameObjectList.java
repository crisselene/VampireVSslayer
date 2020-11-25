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
}
