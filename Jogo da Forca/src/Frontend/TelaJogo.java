package Frontend;


import java.awt.EventQueue;
import java.io.IOException;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Jogo;
import DesenhoForca.Desenhar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;

public class TelaJogo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StringBuilder palavraescondida = new StringBuilder();
	private JPanel contentPane;
	private JTextField letrainput;
	private JLabel imgforca;
	private JLabel textopalavra;
	private final JLabel textotentativa = new JLabel("");
	StringBuilder tentativas = new StringBuilder();
	boolean a = true;
	Jogo jogo = new Jogo();
	Desenhar primeira = new Desenhar();

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
					TelaJogo frame = new TelaJogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public TelaJogo() throws IOException {
		setResizable(false);
		setTitle("JOGO DA FORCA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		letrainput = new JTextField(); //letrainput recebe as letras que estou tentando
		letrainput.setColumns(10);
		imgforca = new JLabel(""); //imgforca é a variavel que vai cuidar das imagens da forca conforme o jogo se passa
		textopalavra = new JLabel(""); //textopalavra é a variavel que guarda a palavra selecionada para o jogador descobrir
		textopalavra.setFont(new Font("FreeSans", Font.BOLD, 20));
		jogo.carregar_palavras();
		primeira.desenhaBoneco(0, imgforca);
		JButton btnTentar = new JButton("TENTAR");
		btnTentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = jogo.testa_letra(letrainput, palavraescondida, textopalavra, imgforca, tentativas, textotentativa);
				if(a == false) dispose();
			}
		});
		
		letrainput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					jogo.testa_letra(letrainput, palavraescondida, textopalavra, imgforca, tentativas, textotentativa);
					if(a == false) dispose();
				}
			}
		});
		
		jogo.esconder_palavra(palavraescondida);
		textopalavra.setText(palavraescondida.toString());
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(imgforca, GroupLayout.PREFERRED_SIZE, 429, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addComponent(letrainput, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnTentar)
					.addGap(266))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addComponent(textotentativa, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(182, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addComponent(textopalavra, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(imgforca, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textopalavra, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTentar)
						.addComponent(letrainput, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
					.addGap(29)
					.addComponent(textotentativa, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
		
	
		
	
		
	}
}