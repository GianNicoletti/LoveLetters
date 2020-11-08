package cartas;

import jugador.Jugador;

public abstract class Carta {
	private String nombre;
	private int fuerza;
	private String imagePath;

	public Carta(String nombre, int fuerza) {
		this.nombre = nombre;
		this.fuerza = fuerza;
		this.imagePath = "Assets/imgs/" + nombre + ".png";
	}

	public String getImagePath() {
		return imagePath;
	}

	public abstract void descartar(Jugador jugador);

	public String getNombre() {
		return nombre;
	}

	public int getFuerza() {
		return fuerza;
	}

}
