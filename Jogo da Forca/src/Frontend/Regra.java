package Frontend;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Jogo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Regra extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Regra frame = new Regra();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
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
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Regra() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnJogar = new JButton("JOGAR");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaJogo telaJogo;
				try {
					Jogo jogo = new Jogo();
					jogo.carregar_palavras();
					telaJogo = new TelaJogo();
					telaJogo.setVisible(true);
					telaJogo.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (telaJogo.getWidth() / 2)), 
			                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (telaJogo.getHeight() / 2)));
					dispose();
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel label = new JLabel("MODO DE JOGO");
		/*
		 *  IMPRIME A REGRA DO JOGO
		 */
		JTextPane txtpnCrieUmArquivo = new JTextPane();
		txtpnCrieUmArquivo.setText("Crie um arquivo na mesma pasta do jogo chamado \"palavras.txt\".\nO seu desafiante será o computador. Ele se encarregará de buscar aleatóriamente uma palavra dentro do arquivo que escolheu e você terá que acertar qual foi.\nPara isso, você terá 6 (seis) vidas!");
		txtpnCrieUmArquivo.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(178)
					.addComponent(btnJogar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(165))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(130))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtpnCrieUmArquivo, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnCrieUmArquivo, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(btnJogar))
		);
		contentPane.setLayout(gl_contentPane);
	}

}