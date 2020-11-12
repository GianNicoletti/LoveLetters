package cartas;

import java.util.Scanner;

import jugador.Jugador;

public class Guardia extends Carta {

	public Guardia() {
		super("Guardia", 1);
	}

	@Override
	public void descartar(Jugador jugador) {
		super.descartar(jugador);
		Jugador otro = jugador.seleccionarOtroJugador(false);
		if(otro==null)
			return;
		System.out.println("Escribe el nombre de un tipo de carta (Excepto Guardia):");
		String nombreCarta = jugador.adivinarCarta();
		System.out.println("Nombre :" + nombreCarta);
		verificarCarta(otro, nombreCarta);
	}

	public void verificarCarta(Jugador otro, String nombreCarta) {
		if (otro.tiene(nombreCarta))
			otro.salirDeRonda();
	}
}
