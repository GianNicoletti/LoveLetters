package cartas;

import jugador.Jugador;

public abstract class Carta {
	private String nombre;
	private int fuerza;
	
	public Carta(String nombre, int fuerza) {
		this.nombre = nombre;
		this.fuerza = fuerza;
	}
	
	public abstract void descartar(Jugador jugador);

	public String getNombre() {
		return nombre;
	}

	public int getFuerza() {
		return fuerza;
	}
	
	
}
