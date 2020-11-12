package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jugador.Jugador;
import sala.Sala;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindowOffline extends JFrame {

	private JPanel contentPane;
	private JTextField jugador1Field;
	private JTextField jugador2Field;
	private JTextField jugador3Field;
	private JTextField jugador4Field;
	private Sala sala;
	private boolean debeCrear;
	
	public LoginWindowOffline() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jugador1Field = new JTextField();
		jugador1Field.setBounds(10, 67, 130, 20);
		contentPane.add(jugador1Field);
		jugador1Field.setColumns(10);
		
		jugador2Field = new JTextField();
		jugador2Field.setColumns(10);
		jugador2Field.setBounds(10, 122, 130, 20);
		contentPane.add(jugador2Field);
		
		jugador3Field = new JTextField();
		jugador3Field.setColumns(10);
		jugador3Field.setBounds(183, 67, 130, 20);
		contentPane.add(jugador3Field);
		
		jugador4Field = new JTextField();
		jugador4Field.setColumns(10);
		jugador4Field.setBounds(183, 122, 130, 20);
		contentPane.add(jugador4Field);
		
		JButton startButton = new JButton("Empezar Partida");
		startButton.setBounds(10, 166, 303, 23);
		contentPane.add(startButton);
		
		JLabel lblNewLabel = new JLabel("Jugador3");
		lblNewLabel.setBounds(10, 108, 130, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblJugador = new JLabel("Jugador1");
		lblJugador.setBounds(10, 52, 130, 14);
		contentPane.add(lblJugador);
		
		JLabel lblNewLabel_2 = new JLabel("Jugador2");
		lblNewLabel_2.setBounds(183, 52, 130, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblJugador_1 = new JLabel("Jugador4");
		lblJugador_1.setBounds(183, 108, 130, 14);
		contentPane.add(lblJugador_1);
		
		
		SpinnerModel value = new SpinnerNumberModel(2, 2, 6, 1);
		JSpinner cantSimbolos = new JSpinner(value);
		cantSimbolos.setBounds(10, 11, 30, 20);
		contentPane.add(cantSimbolos);
		
		JLabel lblNewLabel_1 = new JLabel("Simbolos para ganar");
		lblNewLabel_1.setBounds(50, 14, 263, 14);
		contentPane.add(lblNewLabel_1);
		
		startButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				String aux;
				aux=jugador1Field.getText();
				if(!aux.isEmpty() || !aux.isBlank())
					sala=new Sala(new Jugador(aux),(int)cantSimbolos.getValue());
				aux=jugador2Field.getText();
				if(!aux.isEmpty() || !aux.isBlank())
					sala.agregarJugador(new Jugador(aux));
				aux=jugador3Field.getText();
				if(!aux.isEmpty() || !aux.isBlank())
					sala.agregarJugador(new Jugador(aux));
				aux=jugador4Field.getText();
				if(!aux.isEmpty() || !aux.isBlank())
					sala.agregarJugador(new Jugador(aux));
				debeCrear = false;
				dispose();
			}
		});
		
	}
	
	public Sala getSala() {
		this.debeCrear = true;
		while (debeCrear) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.sala;
	}
}
