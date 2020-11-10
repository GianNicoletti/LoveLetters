package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cartas.Carta;
import jugador.Jugador;
import sala.Sala;
import javax.swing.JLabel;
import java.awt.Color;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private Sala sala;
	private DatosJugador[] datosJugadores;
	private int jugadorActual;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainWindow frame = new MainWindow();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public MainWindow(Sala sala) {
		this.sala = sala;
		int[] posiciones = { 398, 311, 35, 134, 383, 26, 698, 133 };
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 557);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel puntos = new JLabel(sala.getSimbParaGanar() + "");
		puntos.setBounds(508, 11, 46, 14);
		contentPane.add(puntos);
		datosJugadores = new DatosJugador[sala.getJugadores().size()];
		for (int i = 0; i < datosJugadores.length; i++) {
			datosJugadores[i] = new DatosJugador(sala.getJugadores().get(i));
			datosJugadores[i].setBounds(posiciones[i * 2], posiciones[i * 2 + 1], 300, 250);
			contentPane.add(datosJugadores[i]);
		}

	}

	public void actualizar(int actual) {
		this.jugadorActual = actual;
		for (DatosJugador dj : datosJugadores)
			dj.actualizar(sala.getJugadorPorIndice(actual));
	}

	public void actualizarActual(Carta carta2) {
		datosJugadores[jugadorActual].actualizarCarta2(carta2);
	}

	public int elegirCarta() {
		return datosJugadores[jugadorActual].getCartaElegida();
	}

}
