
package objetos;

import logic.GameObject;
import logic.GameObjectList;

public class Slayer extends GameObject{

	public final static int DEFAULT_VIDA = 3;
	private int posx;
	private int posy;
	//private int vida;
	//private GameObjectList list;
	

	public Slayer(int posx, int posy) { //Constructor solo con posx y posy
		this(posx, posy, DEFAULT_VIDA);
	}
	public Slayer(int posx, int posy, int vida) { //Constructor completo
		super(posx, posy, vida);
	}
	
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	
	
	/*public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
	this.vida = vida;
	}*/
	
	public String toString() {
		int vida = this.getVida();
		return "S ["+ vida +"]";
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	/*//TODO: cuando se cree Slayer pasar  posx y posy a este método 
	@Override
	public void crearObject(int posx, int posy) {
		Slayer s = new Slayer(posx, posy);
		list.anadirObjeto(s);
	}*/
	
}