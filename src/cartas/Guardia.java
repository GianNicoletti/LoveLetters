package cartas;

import java.util.Scanner;

import jugador.Jugador;
import sala.Sala;

public class Guardia extends Carta {
	private Jugador jugador;

	public Guardia() {
		super("Guardia", 1);
	}

	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void descartar(Sala sala) {
		Jugador jugador = null;
		int indice;
		try (Scanner teclado = new Scanner(System.in)) {
			do {
				if (jugador != null) {
					System.out.println("No puedes elegirte a ti mismo.");
				}
				System.out.println("Elige un jugador (escribe el numero del jugador): ");
				sala.listarJugadores();
				jugador = sala.getJugadorPorIndice(indice = Integer.valueOf(teclado.nextLine()));
			} while (!jugador.equals(this.jugador));

			System.out.println("Escribe el nombre de un tipo de carta (Excepto Guardia):");

			if (jugador.tiene(teclado.nextLine()))
				sala.sacarDeRonda(indice);
		}
	}

}
