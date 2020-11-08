package logic;

import objetos.Slayer;

public class SlayerList {

	public static final int MAX_SLAYERS=32;//El maximo es 32 porque en el level easy hay como mucho 32 espacios
	private static Slayer slayerList[]= new Slayer[MAX_SLAYERS];
	private static int numSlayers = 0;
	
	public static Slayer getSlayer(int slayerNum) {
		return slayerList[slayerNum];
	}
	public static int getNumSlayers() {
		return numSlayers;
	}
	public void setNumSlayers(int numSlayers) {
		this.numSlayers=numSlayers;
	}
	public static void addSlayer(Slayer slayer) {
		slayerList[numSlayers] = slayer;
		numSlayers = numSlayers + 1;
	}
	public static void reset() {
		slayerList = new Slayer[MAX_SLAYERS];
		numSlayers = 0;
	}
	
	public static void removeSlayer(int pos) {
		//Mueve los vampiros desde la posicion indicada hacia la izq para eliminar el muerto
		for(int i = pos; i < numSlayers; i++) {
			slayerList[i] = slayerList[i+1];
		}
		//Eliminamos el slayer
		numSlayers--;
	}
	public static boolean noCoincide(int posx, int posy) {
		boolean coincide = false;
		for(int i = 0; i < numSlayers; i++) {
			if(slayerList[i].getPosx() == posx && slayerList[i].getPosy() == posy) {
				coincide= true;
			}
		}
		//Ahora miramos si coincide con los vampiros
		for(int i = 0; i < VampireList.getLongitud(); i++) {
			if(VampireList.getArrayVamp()[i].getPosx() == posx && VampireList.getArrayVamp()[i].getPosy() == posy) {
				coincide= true;
			}
		}
		return coincide;
	}
}
