package view;

import logic.Game;
import logic.VampireList;
import objetos.Vampiro;
import utils.MyStringUtils;

public class GamePrinter {
	
	Game game;
	int numRows; 
	int numCols;
	String[][] board;
	final String space = " ";
	//variable que mira si se han añadido los vampiros
	boolean anadido = false;
	
	public GamePrinter (Game game, int cols, int rows) {
		this.game = game;
		this.numRows = rows;
		this.numCols = cols;
	}
	
	
	private void encodeGame(Game game) {
		Vampiro[] arrayVamp = VampireList.getArrayVamp();
		//si hay algún vampiro lo inserto, si no, no pinto ningún vampiro.
		if(arrayVamp != null) {
		//variable centinela para ver si hemos añadido todos los vampiros
		anadido = false;
		//contador de vampiros que hemos anadido
		int contVamp = 0;
		board = new String[numRows][numCols];
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numCols; j++) {	
					board[i][j]= game.getPositionToString(i,j);
					if(!anadido) {
						//recorro el array de vampiros y si coincide "x" e "y" de alguno con la casilla que estoy rellenando, lo meto en ella
						for (int k = 0; k < (arrayVamp.length); k++) {
							int posxV = arrayVamp[k].getPosx();
							int posyV = arrayVamp[k].getPosy();
							int vidaV = arrayVamp[k].getVida();
							if (posxV == i && posyV == j) {
								board[posxV][posyV] = arrayVamp[k].toString();
								contVamp++;
								//anadido solo será true si se han insertado TODOS los vampiros de la lista
								//es decir, si el vampiro que se ha anadido es el ultimo en la lista
								if(contVamp == arrayVamp.length)anadido = true;
							}else {
								//k = 0;
								anadido = false;
							}
							//TO STRING
						
						}
						
					}
		
			}
		}
		//si no hay que pintar ningún vampiro se pinta el tablero vacio	
	}else {
		pintarTableroVacio(game);
	}
}

//pinta el tablero vacio
	private void pintarTableroVacio(Game game) {
		board = new String[numRows][numCols];
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numCols; j++) {	
					board[i][j]= game.getPositionToString(i,j);
			}
		}
	}
	
	 public String toString() {
		encodeGame(game);
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String intersect = space;
		String vIntersect = space;
		String hIntersect = "-";
		String corner = space;

		String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);

		String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
		String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;

		String margin = MyStringUtils.repeat(space, marginSize);
		String lineEdge = String.format("%n%s%s%n", margin, hEdge);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineEdge);
		for(int i=0; i<numRows; i++) {
		        str.append(margin).append(vDelimiter);
		        for (int j=0; j<numCols; j++)
		            str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
		        if (i != numRows - 1) str.append(lineDelimiter);
		        else str.append(lineEdge);   
		}

		return str.toString();
	    }

	
}

