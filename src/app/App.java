package app;

import UI.MainWindow;
import jugador.Jugador;
import sala.Sala;

public class App {
	public static void main(String[] args) {
		Jugador carlos = new Jugador("Carlos-1");
		Sala sala = new Sala(carlos, 3);
		sala.agregarJugador(new Jugador("Roberto-2"));
		sala.agregarJugador(new Jugador("Hernesto-3"));
		sala.agregarJugador(new Jugador("Ricardo-4"));
		sala.empezarJuego();
	}
}