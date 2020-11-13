package cartas;

import jugador.Jugador;

public class Rey extends Carta {

	public Rey() {
		super("Rey", 6);
	}

	@Override
	public void descartar(Jugador jugador) {
		super.descartar(jugador);
		Jugador otro = jugador.seleccionarOtroJugador(false,false);
		if(otro==null)
			return;
		hacerIntercambio(jugador, otro);
	}

	public void hacerIntercambio(Jugador jugador, Jugador otro) {
		jugador.intercambiarMano(otro);
	}

}
