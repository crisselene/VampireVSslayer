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
		System.out.println("Añadimos slayer " + numSlayers);
	}
	public static void reset() {
		slayerList = new Slayer[MAX_SLAYERS];
		numSlayers = 0;
	}
	
	public static void removeSlayer(int pos) {
		
	}
}
