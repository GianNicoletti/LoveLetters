package cartas;

import jugador.Jugador;

public class Principe extends Carta {

	public Principe() {
		super("Principe", 5);
	}

	@Override
	public void descartar(Jugador jugador) {
		super.descartar(jugador);
		Jugador otro = jugador.seleccionarOtroJugador(true,false);
		if(otro==null)
			return;
		cambiarCarta(otro);
	}
	
	public void cambiarCarta(Jugador otro) {
		otro.cambiarCarta();
	}

}
