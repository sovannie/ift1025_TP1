package correctionDeTexte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JTextArea;
import javax.swing.text.*;

public class TraitementTextArea {
	
	private JTextArea textArea, suggestBox; 
	public ArrayList<String> listeMotTextArea; //stockage sous forme de token de tous les mots se trouvant dans textArea
	public HashSet<String> erreurs; //stockage des mots du textArea ne se trouvant pas dans le dictionnaire
	public HashSet<String> dictionnaire = new HashSet<>(); //variable locale contenant le dictionnaire
	public int[] distances; //stockage de la distance avec chacun des mots du dictionnaire
	public String[] dictionnaireTab;
	
	
	public TraitementTextArea(JTextArea textArea,JTextArea suggestBox, String[] dictionnaireTab,HashSet<String> dictionnaire) {
		this.textArea = textArea;
		this.suggestBox = suggestBox;
		this.dictionnaireTab = dictionnaireTab;
		this.dictionnaire = dictionnaire;
		try {
			this.distances = new int[dictionnaireTab.length];
		}
		catch(Exception e){
			suggestBox.setText("Impossible de corriger.\nVeuillez charger un dictionnaire avant!");
	
		}
	}

	public void verifier() throws IOException {
		/*
		 * méthode qui analyse le texte se trouvant dans textArea et surligne les mots non reconnus
		 */
		
		listeMotTextArea = Tokenizer.creerTokens(textArea.getText()); //Convertir chacun des mots du textArea en une liste de Token
		erreurs = Comparateur.comparer(listeMotTextArea,dictionnaire); //Comparer chacun des token (mots du textArea) avec ceux du dictionnaire
		
		TextAreaHighlight h = new TextAreaHighlight(textArea); //implémenter un surligneur
		
		h.removeHighlights(textArea);
		
		//surligne les mots dans erreurs
		for(String mot: erreurs) {
			h.highlight(mot);			
		}

	}
	
	
	
	
	public void motsPlusProche(String mot) {
		/*
		 * methode qui trouve les 5 mots les plus proches et les affiche dans le textArea "Suggestion"
		 */
		

		//Boucle for qui enregistre dans un tableau toutes les distances entre le mot et ceux du dictionnaire
		for(int i=0; i<dictionnaireTab.length; i++) {
			distances[i] = distance(mot,dictionnaireTab[i]); 			
		}
			
			
		int[] index_of_minvalues = min5(distances); //tab regroupant indices des 5 mots aux distances minimales
		
		
		String motPropose = "Vouliez-vous plutôt écrire:\n"; //stockage des mots à proposer
		for (int i=0; i<5; i++) {
			motPropose+=dictionnaireTab[index_of_minvalues[i]]+"\n";			
		}
		suggestBox.setText(motPropose);
			
	}
	
	
 
	public static int distance(String s1, String s2){
		/*
		 * calcule et retourne la distance de Levenshtein (copié-collé sur https://gist.github.com/gabhi/11243437)
		 */
		
	     int edits[][]=new int[s1.length()+1][s2.length()+1];
	     
	     for(int i=0;i<=s1.length();i++)
	         edits[i][0]=i;
	     for(int j=1;j<=s2.length();j++)
	         edits[0][j]=j;
	     for(int i=1;i<=s1.length();i++){
	         for(int j=1;j<=s2.length();j++){
	             int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
	             edits[i][j]=Math.min(
	                             (edits[i-1][j]+1),
	                             Math.min(
	                                (edits[i][j-1]+1),
	                                (edits[i-1][j-1]+u)
	                             )
	                          );
	         }
	     }
	     return edits[s1.length()][s2.length()]; //retour de la distance de Levenshtein
	}
	
	
    
	public static int[] min5(int[] distances){
		/*
		 * méthode qui trouve les 5 valeurs minimales dans un tableau et retourne leurs indices
		 */
		
		int[] index_of_minvalues = new int[5];
		
        //stockage des indices des 5 valeurs minimales trouvées (ordre croissant)
		for (int i=0; i<5; i++) {
			int indice = minTab(distances); //stockage de l'indice de la valeur minimale du tableau "distances"
			index_of_minvalues[i] = indice;
			distances[indice] = Integer.MAX_VALUE; //pour ne pas retrouver la même valeur à l'itératon
			
		}
		return index_of_minvalues;	
		
	}
	   

	public static int minTab(int[] tab) {
		/*
		 * méthode qui trouve la valeur minimale dans le tableau "tab" et retourne son indice
		 */
	       
		   int minVal = Integer.MAX_VALUE;
	       int minValIndice = 0;    
	  
	       for(int i = 0; i < tab.length; i++){
	         
	         if(tab[i] < minVal) {
	           minValIndice = i;
	           minVal = tab[i];
	         }
	
	       }
	       return minValIndice;
	  
	 }
	

}

