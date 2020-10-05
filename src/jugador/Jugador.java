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
	private Carta carta1;

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
	}

	public void jugar() {
		Carta carta2;
		carta2 = this.robar();
		if (carta1.getNombre() == "Condesa")
			carta1.descartar(sala);
		else if (carta2.getNombre() == "Condesa")
			carta2.descartar(sala);
		else {
			System.out.println("Elige una opcion: ");
			System.out.println("1 - " + carta1.getNombre());
			System.out.println("2 - " + carta2.getNombre());
			System.out.println("3 - Rendirse");
			System.out.print("--> ");
			try (Scanner teclado = new Scanner(System.in)) {
				switch (teclado.nextLine()) {
				case "1":
					carta1.descartar(sala);
					carta1 = carta2;
					cantDescartadas++;
					break;
				case "2":
					carta2.descartar(sala);
					cantDescartadas++;
					break;
				case "3":
					this.rendirse();
				}
			}
		}
	}

	public void intercambiarMano(Jugador otro) {
		Carta cartaOtro = otro.carta1;
		cartaOtro.setJugador(this);
		otro.carta1 = this.carta1;
		otro.carta1.setJugador(otro);
		this.carta1 = cartaOtro;
	}

	public void cambiarCarta() {
		if (carta1.getNombre().equals("Princesa")) {
			sala.sacarDeRonda(this);
		} else
			this.robar();
	}

	public Carta robar() {
		Carta carta = sala.getCarta();
		carta.setJugador(this);
		return carta;
	}

	public void rendirse() {
		sala.eliminar(this);
	}

	public boolean tiene(String nombreCarta) {
		return nombreCarta == carta1.getNombre();
	}

	public void incPuntaje() {
		this.puntaje++;
	}

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

	public Carta getCarta1() {
		return carta1;
	}

	public void setCarta1(Carta carta1) {
		this.carta1 = carta1;
		carta1.setJugador(this);
	}

	public boolean juegaRonda() {
		return juegaRonda;
	}

	public void setJuegaRonda(boolean juegaRonda) {
		this.juegaRonda = juegaRonda;
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
