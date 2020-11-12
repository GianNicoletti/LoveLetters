package cartas;

import jugador.Jugador;

public class Mucama extends Carta {

	public Mucama() {
		super("Mucama", 4);
	}

	@Override
	public void descartar(Jugador jugador) {
		super.descartar(jugador);
		jugador.setProtegido(true);
	}

}
