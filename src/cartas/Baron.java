package cartas;

import jugador.Jugador;

public class Baron extends Carta {

	public Baron() {
		super("Baron", 3);
	}

	@Override
	public void descartar(Jugador jugador) {
		super.descartar(jugador);
		Jugador otro = jugador.seleccionarOtroJugador(false);
		if(otro==null)
			return;
		eliminarCorrespondiente(jugador, otro);
	}

	public void eliminarCorrespondiente(Jugador jugador, Jugador otro) {
		if (otro.getCarta().getFuerza() < jugador.getCarta().getFuerza())
			otro.salirDeRonda();
		else
			jugador.salirDeRonda();
	}
}
