package Frontend;


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Palavras;


import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaJogo extends JFrame {
	private StringBuilder palavraescondida = new StringBuilder();
	private JPanel contentPane;
	private JTextField letrainput;
	private JLabel imgforca;
	String selecionada;
	int acertos = 0;
	int erros = 0;
	private JLabel textopalavra;
	private final JLabel textotentativa = new JLabel("");
	ArrayList<String> letrastentadas = new ArrayList<String>();
	Palavras palavra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	   
		/*
		 * Inicia as palavras e transformar em char
		 */
		palavra = new Palavras();
		palavra.carregar_palavras();
		selecionada = palavra.palavraSelecionada();
		char[] letras = selecionada.toCharArray();
		
		
		setResizable(false);
		setTitle("JOGO DA FORCA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/*
		 * letrainput recebe as letras que estou tentando
		 */
		letrainput = new JTextField();
		letrainput.setColumns(10);
		
		JButton btnTentar = new JButton("TENTAR");
		btnTentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String letra = letrainput.getText().toUpperCase();
				if(letra.length() > 1) {
					JOptionPane.showMessageDialog(null, "Mais de uma letra foi inserida!");
				}
				else if (!letrastentadas.contains(letra)){
					try {
						revelaLetra(letra);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					letrainput.setText("");
				}
				else {
					System.out.println("Já tem");
					letrastentadas.add(letra);
				}
			}
		});
		
		/*
		 * imgforca é a variavel que vai cuidar das imagens da forca conforme o jogo se passa
		 */
		imgforca = new JLabel("");
		desenhaBoneco(0);
		
		/*
		 * textopalavra é a variavel que guarda a palavra selecionada para o jogador descobrir
		 */
		textopalavra = new JLabel("");
		textopalavra.setFont(new Font("FreeSans", Font.BOLD, 15));
		
		/*
		 * Problema no laço que a palavra está condida
		 * 
		 * O laço funciona perfeitamente se não colocarmos espaços entre os "_". Porém, se deixar
		 * " _ " já não irá funcionar
		 */
		for(int i = 0; i < letras.length; i++) {
			palavraescondida.append("_");
		}
		textopalavra.setText(palavraescondida.toString());

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(imgforca, GroupLayout.PREFERRED_SIZE, 429, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(128)
					.addComponent(letrainput, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnTentar)
					.addGap(266))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addComponent(textotentativa, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(182, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(117)
					.addComponent(textopalavra, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(imgforca, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textopalavra, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTentar)
						.addComponent(letrainput, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(textotentativa, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
		
	
		
	
		
	}
	
	/*
	 * Verificação dos erros e acertas
	 * Incrementa o desenho para que se vá montando o boneco na forca
	 */
	public void revelaLetra (String letra) throws IOException {
		char[] letras = selecionada.toCharArray();
		
		int acertoantigo = acertos;
		for(int i = 0; i < letras.length; i++) {
			if(letra.equals(String.valueOf(letras[i]))) {
				palavraescondida.setCharAt(i, letra.charAt(0));
				acertos++;
			}
			textopalavra.setText(palavraescondida.toString());
						
		}
		if (acertoantigo == acertos) {
			erros++;
		}
		desenhaBoneco(erros);
		
		System.out.println(acertos);
		System.out.println(erros);
		tentativas(letra);
		
		if(acertos == letras.length) {
			int jogarnovamente = JOptionPane.showConfirmDialog(null, "Você venceu! \nDeseja jogar novamente?");
			if(jogarnovamente == 0) {
			TelaJogo novamente = new TelaJogo();
			novamente.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (novamente.getWidth() / 2)), 
	                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (novamente.getHeight() / 2)));
			novamente.setVisible(true);
			dispose();
			}
			else {
				dispose();
			}
		}
		if(erros == 6) {
			int jogarnovamente = JOptionPane.showConfirmDialog(null, "Perdeu! A palavra era: " + selecionada + "\nDeseja jogar novamente?");
			if(jogarnovamente == 0) {
			TelaJogo novamente = new TelaJogo();
			novamente.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (novamente.getWidth() / 2)), 
	                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (novamente.getHeight() / 2)));
			novamente.setVisible(true);
			dispose();
			}
			else {
				dispose();
			}
			
			
		}
	} 
	
	/*
	 * Gerência as letras que já foram utilizadas ao longo do jogo
	 */
	public void tentativas(String letra) {
		char[] letras = selecionada.toCharArray();
		
		StringBuilder tentativas = new StringBuilder();
		tentativas.append(textotentativa.getText() + " - ");
		tentativas.append(letra);
		textotentativa.setText(tentativas.toString());
		letrastentadas.add(letra);
	}
	
	/*
	 * desenha o boneco na forca
	 * 
	 */
	public void desenhaBoneco (int n) {
		imgforca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DesenhoForca/" + n + ".png")));
	}

}