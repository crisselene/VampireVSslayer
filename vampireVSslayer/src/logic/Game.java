package logic;

import view.GamePrinter;

public class Game {
	
	private long seed;
	private Level level;
	private GamePrinter printer;
	

	public Game(Long seed, Level level) {
		printer= new GamePrinter(this,7,8);
	}
	public boolean isFinished() {
		return false;
	}
	public String toString() {
		return printer.toString();
	}

	public String getPositionToString(int x, int y) {
		return "-";
	}
}
