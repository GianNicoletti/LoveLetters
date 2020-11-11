package jugador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;

import UI.CardSelector;
import UI.MainWindow;
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
	private MainWindow window;
	private ArrayList<Carta> descartadas;

	public Jugador(String nombre) {
		descartadas = new ArrayList<Carta>();
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

	public void jugar(MainWindow window) {
		this.window = window;
		if (protegido)
			protegido = false;
		Carta carta2;
		carta2 = this.robar();
		window.actualizarActual(carta2);
		if (carta.getNombre() == "Condesa" && (carta2.getNombre() == "Principe" || carta2.getNombre() == "Rey")) {
			descartadas.add(carta);
			Carta aux = carta;
			carta = carta2;
			aux.descartar(this);
			cantDescartadas++;
		} else if (carta2.getNombre() == "Condesa" && (carta.getNombre() == "Principe" || carta.getNombre() == "Rey")) {
			descartadas.add(carta2);
			carta2.descartar(this);
			cantDescartadas++;
		} else {
			System.out.println("Elige una opcion: ");
			System.out.println("1 - " + carta.getNombre());
			System.out.println("2 - " + carta2.getNombre());
			System.out.println("3 - Rendirse");
			System.out.print("--> ");
			int cartaElegida = window.elegirCarta();
			switch (cartaElegida) {
			case 1:
				Carta aux = carta;
				carta = carta2;
				aux.descartar(this);
				cantDescartadas++;
				break;
			case 2:
				carta2.descartar(this);
				cantDescartadas++;
				break;
			case 3:
				this.rendirse();
				break;
			}
		}
	}

	public Jugador seleccionarOtroJugador(boolean allowSelf) {
		Jugador otro;
		System.out.println("Elige un jugador (escribe el numero del jugador): ");
		// try (Scanner teclado = new Scanner(System.in)) {
		// sala.listarJugadores();
		// int i = teclado.nextInt();
		int i = window.elegirJugador(allowSelf);
		if (i == -1)
			return null;
		otro = sala.getJugadorPorIndice(i);
		System.out.println("Elegido: " + otro.getNombre());
		return otro;
		// }
	}

	public int seleccionarIndiceOtroJugador() {
		Jugador otro;
		System.out.println("Elige un jugador (escribe el numero del jugador): ");
		// try (Scanner teclado = new Scanner(System.in)) {
		// sala.listarJugadores();
		// int i = teclado.nextInt();
		return window.elegirJugador(false);
		// }
	}

	public void verCarta(int indiceJugador) {
		window.verCarta(indiceJugador);
	}

	public String adivinarCarta() {
		CardSelector seleccionar = new CardSelector();
		seleccionar.setVisible(true);
		return seleccionar.getSeleccion();
	}

	public void salirDeRonda() {
		juegaRonda = false;
	}

	public void entrarEnRonda() {
		juegaRonda = true;
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
		carta = this.robar();
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

	public ArrayList<Carta> getDescartadas() {
		return descartadas;
	}

}
