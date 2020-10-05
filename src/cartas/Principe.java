package cartas;

import java.util.Scanner;

import jugador.Jugador;
import sala.Sala;

public class Principe extends Carta {
	private Jugador jugador;

	public Principe() {
		super("Principe", 5);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void descartar(Sala sala) {
		int i;
		try (Scanner teclado = new Scanner(System.in)) {
			System.out.println("Elige un jugador (escribe el numero del jugador): ");
			sala.listarJugadores();
			i = Integer.valueOf(teclado.nextLine());
		}
		jugador = sala.getJugadorPorIndice(i);
		jugador.cambiarCarta();
	}

}
