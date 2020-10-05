package cartas;

import java.util.Scanner;

import jugador.Jugador;
import sala.Sala;

public class Baron extends Carta {
	private Jugador jugador;

	public Baron() {
		super("Baron", 3);
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void descartar(Sala sala) {
		Jugador jugador;
		int i;
		try (Scanner teclado = new Scanner(System.in)) {
			System.out.println("Elige un jugador (escribe el numero del jugador): ");
			sala.listarJugadores();
			i = Integer.valueOf(teclado.nextLine());
		}
		jugador = sala.getJugadorPorIndice(i);
		if (jugador.getCarta1().getFuerza() < this.jugador.getCarta1().getFuerza())
			sala.sacarDeRonda(i);
		else
			sala.sacarDeRonda(this.jugador);
	}

}
