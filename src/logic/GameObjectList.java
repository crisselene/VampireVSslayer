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

	public boolean buscarObjeto(int fila, int columna) {
		
		for (GameObject ob : gameobjects) {
			if(ob.getposx() == columna && ob.getposy() == fila) return true;
		}
		return false;

	}
	
	public String toString(int x, int y) {
		for(GameObject ob: gameobjects) {
			if(ob.coincidesPosiciones(x,y)) {
				return ob.toString();
			}
		}
		return "";
	}

	public void attack() {
		for(IAttack ob: gameobjects) {			
			ob.attack();
		}	
	}
	
	public void move() {
		for (GameObject gameObject : gameobjects) {
			boolean ocupado = buscarObjeto(gameObject.posy,gameObject.posx-1);
			gameObject.move(ocupado);
		}
	}

	public GameObject getAttackableInPosition(int posx, int posy) {
		for (GameObject gameObject : gameobjects) {
			if(gameObject.getposx()==posx && gameObject.getposy()== posy) {
				return gameObject;
			}
		}
		return null;
		
	}

	public void removeDead() {
		for(int i = 0; i < gameobjects.size() ; i++) {
			GameObject aux = gameobjects.get(i);
			if(!aux.isAlive()) {
				gameobjects.remove(i);
				i--;
			}
		}
		
	}

	public void pushVampires() {
		for(GameObject objeto : gameobjects) {
			objeto.receiveGarlicPush();
		}
		
	}

	public void lightVampires() {
		for(GameObject objeto : gameobjects) {
			objeto.receiveLightFlash();
		}
		
	}

	public String serialize() {
		String serialize = "";
		for(GameObject objeto : gameobjects) {
			serialize = objeto.serialize(serialize) + "\n";
		}
		return serialize;
	}
		
}
