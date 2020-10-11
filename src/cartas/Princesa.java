package cartas;

import jugador.Jugador;

public class Princesa extends Carta {

	public Princesa() {
		super("Princesa", 8);
	}

	@Override
	public void descartar(Jugador jugador) {
		jugador.salirDeRonda();
	}

}
