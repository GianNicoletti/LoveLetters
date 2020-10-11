package cartas;

import jugador.Jugador;

public class Baron extends Carta {

	public Baron() {
		super("Baron", 3);
	}

	@Override
	public void descartar(Jugador jugador) {
		Jugador otro = jugador.seleccionarOtroJugador();
		eliminarCorrespondiente(jugador, otro);
	}

	public void eliminarCorrespondiente(Jugador jugador, Jugador otro) {
		if (otro.getCarta().getFuerza() < jugador.getCarta().getFuerza())
			otro.salirDeRonda();
		else
			jugador.salirDeRonda();
	}
}
