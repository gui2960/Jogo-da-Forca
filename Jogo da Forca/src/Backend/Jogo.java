package Backend;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DesenhoForca.Desenhar;
import Frontend.TelaJogo;

public class Jogo {
	Desenhar desenho = new Desenhar();
	String selecionada;
	public int acertos = 0;
	int erros = 0;
	Palavras palavra = new Palavras();
	ArrayList<String> letrastentadas = new ArrayList<String>();
	char[] letras;
	String letra;
	
	public void carregar_palavras(){
			try {
				this.palavra.carregar_palavras();
				this.selecionada = palavra.palavraSelecionada();
				this.letras = selecionada.toCharArray();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Mais de uma letra foi inserida!");
			}
	}

	public boolean testa_letra(JTextField letrainput, StringBuilder palavraescondida, JLabel textopalavra,
			JLabel imgforca, StringBuilder tentativas, JLabel textotentativa) {
		letra = letrainput.getText().toUpperCase();
		letrainput.setText("");
		if (letra.length() > 1) {
			JOptionPane.showMessageDialog(null, "Mais de uma letra foi inserida!");
		} else if (!letrastentadas.contains(letra)) {
			try {
				textotentativa.setText("");
				return revelaLetra(letra, palavraescondida, textopalavra, imgforca, tentativas, textotentativa);
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			letrainput.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Esta letra já foi tentanda!");

		}

		return true;
	}

	/*
	 * 
	 * 
	 * 
	 */
	public boolean revelaLetra(String letra, StringBuilder palavraescondida, JLabel textopalavra, JLabel imgforca,
			StringBuilder tentativas, JLabel textotentativa) throws IOException {
		char[] letras = selecionada.toCharArray();
		int acertoantigo = acertos;
		for (int i = 0; i < letras.length; i++) {
			if (letra.equals(String.valueOf(letras[i]))) {
				palavraescondida.setCharAt(i, letra.charAt(0));
				acertos++;
				
			}
		}
	
			
		
		textopalavra.setText(palavraescondida.toString());

		if (acertoantigo == acertos) {
			erros++;
		}
		desenho.desenhaBoneco(erros, imgforca);

		System.out.println(acertos);
		System.out.println(erros);
		tentativas(letra, tentativas, textotentativa, letrastentadas);

		if (acertos == letras.length) {
			int jogarnovamente = JOptionPane.showConfirmDialog(null, "Você venceu! \nDeseja jogar novamente?");
			if (jogarnovamente == 0) {
				TelaJogo novamente = new TelaJogo();
				novamente.setLocation(
						((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (novamente.getWidth() / 2)),
						((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (novamente.getHeight() / 2)));
				novamente.setVisible(true);
				return false;
			} else {
				return false;
			}
		}
		if (erros == 6) {
			int jogarnovamente = JOptionPane.showConfirmDialog(null,
					"Perdeu! A palavra era: " + selecionada + "\nDeseja jogar novamente?");
			if (jogarnovamente == 0) {
				TelaJogo novamente = new TelaJogo();
				novamente.setLocation(
						((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (novamente.getWidth() / 2)),
						((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (novamente.getHeight() / 2)));
				novamente.setVisible(true);
				return false;
			} else {
				return false;
			}

		}
		return true;
	}

	public void tentativas(String letra, StringBuilder tentativas, JLabel textotentativa,
			ArrayList<String> letrastentadas) {
		this.letras = selecionada.toCharArray();
		tentativas.append(textotentativa.getText() + " - ");
		tentativas.append(letra);
		textotentativa.setText(tentativas.toString());
		letrastentadas.add(letra);
	}

	public void esconder_palavra(StringBuilder palavraescondida) {
		for (int i = 0; i < letras.length; i++) {
			palavraescondida.append("-");
			

		}
		System.out.println(letras.length);
		System.out.println(palavraescondida.length());

	}

}
