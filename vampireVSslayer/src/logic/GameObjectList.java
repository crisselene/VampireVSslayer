package logic;

import java.util.ArrayList;

public class GameObjectList {
	private ArrayList<GameObject> gameobjects;
	
	public GameObjectList() {
		gameobjects = new ArrayList<GameObject>();
	}
	
	public void anadirObjeto(GameObject ob) {
		gameobjects.add(ob);
	}
	
	public void resetList() {
		gameobjects.clear();
	}

	public String toString(int x, int y) {
		for(GameObject ob: gameobjects) {
			if(ob.coincidesPosiciones(x,y)) {
				return ob.toString();
			}
		}
		return " ";
	}
}
