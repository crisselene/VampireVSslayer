package control;

import java.util.Random;

import logic.Game;
import logic.GameObject;
import logic.GameObjectList;
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
	private GameObjectList obList;
	private Level level;
	private Random random; 
	
	
	public GameObjectBoard(Long seed,Level level,GameObjectList obList) {
		this.player=new Player();
		random = new Random(seed);
		this.level = level; 
		this.obList = obList;
	}

	/*public void addVampire(Level level,VampireList vampList, Random random) {
		boolean anadido = false; //controlar que solo se cree un vampiro por ronda
		
		double levelFreq = level.getVampireFrequency();
		double numVamp = level.numberOfVampires();
		//numero de vampiros en la partida******************************
		double nVampJuego = VampireList.getVampSalidos(); 
		
		//si el numero de vampiros en partida es menor del que se puede, intentamos crear uno
		if(nVampJuego<numVamp) {
			//probabilidad de crear un vampiro
			double randomN = random.nextDouble();
			if (randomN <= levelFreq ) {
				//se puede crear el vampiro, buscar fila aleatoria, dentro de la dimension y
				int filaAleatoria = random.nextInt(level.getDimy()); 
				int columna = (level.getDimx() - 1);
			
				//si no hay vampiros, se crea uno*******************
				if(VampireList.getLongitud() == 0) {
					crearVampiro(filaAleatoria, level.getDimx() - 1);
					anadido = true;
				}else {
					//si el vampiro se intenta poner sobre una casilla donde hay un objeto, no se crea
					if (!anadido) {
						boolean ocupado =  obList.buscarObjeto(filaAleatoria,columna);
					//	for (int i = 0; i < VampireList.getLongitud() ; i++) {
						//	if(filaAleatoria != VampireList.getArrayVamp()[i].getPosy() && anadido== false) {
								crearVampiro(filaAleatoria, columna);
								anadido = true;
							} 
					    if (!anadido){
							System.out.println(NO_CREAR_VAMP + " porque se ha intentado meter en la fila " + filaAleatoria );
							anadido = true;
					    }
					//}		
			}
		}
	}*/
		
	private void crearVampiro(int filaAleatoria, int longitudX) {
		//se crea un vampiro en una fila aleatoria, en la ultima columna, con 5 vidas , los ciclos a 0 y sin atacar
		Vampiro vampiro = new Vampiro(filaAleatoria, longitudX,5,0,false);
		game.addObject(vampiro);			
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
		game.addObject(slayer);
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
	
	public void addVampire(GameObject obj,int filaAleatoria, int columna ) {
		boolean ocupado = obList.buscarObjeto(filaAleatoria, columna);
		if(!ocupado) {
			boolean crear = this.frecuenciaLimiteVamps();
			boolean filaOcupada = this.noHayVenLafila(filaAleatoria); //FILAAAA****
			if(crear) {
				if(!filaOcupada) {
					this.addObject(obj);System.out.println("se crea vamp");
				}else System.out.println("NO se crea Vamp");
			}else System.out.println("NO se crea Vamp");
		}
	}

	//comprueba que no haya vampiros en la fila (no puede haber dos vampiros en la misma fila)
	private boolean noHayVenLafila(int fila) {
		boolean crear = obList.noHayVenLafila(fila);
		return crear;
	}

	public void addObject(GameObject obj) {
		obList.anadirObjeto(obj);
		
	}

	public boolean frecuenciaLimiteVamps() {
		boolean crear=false;
		double levelFreq = level.getVampireFrequency();
		double numVamp = level.numberOfVampires();
		double randomN = random.nextDouble();
		int nVampJuego = obList.contarVamp();
		
		if(nVampJuego<numVamp) 
			if (randomN <= levelFreq ) crear= true;
		return crear;
	}
}
