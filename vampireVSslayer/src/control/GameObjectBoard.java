package control;

import java.util.Random;

import logic.Game;
import logic.Level;
import logic.SlayerList;
import logic.VampireList;
import objetos.Player;
import objetos.Slayer;
import objetos.Vampiro;

public class GameObjectBoard {

	static final String NO_CREAR_VAMP = "NO se crea vampiro esta ronda";
	
	private Player player;
	private SlayerList slayerlist;
	private Game game;
	
	
	public GameObjectBoard() {
		this.player=new Player();
	}

	public void addVampire(Level level,VampireList vampList, Random random) {
		boolean anadido = false; //controlar que solo se cree un vampiro por ronda
		//recojo el valor de la frecuencia de los vampiros 
		double levelFreq = level.getVampireFrequency();
		//el numero de vampiros que se pueden crear
		double numVamp = level.numberOfVampires();
		//numero de vampiros en la partida
		double nVampJuego = VampireList.getVampSalidos(); 
		
		//si el numero de vampiros en partida es menor del que se puede, intentamos crear uno
		if(nVampJuego<numVamp) {
			//probabilidad de crear un vampiro
			double randomN = random.nextDouble();
			if (randomN <= levelFreq ) {
				//se puede crear el vampiro, buscar fila aleatoria, dentro de la dimension y
				int filaAleatoria = random.nextInt(level.getDimy()); 
			
				//si no hay vampiros, se crea uno
				if(VampireList.getLongitud() == 0) {
					crearVampiro(filaAleatoria, level.getDimx() - 1);
					anadido = true;
				}else {
					//si el vampiro se intenta poner sobre una casilla donde hay un vampiro, no se crea
					if (!anadido) {
						for (int i = 0; i < VampireList.getLongitud() ; i++) {
							if(filaAleatoria != VampireList.getArrayVamp()[i].getPosy() && anadido== false) {
								crearVampiro(filaAleatoria, level.getDimx() - 1);
								anadido = true;
							} 
					    }if (!anadido){
							System.out.println(NO_CREAR_VAMP + " porque se ha intentado meter en la fila " + filaAleatoria );
							anadido = true;
					    }
					}		
				}
			}
		}
	}
		
	private void crearVampiro(int filaAleatoria, int longitudX) {
		//se crea un vampiro en una fila aleatoria, en la ultima columna, con 5 vidas , los ciclos a 0 y sin atacar
		Vampiro vampiro = new Vampiro(filaAleatoria, longitudX,5,0,false);
		game.addVampire(vampiro);			
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
	
	public boolean addSlayer(int posx, int posy)
	{
		boolean anadido= false;
		//Si el jugador tiene monedas suficientes
		if(player.getMonedas() >= 50) {
			//Restamos 50
			player.setMonedas(player.getMonedas()-50);
			//Añadimos el vampiro a la lista
			crearSlayer(posx,posy);
			anadido = true;
		}
		else //En caso contrario
		{
			//Mostrar que no tiene monedas suficientes
			System.out.println("No de dispone de suficientes monedas");
		}
		return anadido;
	}
	
	public void crearSlayer(int posx, int posy) {
		Slayer slayer = new Slayer(posx, posy);
		SlayerList.addSlayer(slayer);
	}

	public void recibeMonedas(Random random) {
		player.ganaMonedas(random.nextFloat());		
	}

	public void reset() {
		player = new Player();
		SlayerList.reset();
	}

	//Ataque de los slayers
	public void slayerAttack() {
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
				if(slayerAtacante.getPosy() == vampirelist[j].getPosy()) {
					//en la primera ejecucion guardamos el vampiro como vampiro atacado
					if (vampiroAtacado == null) {
						vampiroAtacado=vampirelist[j];
						posVamp = j;
					}
					else {//En caso contrario atacamos al que este a la izq o delante
						if(vampirelist[j].getPosx() <= vampiroAtacado.getPosx()) {
							vampiroAtacado=vampirelist[j];
							posVamp = j;
						}
					}
				}				
				j++;
			}
			//Si tiene a quien atacar
			if (vampiroAtacado != null) {
				//El slayer ataca
				vampiroAtacado.setVida(vampiroAtacado.getVida() - 1);
				VampireList.getArrayVamp()[posVamp] = vampiroAtacado;
			}
		}
		
	}
	
	//Busca los slayer muertos
	public void buscarSlayers() {
		
		for(int i = 0; i<SlayerList.getNumSlayers(); i++) {
			//si no tiene vida lo eliminamos
			if(SlayerList.getSlayer(i).getVida()==0) {
				SlayerList.removeSlayer(i);
			}
		}
		
	}
	
	//si delante de un vampiro hay un slayer, le quita una vida y no avanza
	public void VampireAttack(VampireList vampireList, SlayerList slayerList) {
		//variable que controla si un vampiro ataca o no
		//la iniciamos como -1 (el vampiro no ataca)
		
		
		for (int j = 0; j < slayerList.getNumSlayers(); j++) {
			for (int i = 0; i < vampireList.getLongitud(); i++) {
				int posXVamp = vampireList.getArrayVamp()[i].getPosx();
				int posYVamp = vampireList.getArrayVamp()[i].getPosy();
				int PosXSlay = slayerList.getSlayer(j).getPosx();
				int PosYSlay = slayerList.getSlayer(j).getPosy();
				//solo ataca si no le ha matado ya el slayer
				if(vampireList.getArrayVamp()[i].getVida() != 0){
					//si el slayer está en la fila siguiente al vampiro:
					if (PosXSlay == (posXVamp-1) && PosYSlay== posYVamp) {
						//el vampiro no avanza, ataca
						vampireList.getArrayVamp()[i].setAtaque(true);
									
						//el vampiro ataca
						//el slayer pierde una vida
						int vidaS = slayerList.getSlayer(j).getVida();
						vidaS--;
						slayerList.getSlayer(j).setVida(vidaS);
						//si el vampiro mata al slayer, sigue avanzando
						if (vidaS == 0) {
							//para de atacar
							vampireList.getArrayVamp()[i].setAtaque(false);
						}
					
					}
				}
			}
		}
	}
	
}
