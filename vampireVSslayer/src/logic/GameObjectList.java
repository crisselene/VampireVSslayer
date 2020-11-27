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
	
	public void move() {
		for (GameObject gameObject : gameobjects) {
			gameObject.move();
		}
	}

	public int contarVamp() {
		int contV=0;
		boolean cont = false;
		for (GameObject gameObject : gameobjects) {
			cont = gameObject.contarVamp();
			if(cont) contV++; cont=false;
		}
		return contV;
	}

	public boolean noHayVenLafila(int fila) {
		boolean filaLibre = true;
		for (GameObject gameObject : gameobjects) {
			 filaLibre = gameObject.noHayVenLafila(filaLibre,fila);
			 if(!filaLibre) {
				 System.out.println("la fila " + fila + " no está libre");
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
		
}
