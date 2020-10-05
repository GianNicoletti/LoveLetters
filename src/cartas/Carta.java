package cartas;

import jugador.Jugador;
import sala.Sala;

public abstract class Carta {
	private String nombre;
	private int fuerza;
	
	public Carta(String nombre, int fuerza) {
		this.nombre = nombre;
		this.fuerza = fuerza;
	}
	
	public abstract void setJugador(Jugador jugador);
	public abstract void descartar(Sala sala);

	public String getNombre() {
		return nombre;
	}

	public int getFuerza() {
		return fuerza;
	}
	
	
}
