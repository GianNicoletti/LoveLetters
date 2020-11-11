package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cartas.Carta;
import jugador.Jugador;
import sala.Sala;
import javax.swing.JLabel;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private Sala sala;
	private DatosJugador[] datosJugadores;
	private int jugadorActual;
	private boolean debeElegirJugador;
	private int jugadorElegido;
	private BufferedImage imgAtras;
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
		try {
			imgAtras = ImageIO.read(new File("Assets/imgs/" + "Atras" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sala = sala;
		this.setResizable(true);
		int[] posiciones = { 500, 500, 35, 300, 500, 50, 1300, 300 };
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel puntos = new JLabel(sala.getSimbParaGanar() + "");
		puntos.setBounds(508, 11, 46, 14);
		contentPane.add(puntos);

		DatosJugador a = new DatosJugador(sala.getJugadores().get(1));
		contentPane.add(a);

		datosJugadores = new DatosJugador[sala.getJugadores().size()];
		for (int i = 0; i < datosJugadores.length; i++) {
			datosJugadores[i] = new DatosJugador(sala.getJugadores().get(i));
			datosJugadores[i].setBounds(posiciones[i * 2], posiciones[i * 2 + 1], 500, 500);
			datosJugadores[i].setBackground(new Color(0, 0, 0, 1));
			contentPane.add(datosJugadores[i]);
		}

	}

	public void actualizar(int actual) {
		this.jugadorActual = actual;
		for (DatosJugador dj : datosJugadores)
			dj.actualizar(sala.getJugadorPorIndice(actual));
		this.repaint();
	}

	public void actualizarActual(Carta carta2) {
		datosJugadores[jugadorActual].actualizarCarta2(carta2);
	}

	public int elegirCarta() {
		return datosJugadores[jugadorActual].getCartaElegida();
	}

	// @Override
	// public void paint(Graphics g) {
	// Graphics2D g2 = (Graphics2D) g;
	// int y = 0;
	// int x = 0;
	// for (int i = 0; i < 30; i++) {
	// g2.drawImage(imgAtras, null, x + i, y + i);
	// }
	// }

	public int elegirJugador(boolean allowSelf) {

		debeElegirJugador = true;
		int j = 0;
		int cant = sala.jugadoresEnRonda();
		if (!allowSelf)
			cant--;
		JButton[] botones = new JButton[cant];
		int[] posiciones = { 500, 500, 35, 300, 500, 50, 1300, 300 };
		JButton boton;
		for (int i = 0; i < sala.getJugadores().size(); i++) {
			if ((i != jugadorActual || allowSelf) && sala.getJugadorPorIndice(i).juegaRonda()
					&& !sala.getJugadorPorIndice(i).isProtegido()) {
				boton = new JButton("Seleccionar");
				boton.setBounds(posiciones[i * 2], posiciones[i * 2 + 1], 89, 23);
				botones[j] = boton;
				final int index = i;
				botones[j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						jugadorElegido = index;
						debeElegirJugador = false;
					}
				});
				contentPane.add(boton);
				j++;
			}
		}
		this.repaint();
		while (debeElegirJugador) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < botones.length; i++)
			if (botones[i] != null)
				contentPane.remove(botones[i]);
		return jugadorElegido;
	}
}
