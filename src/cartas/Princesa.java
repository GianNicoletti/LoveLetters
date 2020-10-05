package cartas;

import jugador.Jugador;
import sala.Sala;

public class Princesa extends Carta {

	private Jugador jugador;

	public Princesa() {
		super("Princesa", 7);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void descartar(Sala sala) {
		sala.sacarDeRonda(jugador);
	}

}
