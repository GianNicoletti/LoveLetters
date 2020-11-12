package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class MainWindow extends JPanel {

	private Sala sala;
	private DatosJugador[] datosJugadores;
	private int jugadorActual;
	private boolean debeElegirJugador;
	private int jugadorElegido;
	private BufferedImage imgAtras;
	private BufferedImage background;
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
			background = ImageIO.read(new File("Assets/imgs/" + "Fondo" + ".jpg"));

			// Determino las dimensiones del monitor.
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double xPantalla = screenSize.getWidth();
			double yPantalla = screenSize.getHeight();

			double xImagen = background.getWidth();
			double yImagen = background.getHeight();

			// Obtengo el porcentaje que debo agrandar el fondo de acuerdo al tamaño del
			// monito
			double xFactor = xPantalla / xImagen;
			double yFactor = yPantalla / yImagen;

			xImagen *= xFactor;
			yImagen *= yFactor;

			// Aplico una transformacion con eso factores a la imagen del fondo para que se
			// estire.
			BufferedImage scaledImage = new BufferedImage((int) xImagen, (int) yImagen, BufferedImage.TYPE_INT_ARGB);
			AffineTransform at = AffineTransform.getScaleInstance(xFactor, yFactor);
			AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			background = ato.filter(background, scaledImage);

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.sala = sala;

		int[] posiciones = { 500, 500, 35, 300, 500, 50, 1300, 300 };

		setLayout(null);

		JLabel puntos = new JLabel("Se necesitan " + sala.getSimbParaGanar() + " simbolos para ganar.");
		puntos.setBounds(230, 5, 210, 16);

		datosJugadores = new DatosJugador[sala.getJugadores().size()];

		for (int i = 0; i < datosJugadores.length; i++) {
			datosJugadores[i] = new DatosJugador(sala.getJugadores().get(i));
			datosJugadores[i].setBounds(posiciones[i * 2], posiciones[i * 2 + 1], 500, 500);
			datosJugadores[i].setBackground(new Color(0, 0, 0, 1));
			add(datosJugadores[i]);
		}

		add(puntos);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(background, null, 0, 0);

		int x = 5;
		int y = 5;

		for (int i = 0; i < sala.getTamanioMazo(); i++) {
			g2.drawImage(imgAtras, null, x + 2 * i, y + 2 * i);
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
			if ((i != jugadorActual || allowSelf) && sala.getJugadorPorIndice(i) != null
					&& sala.getJugadorPorIndice(i).juegaRonda() && !sala.getJugadorPorIndice(i).isProtegido()) {
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
				add(boton);
				j++;
			}
		}
		this.repaint();
		if (j == 0) {
			debeElegirJugador = false;
			System.out.println("no se puede elegir a nadie"); // poner dialogo o algo
			return -1;
		}
		while (debeElegirJugador) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < botones.length; i++)
			if (botones[i] != null)
				remove(botones[i]);
		return jugadorElegido;
	}

	public void verCarta(int indice) {
		datosJugadores[indice].verCarta();
	}

	public void cambiarTurno() {
		int i = jugadorActual + 1;
		if (i == sala.getJugadores().size())
			i = 0;
		while (sala.getJugadorPorIndice(i) == null
				|| (sala.getJugadorPorIndice(i) == null && !sala.getJugadorPorIndice(i).juegaRonda())) {
			i++;
			if (i == sala.getJugadores().size())
				i = 0;
		}
		Dialogo dialog = new Dialogo("El siguiente turno es del jugador "+sala.getJugadorPorIndice(i).getNombre());
		dialog.setVisible(true);
		dialog.pasar();
	}
	
	public void eliminar(int indice) {
		datosJugadores[indice].setNull();
	}
}
