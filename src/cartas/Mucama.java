package cartas;

import jugador.Jugador;
import sala.Sala;

public class Mucama extends Carta {

	private Jugador jugador;

	public Mucama() {
		super("Mucama", 4);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void descartar(Sala sala) {
		jugador.setProtegido(true);
	}

}
