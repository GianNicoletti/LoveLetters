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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class DatosJugador extends JPanel {

	private Jugador jugador;
	private PanelCarta carta1;
	private PanelCarta carta2;
	private int cartaElegida;
	private boolean debeElegirCarta;
	private JLabel puntaje;
	private JLabel descarte;
	private JLabel nombre;
	private JButton rendirse;
	private boolean abandono;

	/**
	 * Create the panel.
	 */
	public DatosJugador(Jugador j) {
		super();
		abandono = false;
		setBackground(Color.WHITE);
		this.jugador = j;
		setLayout(null);

		nombre = new JLabel(j.getNombre());
		nombre.setBackground(SystemColor.info);
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setBounds(27, 24, 155, 14);

		add(nombre);

		carta1 = new PanelCarta();
		carta1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (debeElegirCarta) {

					System.out.println("tocado");
					cartaElegida = 1;
					debeElegirCarta = false;
				}
			}
		});
		carta1.setBounds(27, 49, 200, 260);
		carta1.setLayout(null);
		carta1.setBackground(new Color(0, 0, 0, 1));
		add(carta1);

		carta2 = new PanelCarta();
		carta2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cartaElegida = 2;
				debeElegirCarta = false;
			}
		});
		carta2.setBounds(255, 49, 200, 260);
		carta2.setBackground(new Color(0, 0, 0, 1));
		add(carta2);
		carta2.setLayout(null);

		puntaje = new JLabel("New label");
		puntaje.setBounds(372, 16, 83, 30);
		puntaje.setText(jugador.getPuntaje() + " Puntos");
		add(puntaje);

		descarte = new JLabel(jugador.getCantDescartadas() + " cartas jugadas");
		descarte.setBounds(192, 24, 131, 14);
		add(descarte);

		rendirse = new JButton("Rendirse");
		rendirse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cartaElegida = 3;
				debeElegirCarta = false;
			}
		});
		rendirse.setBounds(76, 320, 330, 23);
		add(rendirse);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Dimension currentDimension = getContentPane().getSize();
		// g2.scale(currentDimension.getWidth() / WIDTH, currentDimension.getHeight() /
		// HEIGHT);

		int i = 0;
		for (Carta carta : jugador.getDescartadas()) {
			BufferedImage image;
			try {
				image = ImageIO.read(new File("Assets/imgs/" + carta.getImagePath() + ".png"));
				BufferedImage img = new BufferedImage(10, 10, image.getType());
				g2.drawImage(image, null, 0 + i, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}
		// g2.drawImage(carta1, null, 5, 90);
		// g2.drawImage(carta2, null, 90, 90);
	}

	public void actualizar(Jugador ac) {
		if (!abandono) {

			try {
				if (ac == jugador) {
					this.carta1.setImg(ImageIO.read(new File(jugador.getCarta().getImagePath())));
					this.rendirse.setVisible(true);
				} else {
					this.carta1.setImg(ImageIO.read(new File("Assets/imgs/" + "Atras" + ".png")));
					this.rendirse.setVisible(false);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			carta2.setImg(null);
			puntaje.setText(jugador.getPuntaje() + " Puntos");
			descarte.setText((jugador.getCantDescartadas() + " cartas jugadas"));

			// UNA FORMA MUY BRUSCA DE MARCAR QUE ESTA ELIMINADO
			if (!jugador.juegaRonda())
				this.nombre.setForeground(Color.red);
			else if (jugador.isProtegido())
				this.nombre.setForeground(Color.green);
			else
				this.nombre.setForeground(Color.black);
		} else {
			this.nombre.setText(jugador.getNombre() + " - Desconectado");
			this.nombre.setForeground(Color.red);
			this.carta1.setImg(null);
			this.carta2.setImg(null);
			this.rendirse.setVisible(false);
		}
		this.repaint();
	}

	public void actualizarCarta2(Carta carta2) {
		try {
			this.carta2.setImg(ImageIO.read(new File(carta2.getImagePath())));
			this.carta2.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCartaElegida() {
		System.out.println("voy a elegir");
		debeElegirCarta = true;
		while (debeElegirCarta) {
			cartaElegida = 0;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return cartaElegida;
	}

	public void verCarta() {
		try {
			carta1.setImg(ImageIO.read(new File(jugador.getCarta().getImagePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		carta1.repaint();
	}

	public void setNull() {
		this.abandono = true;
	}
}
