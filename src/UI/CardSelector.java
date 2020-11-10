package UI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import cartas.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardSelector extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Carta> cartas = new ArrayList<Carta>();
	private String highlighted = null;
	private int pos;
	private boolean debeElegir;

	/**
	 * Launch the application.
	 */
	public CardSelector() {
		cartas.add(new Sacerdote());
		cartas.add(new Baron());
		cartas.add(new Mucama());
		cartas.add(new Principe());
		cartas.add(new Rey());
		cartas.add(new Condesa());
		cartas.add(new Princesa());

		BufferedImage img = null;
		Image finalImg;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 251, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel displayLabel = new JLabel("");
		displayLabel.setBounds(10, 11, 215, 383);
		contentPane.add(displayLabel);

		try {
			img = ImageIO.read(new File(cartas.get(pos).getImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		finalImg = img.getScaledInstance(displayLabel.getWidth(), displayLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon finalDisplay = new ImageIcon(finalImg);
		displayLabel.setIcon(finalDisplay);
		highlighted = cartas.get(pos).getNombre();

		JButton backButton = new JButton("\u21DA");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pos == 0)
					pos = 6;
				else
					pos--;
				BufferedImage bimg = null;
				try {
					bimg = ImageIO.read(new File(cartas.get(pos).getImagePath()));
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				Image finalImg = bimg.getScaledInstance(displayLabel.getWidth(), displayLabel.getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon finalDisplay = new ImageIcon(finalImg);
				displayLabel.setIcon(finalDisplay);
				highlighted = cartas.get(pos).getNombre();
			}
		});
		backButton.setBounds(10, 405, 41, 23);
		contentPane.add(backButton);

		JButton nextButton = new JButton("\u21DB");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pos == 6)
					pos = 0;
				else
					pos++;
				BufferedImage bimg = null;
				try {
					bimg = ImageIO.read(new File(cartas.get(pos).getImagePath()));
				} catch (IOException e1) {
					e1.printStackTrace();
					return;
				}
				Image finalImg = bimg.getScaledInstance(displayLabel.getWidth(), displayLabel.getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon finalDisplay = new ImageIcon(finalImg);
				displayLabel.setIcon(finalDisplay);
				highlighted = cartas.get(pos).getNombre();
			}
		});
		nextButton.setBounds(184, 405, 41, 23);
		contentPane.add(nextButton);

		JButton acceptButton = new JButton("Aceptar");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debeElegir = false;
				dispose();
			}
		});
		acceptButton.setBounds(61, 405, 113, 23);
		contentPane.add(acceptButton);

	}

	public String getSeleccion() {
		this.debeElegir = true;
		while (debeElegir) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.highlighted;
	}
}
