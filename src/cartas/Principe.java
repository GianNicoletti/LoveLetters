package cartas;

import jugador.Jugador;

public class Principe extends Carta {

	public Principe() {
		super("Principe", 5);
	}

	@Override
	public void descartar(Jugador jugador) {
		Jugador otro = jugador.seleccionarOtroJugador();
		otro.cambiarCarta();
	}

}
