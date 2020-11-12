package cartas;

import UI.MainWindow;
import jugador.Jugador;

public class Sacerdote extends Carta {

	public Sacerdote() {
		super("Sacerdote", 2);
	}

	@Override
	public void descartar(Jugador jugador) {
		super.descartar(jugador);
		int indice = jugador.seleccionarIndiceOtroJugador();
		if (indice != -1)
			jugador.verCarta(indice);
		// obtenerCarta(otro);

	}

	/*
	 * public void obtenerCarta(Jugador otro) { System.out.println("La carta de " +
	 * otro.getNombre() + " es " + otro.getCarta().getNombre()); // que retorne
	 * string no har�a falta, se agreg� para hacer los tests. }
	 */
}
