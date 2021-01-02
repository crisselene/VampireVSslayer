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
		//Hara que todos los objetos ataquen
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

	/*public int contarVamp() {
		int contV=0;
		boolean cont = false;
		for (GameObject gameObject : gameobjects) {
			cont = gameObject.contarVamp();
			if(cont) contV++; cont=false;
		}
		return contV;
	}*/

	public boolean noHayVenLafila(int fila) {
		boolean filaLibre = true;
		for (GameObject gameObject : gameobjects) {
			 filaLibre = gameObject.noHayVenLafila(filaLibre,fila);
			 if(!filaLibre) {
				 return filaLibre;
			 }
		}
		return filaLibre;
	}

	public GameObject getAttackableInPosition(int posx, int posy) {
		for (GameObject gameObject : gameobjects) {
			if(gameObject.getposx()==posx && gameObject.getposy()== posy) {
				return gameObject;
			}
		}
		return null;
		
	}

	public GameObject getAttackableInLine(int posy) {
		for (GameObject gameObject : gameobjects) {
			if(gameObject.getposy() == posy) {
				//da igual el ataque que haga, es solo para ver si le puede atacar 
				if(gameObject.receiveSlayerAttack(0)==true) {
					return gameObject;
				}
				
			}
		}
		return null;
	}

	public void removeDead() {
		for(int i = 0; i < gameobjects.size() ; i++) {//recorrera todos los gameobjects
			GameObject aux = gameobjects.get(i);
			if(!aux.isAlive()) {
				gameobjects.remove(i);
				i--;
			}
		}
		
	}

	public boolean llegoFinal() {
		for(GameObject object: gameobjects) {
			if(object.llegoFinal()) {
				return true;
			}
		}
		return false;
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
		
}
