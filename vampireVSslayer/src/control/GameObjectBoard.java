package control;

import logic.Level;
import logic.SlayerList;
import logic.VampireList;
import objetos.Player;
import objetos.Slayer;
import objetos.Vampiro;

public class GameObjectBoard {

	private Player player;
	private SlayerList slayerlist;
	
	public GameObjectBoard(long seed) {
		this.player=new Player(seed);
	}

	//TODO añadir vampiro
	public void addVampire(Level level,VampireList vampList) {
		boolean anadido = false; //controlar que solo se cree un vamppiro por ronda
		//recojo el valor de la frecuencia de los vampiros 
		double levelFreq = level.getVampireFrequency();
		//el numero de vampiros que se pueden crear
		double numVamp = level.numberOfVampires();
		//numero de vampiros en la partida
		double nVampJuego = vampList.getLongitud(); 
		
		//si el numero de vampiros en partida es menor del que se puede, intentamos crear uno
		if(nVampJuego<numVamp) {
			//probabilidad de crear un vampiro
			double randomN = 0.1; //random de 0 a 1**************************************************************
			if (randomN <= levelFreq ) {
				//se puede crear el vampiro, buscar fila aleatoria, dentro de la dimension y
				int filaAleatoria = (int) (Math.random()*level.getDimy()-1);
			
				
				//si no hay vampiros, se crea uno
				if(nVampJuego == 0) {
					crearVampiro(filaAleatoria);
					anadido = true;
				}else {
					//si el vampiro se intenta poner sobre una casilla donde hay un vampiro, no se crea
					if (!anadido) {
						for (int i = 0; i < nVampJuego ; i++) {
							if(filaAleatoria != vampList.getArrayVamp()[i].getPosx() && anadido== false) {
								crearVampiro(filaAleatoria);
								anadido = true;
							}
						}
					}else System.out.println("NO se crean vampiros porque se ha intentado meter en la fila " + filaAleatoria );
							
				}
				}
			}else System.out.println("ya están todos los vampiros en el tablero");
				
		}
		
	private void crearVampiro(int filaAleatoria) {
		Vampiro vampiro = new Vampiro(filaAleatoria,7,5);
		VampireList.addVampire(vampiro);
		System.out.println("Se crea un vampiro en la fila " + filaAleatoria);
			
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getMonedas() {
		return player.getMonedas();
	}
	
	public SlayerList getSlayerList() {
		return slayerlist;
	}
	
	public void addSlayer(int posx, int posy)
	{
		//Si el jugador tiene monedas suficientes
		if(player.getMonedas() >= 50) {
			//Restamos 50
			player.setMonedas(player.getMonedas()-50);
			//Añadimos el vampiro a la lista
			crearSlayer(posx,posy);
		}
		else //En caso contrario
		{
			//Mostrar que no tiene monedas suficientes
			System.out.println("No de dispone de suficientes monedas");
		}
	}
	
	public void crearSlayer(int posx, int posy) {
		Slayer slayer = new Slayer(posx, posy);
		SlayerList.addSlayer(slayer);
	}

	public void recibeMonedas() {
		player.ganaMonedas();		
	}

	public void reset(long seed) {
		player = new Player(seed);
		SlayerList.reset();
	}

	//Ataque de los slayers
	public void slayerAtack() {
		for (int i = 0; i< SlayerList.getNumSlayers(); i++) {
			//variables aux
			Slayer slayerAtacante = SlayerList.getSlayer(i);
			Vampiro[] vampirelist = VampireList.getArrayVamp();
			Vampiro vampiroAtacado = null;//Vampiro al que se atacara
			int posVamp = 0;//Guarda la posicion a la que se ataca
			//Comprobamos si coincide con algun vampiro
			int j = 0;
			while (j < VampireList.getLongitud()) {
				//Si esta justo delante
				if(slayerAtacante.getPosx() == vampirelist[j].getPosx() && slayerAtacante.getPosy() < vampirelist[j].getPosy()) {
					System.out.println("f");
					//en la primera ejecucion guardamos el vampiro como vampiro atacado
					if (j == 0) {
						vampiroAtacado=vampirelist[j];
						posVamp = j;
					}
					else {//En caso contrario atacamos al que este delante
						if(vampirelist[j].getPosx() <= vampiroAtacado.getPosx()) {
							vampiroAtacado=vampirelist[j];
							posVamp = j;
						}
					}
				}				
				j++;
			}//while
			//Si tiene a quien atacar
			if (vampiroAtacado != null) {
				//El slayer ataca
				vampiroAtacado.setVida(vampiroAtacado.getVida() - 1);
				VampireList.getArrayVamp()[posVamp] = vampiroAtacado;
			}
		}//for
		
	}
	
}
