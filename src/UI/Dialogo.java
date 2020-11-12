package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Dialogo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425548700105069971L;
	private final JPanel texto = new JPanel();
	private boolean debeContinuar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Dialogo(String mensaje) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				debeContinuar = false;
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 139);
		getContentPane().setLayout(new BorderLayout());
		texto.setLayout(new FlowLayout());
		texto.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(texto, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel(mensaje);
			texto.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						debeContinuar = false;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void pasar() {
		debeContinuar = true;
		while (debeContinuar) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.dispose();
	}

}
