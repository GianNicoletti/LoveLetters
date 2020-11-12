package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sala.Sala;

public class MainContainer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5598692079766445564L;
	private MainWindow contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainContainer(Sala sala) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
				
		contentPane = new MainWindow(sala);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
	}

	public MainWindow getContentPane() {
		return contentPane;
	}

}
