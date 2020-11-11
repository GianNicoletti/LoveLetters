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

public class DatosJugador extends JPanel {

	private Jugador jugador;
	private PanelCarta carta1;
	private PanelCarta carta2;
	private int cartaElegida;
	private boolean debeElegirCarta;
	private JLabel puntaje;

	/**
	 * Create the panel.
	 */
	public DatosJugador(Jugador j) {
		super();
		setBackground(Color.WHITE);
		this.jugador = j;
		setLayout(null);

		JLabel nombre = new JLabel(j.getNombre());
		nombre.setBounds(193, 0, 112, 14);
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
		puntaje.setBounds(295, 27, 46, 14);
		puntaje.setText(jugador.getPuntaje() + " Puntos");
		add(puntaje);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Dimension currentDimension = getContentPane().getSize();
		// g2.scale(currentDimension.getWidth() / WIDTH, currentDimension.getHeight() /
		// HEIGHT);
		if (!jugador.juegaRonda())
			g2.drawString("Eliminado", 0, 20);
		else if (jugador.isProtegido())
			g2.drawString("Protegido", 0, 20);
		// g2.drawImage(carta1, null, 5, 90);
		// g2.drawImage(carta2, null, 90, 90);
	}

	public void actualizar(Jugador ac) {
		if (jugador == null)
			return;
		try {
			if (ac == jugador) {
				this.carta1.setImg(ImageIO.read(new File(jugador.getCarta().getImagePath())));
			} else {
				this.carta1.setImg(ImageIO.read(new File("Assets/imgs/" + "Atras" + ".png")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		carta2.setImg(null);
		puntaje.setText(jugador.getPuntaje() + " Puntos");
		
		if(!jugador.juegaRonda())
			setBackground(Color.DARK_GRAY);
		//UNA FORMA MUY BRUSCA DE MARCAR QUE ESTA ELIMINADO
		
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
}
