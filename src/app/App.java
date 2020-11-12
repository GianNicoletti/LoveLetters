package app;

import UI.*;
import jugador.Jugador;
import sala.Sala;

public class App {
	public static void main(String[] args) {
		//Jugador carlos = new Jugador("Carlos-1");
		
		//LoginWindowOffline win=new LoginWindowOffline();
		//win.setVisible(true);
		
		Sala sala = new Sala(new Jugador("Carlos-1"), 3);
		sala.agregarJugador(new Jugador("Roberto-2"));
		sala.agregarJugador(new Jugador("Hernesto-3"));
		sala.agregarJugador(new Jugador("Ricardo-4"));
		sala.empezarJuego();
	}
}