import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<String> gama = new ArrayList<>();
		ArrayList<String> fi = new ArrayList<>();		

		try {
			FileReader arquivo = new FileReader("E:\\Downloads\\dados4.txt");
			BufferedReader ler = new BufferedReader(arquivo);
			String linhas = ler.readLine();
			while (linhas != null) {				
				while (linhas.substring(0, 1).equals("c") || linhas.substring(0, 1).equals("p"))
					linhas = ler.readLine();
				
				gama.add(linhas);		
				linhas = ler.readLine();				

				if (linhas != null){
					if (linhas.substring(0, 1).equals("$")){					
						linhas = ler.readLine();					
						fi.add(linhas);
						break;
					}
				}
			}			
			arquivo.close();	

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}		
		try {
			Resolucao resolucao = new Resolucao(gama,fi);							
		} catch (Exception e) {
			System.err.printf("Erro NumberFormatException na Main");
		}					
	}
}