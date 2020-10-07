package cartas;

import jugador.Jugador;

public class Sacerdote extends Carta {

	public Sacerdote() {
		super("Sacerdote", 2);
	}

	@Override
	public void descartar(Jugador jugador) {
		Jugador otro = jugador.seleccionarOtroJugador();
		System.out.println("La carta de " + otro.getNombre() + " es " + otro.getCarta().getNombre());
	}
}
