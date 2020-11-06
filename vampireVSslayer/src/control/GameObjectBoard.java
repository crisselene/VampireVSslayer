package control;

import java.util.Random;
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
	private Random random;
	
	
	public GameObjectBoard(long seed) {
		this.player=new Player(seed);
		random = new Random(seed);
	}

	//TODO añadir vampiro
	public void addVampire(Level level,VampireList vampList) {
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
			//Tiene que ser asi, si pones 0.1 sale uno cada ronda, tiene que salir uno con la probabilidad que dice el nivel
			//por eso el next double, si sale uno cada ronda es imposible matarlos a todos porque no hay monedas suficientes
			double randomN = random.nextDouble(); //random de 0 a 1**********************
			if (randomN <= levelFreq ) {
				//se puede crear el vampiro, buscar fila aleatoria, dentro de la dimension y
				int filaAleatoria = random.nextInt(level.getDimy()); 
			
				//si no hay vampiros, se crea uno
				if(VampireList.getLongitud() == 0) {
					crearVampiro(filaAleatoria);
					anadido = true;
				}else {
					//si el vampiro se intenta poner sobre una casilla donde hay un vampiro, no se crea
					if (!anadido) {
						for (int i = 0; i < VampireList.getLongitud() ; i++) {
							if(filaAleatoria != VampireList.getArrayVamp()[i].getPosy() && anadido== false) {//**********
								crearVampiro(filaAleatoria);
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
		
	private void crearVampiro(int filaAleatoria) {
		//se crea un vampiro en una fila aleatoria, en la ultima columna, con 5 vidas , los ciclos a 0 y sin atacar
		Vampiro vampiro = new Vampiro(filaAleatoria,7,5,0,false);
		boolean creado = VampireList.addVampire(vampiro);
		if(creado) System.out.println("Se crea un vampiro en la fila " + filaAleatoria);
		else System.out.println(NO_CREAR_VAMP);
			
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
				if(slayerAtacante.getPosy() == vampirelist[j].getPosy() && slayerAtacante.getPosx() < vampirelist[j].getPosx()) {
					//en la primera ejecucion guardamos el vampiro como vampiro atacado
					if (vampiroAtacado == null) {
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
	//
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
				//si el slayer está en la fila siguiente al vampiro:
				if (PosXSlay == (posXVamp-1) && PosYSlay== posYVamp) {
					//el vampiro no avanza, ataca
					vampireList.getArrayVamp()[i].setAtaque(true);
					//posXVamp++;
					//vampireList.getArrayVamp()[i].setPosx(posXVamp);
					
					//el vampiro ataca
					//el slayer pierde una vida
					int vidaS = slayerList.getSlayer(j).getVida();
					vidaS--;
					slayerList.getSlayer(j).setVida(vidaS);
					System.out.println("Un vampiro ha mordido a un slayer");
				}
			}
		}
	}
	
	public void avanzarVampireOAtacar(VampireList vampireList, SlayerList slayerList) {
		//si delante hay un slayer, no avanza,ataca
		for (int i = 0; i < vampireList.getLongitud(); i++) {
			for (int j = 0; j < slayerList.getNumSlayers(); j++) {
				int posYVamp = vampireList.getArrayVamp()[i].getPosy();
				//si el slayer está en la fila siguiente al vampiro:
				if (slayerList.getSlayer(j).getPosy() == (posYVamp-1) ) {
					
				}
			}
		}
	}

	
}
