package Backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;




public class Palavras {
	ArrayList<String> palavras;
	/*
	 * Carregas as palavras do arquivo "palavras.txt" dentro do ArrayList palavras
	 */
	public void carregar_palavras() throws IOException{
		/*
		 * Se o ArrayList estiver vazio, ele entra no laço
		 * Evita a necessidade de varias leituras durante o uso
		 */
		if(palavras == null) {
			palavras = new ArrayList<String>();
	        try {
	        	InputStream in = getClass().getResourceAsStream("/palavras.txt");
	        	BufferedReader lerArq = new BufferedReader(new InputStreamReader(in));
	            String str = lerArq.readLine();
	            while (str != null) {
	            	palavras.add(str.toUpperCase());
	                str = lerArq.readLine();
	            }
	            in.close();
	        } catch (IOException e) {
	            System.err.printf("Erro na abertura do arquivo!");
	        }

	        System.out.println();
	    }
	}
	/*
	 * Busca uma palavra aleatória dentro do arquivo "palavras.txt"
	 */
	public String palavraSelecionada(){
		int randomNum = ThreadLocalRandom.current().nextInt(0, palavras.size());
		return palavras.get(randomNum);
	}

}