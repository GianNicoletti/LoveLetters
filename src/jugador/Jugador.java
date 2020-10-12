package jugador;

import java.util.Scanner;

import cartas.Carta;
import sala.Sala;

public class Jugador {
	private String nombre;
	private Sala sala;
	private int puntaje;
	private int cantDescartadas;
	private boolean host;
	private boolean juegaRonda;
	private boolean protegido;
	private Carta carta;

	public Jugador(String nombre) {
		this.nombre = nombre;
		juegaRonda = true;
		protegido = false;
	}

	public Sala crearSala() {
		int num;
		try (Scanner teclado = new Scanner(System.in)) {
			do {
				System.out.println("Elegir la cantidad de simbolos de afecto para ganar (maximo 99):");
				num = Integer.valueOf(teclado.nextLine());
			} while (num < 0 || num > 99);
		}
		return new Sala(this, num);
	}

	public void unirseASala(Sala sala) {
		sala.agregarJugador(this);
		this.sala = sala;
	}

	public void jugar() {
		if (protegido)
			protegido = false;
		Carta carta2;
		carta2 = this.robar();
		if (carta.getNombre() == "Condesa" && (carta2.getNombre() == "Principe" || carta2.getNombre() == "Rey")) {
			Carta aux = carta;
			carta = carta2;
			aux.descartar(this);
			cantDescartadas++;
		} else if (carta2.getNombre() == "Condesa" && (carta.getNombre() == "Principe" || carta.getNombre() == "Rey")) {
			carta2.descartar(this);
			cantDescartadas++;
		} else {
			System.out.println("Elige una opcion: ");
			System.out.println("1 - " + carta.getNombre());
			System.out.println("2 - " + carta2.getNombre());
			System.out.println("3 - Rendirse");
			System.out.print("--> ");
			try (Scanner teclado = new Scanner(System.in)) {
				switch (teclado.nextLine()) {
				case "1":
					Carta aux = carta;
					carta = carta2;
					aux.descartar(this);
					cantDescartadas++;
					break;
				case "2":
					carta2.descartar(this);
					cantDescartadas++;
					break;
				case "3":
					this.rendirse();
				}
			}
		}
	}

	public Jugador seleccionarOtroJugador() {
		Jugador otro;
		System.out.println("Elige un jugador (escribe el numero del jugador): ");
		try (Scanner teclado = new Scanner(System.in)) {
			sala.listarJugadores();
			int i = Integer.valueOf(teclado.nextLine());
			otro = sala.getJugadorPorIndice(i);
			return otro;
		}
	}

	public void salirDeRonda() {
		juegaRonda = false;
	}

	public void intercambiarMano(Jugador otro) {
		Carta cartaOtro = otro.carta;
		otro.carta = this.carta;
		this.carta = cartaOtro;
	}

	public void cambiarCarta() {
		if (carta.getNombre() == "Princesa") {
			carta.descartar(this);
		}
		carta=this.robar();
	}

	public Carta robar() {
		return sala.getCarta();
	}

	public void rendirse() {
		sala.eliminar(this);
	}

	public boolean tiene(String nombreCarta) {
		return nombreCarta == carta.getNombre();
	}

	public void incPuntaje() {
		this.puntaje++;
	}

	// Getters y Setters
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public String getNombre() {
		return nombre;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public boolean juegaRonda() {
		return juegaRonda;
	}

	public boolean isProtegido() {
		return protegido;
	}

	public void setProtegido(boolean protegido) {
		this.protegido = protegido;
	}

	public int getCantDescartadas() {
		return cantDescartadas;
	}

	public void setCantDescartadas(int cantDescartadas) {
		this.cantDescartadas = cantDescartadas;
	}

}
