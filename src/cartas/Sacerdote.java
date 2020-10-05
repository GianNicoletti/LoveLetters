package cartas;

import java.util.Scanner;

import jugador.Jugador;
import sala.Sala;

public class Sacerdote extends Carta {
	private Jugador jugador;

	public Sacerdote() {
		super("Sacerdote", 2);
	}

	@Override
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void descartar(Sala sala) {
		Jugador jugador;
		int i;
		try (Scanner teclado = new Scanner(System.in)) {
			do {
				System.out.println("Elige un jugador (escribe el numero del jugador): ");
				sala.listarJugadores();
				i = Integer.valueOf(teclado.nextLine());
				jugador = sala.getJugadorPorIndice(i);
			} while (jugador.equals(this.jugador));
		}
		System.out.println("La carta de " + jugador.getNombre() + " es " + jugador.getCarta().getNombre());
	}
}
