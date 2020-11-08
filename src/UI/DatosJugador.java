package UI;

import javax.swing.JPanel;

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
		g2.drawImage(carta1, null, 90, 90);
		g2.drawImage(carta2, null, 190, 190);
	}

	public void actualizar() {
		if (jugador == null)
			return;
		try {
			this.carta1 = ImageIO.read(new File(jugador.getCarta().getImagePath()));
			this.carta2 = ImageIO.read(new File("Assets/imgs/" + "Guardia" + ".png")); //falta enviar bien la 2da carta
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.repaint();
	}

}
