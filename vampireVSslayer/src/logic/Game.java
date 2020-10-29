package logic;

import view.GamePrinter;

public class Game {
	
	private long seed;
	private Level level;
	private GamePrinter printer;
	

	public Game(Long seed, Level level) {
		printer= new GamePrinter(this,level.getDimx(),level.getDimy());
		this.level=level;
		this.seed = seed;
	}
	
	public boolean isFinished() {
		return false;
	}
	
	public String toString() {
		return printer.toString();
	}

	public String getPositionToString(int x, int y) {
		//Si queremos un slayer
		for (int i = 0 ; i < SlayerList.getNumSlayers() ; i++)
		{
			if (SlayerList.getSlayer(i).getPosx()== x && SlayerList.getSlayer(i).getPosy()== y) {
				return SlayerList.getSlayer(i).toString();
			}
		}
		return "-";
	}
	public int getLevelDimX() {
		return level.getDimx();
	}
	public int getLevelDimY() {
		return level.getDimy();
	}
	
	public long getSeed() {
		return seed;
	}
}
