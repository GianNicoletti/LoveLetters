package cartas;

import java.util.Scanner;

import jugador.Jugador;

public class Guardia extends Carta {

	public Guardia() {
		super("Guardia", 1);
	}

	@Override
	public void descartar(Jugador jugador) {
		Jugador otro = jugador.seleccionarOtroJugador();
		System.out.println("Escribe el nombre de un tipo de carta (Excepto Guardia):");
		try (Scanner teclado = new Scanner(System.in)) {
			//verificarCarta(otro, teclado.nextLine());
		}
	}

	public void verificarCarta(Jugador otro, String nombreCarta) {
		if (otro.tiene(nombreCarta))
			otro.salirDeRonda();
	}
}
