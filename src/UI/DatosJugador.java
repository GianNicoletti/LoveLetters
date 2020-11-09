package UI;

import javax.swing.JPanel;

import cartas.Carta;
import jugador.Jugador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class DatosJugador extends JPanel {

	private Jugador jugador;
	private BufferedImage carta1;
	private BufferedImage carta2;

	/**
	 * Create the panel.
	 */
	public DatosJugador(Jugador j) {
		super();
		this.jugador = j;
		setLayout(null);

		JLabel nombre = new JLabel(j.getNombre());
		nombre.setBounds(183, 27, 112, 14);
		add(nombre);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Dimension currentDimension = getContentPane().getSize();
		// g2.scale(currentDimension.getWidth() / WIDTH, currentDimension.getHeight() /
		// HEIGHT);
		g2.drawString(jugador.getPuntaje() + " Puntos", 84, 58);
		g2.drawImage(carta1, null, 5, 90);
		g2.drawImage(carta2, null, 90, 90);
	}

	public void actualizar(Jugador ac) {
		if (jugador == null)
			return;
		try {
			if (ac == jugador) {
				this.carta1 = ImageIO.read(new File(jugador.getCarta().getImagePath()));
			} else {
				this.carta1 = ImageIO.read(new File("Assets/imgs/" + "Atras" + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		carta2=null;
		this.repaint();
	}

	public void actualizarCarta2(Carta carta2) {
		try {
			this.carta2 = ImageIO.read(new File(carta2.getImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
