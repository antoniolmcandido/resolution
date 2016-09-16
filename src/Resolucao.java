import java.util.ArrayList;
import java.util.Collections;

public class Resolucao {	
	ArrayList<Integer> atomicas;
	ArrayList<ArrayList<Integer>> clausulas = new ArrayList<>();
	ArrayList<ArrayList<Integer>> resolventes = new ArrayList<>();
	ArrayList<ArrayList<Integer>> novasClausulas = new ArrayList<>();

	public Resolucao(ArrayList<String> gama, ArrayList<String> fi) {		
		for (int i = 0; i < gama.size(); i++) {			
			String partes[] = gama.get(i).split("\\s+");		
			atomicas = new ArrayList<>();
			for (int j = 0; j < partes.length - 1; j++){					
				atomicas.add(Integer.parseInt(partes[j]));					
			}				
			clausulas.add(atomicas);			
		}

		if (!(fi.isEmpty())){
			for (int i = 0; i < fi.size(); i++) {			
				String partes[] = fi.get(i).split("\\s+");		
				atomicas = new ArrayList<>();
				for (int j = 0; j < partes.length - 1; j++){					
					atomicas.add(Integer.parseInt(partes[j]) * -1); //negando  fi (se houver) e incluindo nas clausulas					
				}				
				clausulas.add(atomicas);			
			}
		}
		resolver();		
	}

	public void resolver(){
		ArrayList<ArrayList<Integer>> diferentes = new ArrayList<>();

		for (int i = 0; i < clausulas.size(); i++){ //
			Collections.sort(clausulas.get(i));
		}

		do{			
			diferentes.clear();			
			for (int i = 0; i < clausulas.size(); i++){
				for (int j = 0; j < clausulas.size(); j++){					
					resolventes.add(res(clausulas.get(i), clausulas.get(j)));					
				}		
			}			

			for (int i = 0; i < resolventes.size(); i++) {
				if(resolventes.get(i).size() == 0){
					System.out.println("RESOLVIDO!!! Consequencia Logica ou Satisfativel");
					System.exit(0);
				}				

				if(!(clausulas.contains(resolventes.get(i)))){
					//System.out.println(resolventes.get(i));
					diferentes.add(resolventes.get(i));
				}				
			}
			resolventes.clear();
			for (int i = 0; i < diferentes.size(); i++){
				if(!(clausulas.contains(diferentes.get(i))))
					clausulas.add(diferentes.get(i));
			}
		}while(!(diferentes.isEmpty()));

		System.out.println("\nNAO RESOLVIDO!!!");
		//System.out.println("Ultima Valoracao "+clausulas.get(clausulas.size()-1));
		
	}	

	public ArrayList<Integer> res(ArrayList<Integer> c1, ArrayList<Integer> c2){
		ArrayList<Integer> clausulaCompleta = new ArrayList<>();
		ArrayList<Integer> clausulaResultante = new ArrayList<>();
		ArrayList<Integer> clausulaRemovida = new ArrayList<>();		
		
		//System.out.println("C1 "+c1);
		//System.out.println("C2 "+c2);
		for (int i = 0; i < c1.size(); i++){
			for (int j = 0; j < c2.size(); j++){
				if ((c1.get(i) + c2.get(j)) != 0){
					if (!clausulaCompleta.contains(c1.get(i))){
						clausulaCompleta.add(c1.get(i));
					}			

					if (!clausulaCompleta.contains(c2.get(j))){
						clausulaCompleta.add(c2.get(j));
					}					
				}

				if ((c1.get(i) + c2.get(j)) == 0 && clausulaRemovida.size() == 0) {
					clausulaRemovida.add(c1.get(i));
					clausulaRemovida.add(c2.get(j));
					
				}	
			}
		}
		
		boolean remove;
		for (int i = 0 ; i < clausulaCompleta.size() ; i++ ) {
			remove = false;
			for (int j = 0 ; j < clausulaRemovida.size() ; j++ ) {
				if (clausulaCompleta.get(i) == clausulaRemovida.get(j))
					remove = true;
			}
			if (!remove)
				clausulaResultante.add(clausulaCompleta.get(i));
		}
		System.out.println("Clausula Completa   "+clausulaCompleta);
		System.out.println("Clausula a Remover  "+clausulaRemovida);
		System.out.println("Clausula Resultante "+clausulaResultante);
		
		Collections.sort(clausulaResultante);
		
		return clausulaResultante;
	}	
}