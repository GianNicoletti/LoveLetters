package cartas;

import jugador.Jugador;

public class Rey extends Carta {

	public Rey() {
		super("Rey", 6);
	}

	@Override
	public void descartar(Jugador jugador) {
		Jugador otro = jugador.seleccionarOtroJugador();
		jugador.intercambiarMano(otro);
	}

}
