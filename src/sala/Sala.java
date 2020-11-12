package sala;

import java.util.Iterator;
import java.util.LinkedList;

import UI.MainContainer;
import UI.MainWindow;
import cartas.*;
import jugador.Jugador;

public class Sala {
	private LinkedList<Jugador> jugadores;
	private Mazo mazo;
	private Jugador admin;
	private int simbParaGanar;
	private MainWindow window;

	public Sala(Jugador admin, int simbParaGanar) {
		jugadores = new LinkedList<Jugador>();
		jugadores.add(admin);
		this.admin = admin;
		admin.setSala(this);
		this.simbParaGanar = simbParaGanar;
	}

	public void agregarJugador(Jugador nuevo) {
		jugadores.add(nuevo);
		nuevo.setSala(this);
	}

	public void listarJugadores() {
		int i = 1;
		for (Jugador jugador : jugadores) {
			if (jugador.juegaRonda())
				System.out.println(i++ + " - " + jugador.getNombre());
		}
	}

	public LinkedList<Jugador> getJugadores() {
		return jugadores;
	}

	public void empezarJuego() {
		if (jugadores.size() < 2 || jugadores.size() > 4) {
			System.out.println("La cantidad de jugadores debe estar entre 2 y 4");
			return;
		}
		System.out.println("arranca");
		MainContainer container = new MainContainer(this);
		container.setVisible(true);
		window = container.getContentPane();
		while (!this.buscarGanador() && jugadores.size() > 1) {
			this.generarMazo();
			this.jugarRonda();
		}
	}

	public void jugarRonda() {
		int i = 0;
		Jugador jugador;
		mazo.sacarCartaAlAzar();
		this.iniciarCantDescartadas();
		this.repartir();
		while (mazo.size() > 0 && this.jugadoresEnRonda() > 1) {
			window.actualizar(i);
			if (i >= jugadores.size())
				i = 0;
			jugador = jugadores.get(i);
			if (jugador.juegaRonda()) {
				jugador.jugar(window);
				window.cambiarTurno();
				window.actualizar(i);
			}
			i++;
			if (i == jugadores.size())
				i = 0;
		}
		jugador = this.buscarGanadorRonda();
		System.out.println("Ganador" + jugador.getNombre());
		jugador.incPuntaje();
		rehabilitarJugadores();
	}

	private void rehabilitarJugadores() {
		for (Jugador jugador : jugadores) {
			jugador.entrarEnRonda();
		}
	}

	public void repartir() {
		for (Jugador jugador : jugadores) {
			jugador.setCarta(mazo.getCarta());
		}
	}

	public void iniciarCantDescartadas() {
		for (Jugador jugador : jugadores) {
			jugador.setCantDescartadas(0);
		}
	}

	public int jugadoresEnRonda() {
		int cont = 0;
		for (Jugador jugador : jugadores) {
			if (jugador!=null && jugador.juegaRonda())
				cont++;
		}
		return cont;
	}

	public Jugador buscarGanadorRonda() {

		Jugador ganador = jugadores.getFirst();

		for (Jugador jugador : jugadores) {
			/*
			 * agrego la validacion de que este jugador no este descalificado, ya que si fue
			 * descaliicado no deberia si quiera compararse contra el actual ganador.
			 */
			if (jugador.juegaRonda()) {

				/*
				 * Es posible que el jugador que elegí como ganador inicialmente este
				 * descalificado por lo que pregunto esto para evitar un posible bug, donde el
				 * primero sea el ganador pero haya sido descalificado.
				 */
				if (jugador.getCarta().getFuerza() > ganador.getCarta().getFuerza() || !ganador.juegaRonda())
					ganador = jugador;
				else if (jugador.getCarta().getFuerza() == ganador.getCarta().getFuerza())
					if (jugador.getCantDescartadas() > ganador.getCantDescartadas())
						ganador = jugador;
			}

		}
		return ganador;
	}

	public void generarMazo() {
		for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
			iterator.next().setCarta(null);
		}
		mazo = new Mazo();
		mazo.mezclar();
	}

	public Carta getCarta() {
		return mazo.getCarta();
	}

	public boolean buscarGanador() {
		for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
			if (iterator.next().getPuntaje() >= simbParaGanar)
				return true;
		}
		return false;
	}

	public Jugador getGanador() {
		for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
			Jugador jugador = iterator.next();
			if (jugador.getPuntaje() >= simbParaGanar)
				return jugador;
		}
		return null;
	}

	public Jugador getJugadorPorIndice(int i) {
		return jugadores.get(i);
	}

	public void eliminar(Jugador jugador) {
		for(int i=0;i<jugadores.size();i++)
			if(jugadores.get(i)==jugador)
				jugadores.set(i, null);
	}

	public int getSimbParaGanar() {
		return simbParaGanar;
	}

	public int getTamanioMazo() {
		return mazo.size();
	}
}
