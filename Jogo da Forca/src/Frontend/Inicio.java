package Frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;


import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Inicio {

	private JFrame frmJogoDaForca;
	private final JLabel lblNewLabel = new JLabel("JOGO DA FORCA");

	/**
	 * Launch the application.
	 */
		
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frmJogoDaForca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogoDaForca = new JFrame();
		frmJogoDaForca.setTitle("JOGO DA FORCA");
		frmJogoDaForca.setResizable(false);
		frmJogoDaForca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJogoDaForca.setBounds(100, 100, 430, 300);
		frmJogoDaForca.getContentPane().setLayout(null);
		
		frmJogoDaForca.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (frmJogoDaForca.getWidth() / 2)), 
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (frmJogoDaForca.getHeight() / 2)));
		
		
		JButton btnJogar = new JButton("JOGAR");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaJogo telaJogo;
				try {
					telaJogo = new TelaJogo();
					telaJogo.setVisible(true);
					telaJogo.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (telaJogo.getWidth() / 2)), 
			                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (telaJogo.getHeight() / 2)));
					frmJogoDaForca.dispose();
					frmJogoDaForca.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnJogar.setBounds(159, 109, 114, 25);
		frmJogoDaForca.getContentPane().add(btnJogar);
		
		JButton btnRegras = new JButton("REGRAS");
		btnRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regra regra = new Regra();
				regra.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (regra.getWidth() / 2)), 
		                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (regra.getHeight() / 2)));
				frmJogoDaForca.dispose();
				regra.setVisible(true);
				
			}
		});
		btnRegras.setBounds(159, 152, 114, 25);
		frmJogoDaForca.getContentPane().add(btnRegras);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setBounds(83, 12, 286, 49);
		frmJogoDaForca.getContentPane().add(lblNewLabel);
	}
}