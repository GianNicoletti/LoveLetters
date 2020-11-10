package cartas;

import jugador.Jugador;

public class Sacerdote extends Carta {

	public Sacerdote() {
		super("Sacerdote", 2);
	}

	@Override
	public void descartar(Jugador jugador) {
		Jugador otro = jugador.seleccionarOtroJugador(false);
		obtenerCarta(otro);
	}
	
	public String obtenerCarta(Jugador otro) {
		System.out.println("La carta de " + otro.getNombre() + " es " + otro.getCarta().getNombre());
		return otro.getCarta().getNombre();	//que retorne string no har�a falta, se agreg� para hacer los tests.
	}
}
