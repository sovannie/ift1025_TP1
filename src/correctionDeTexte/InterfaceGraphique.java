package correctionDeTexte;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

public class InterfaceGraphique  extends JFrame implements MouseListener,ActionListener{

	
	JTextArea textArea, suggestBox;
	JPanel p_txtArea,p_menu,p_suggest;
	JMenu menu, submenu;  
    JMenuItem i_dictionnaire, i_verifier, i_ouvrir, i_save;  
    JFrame f= new JFrame("Correcteur de texte"); 

	//Constructeur
	public InterfaceGraphique() {

	     //creation textArea
	     textArea = new JTextArea(20,50);
		 textArea.addMouseListener(this);
		 p_txtArea = new JPanel(new FlowLayout());
		 p_txtArea.add(textArea);
		 p_txtArea.setBorder(new TitledBorder("Texte à corriger"));
		
		 //creation textArea pour suggestions de mots
		 suggestBox = new JTextArea(10,50);
		 suggestBox.addMouseListener(this);
		 suggestBox.setEditable(false); //empêche l'utilisateur d'écrire dans la boîte de suggestion
		 p_suggest = new JPanel(new FlowLayout());
		 p_suggest.add(suggestBox);
		 p_suggest.setBorder(new TitledBorder("Commentaires/Suggestions"));
		 
		 
		 
	     //creation menu     
		 JMenuBar mb=new JMenuBar();  
	     menu=new JMenu("Menu"); 
	     submenu=new JMenu("Fichier");  
	     i_dictionnaire=new JMenuItem("Dictionnaire");  
	     i_verifier=new JMenuItem("Vérifier");  
	     i_ouvrir=new JMenuItem("Ouvrir");  
	     i_save=new JMenuItem("Sauvegarder");  
	     menu.add(i_dictionnaire); menu.add(i_verifier);  
	     submenu.add(i_ouvrir); submenu.add(i_save);  
	     menu.add(submenu);  
	     mb.add(menu); 

	     //ajout ActionListener
	     i_dictionnaire.addActionListener(this);
	     i_verifier.addActionListener(this);
	     i_ouvrir.addActionListener(this);
	     i_save.addActionListener(this);
	     	     
	     p_menu = new JPanel(); //panel contenant le menu
	     p_menu.setLayout(new BorderLayout());	
		 p_menu.add(mb, BorderLayout.NORTH);

	     //creation fenetre contenant les différents panels
		 f.add(p_txtArea,BorderLayout.CENTER);
	     f.add(p_menu,BorderLayout.NORTH); 
	     f.add(p_suggest,BorderLayout.SOUTH);
	     f.setSize(550,600);    
	     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     f.setVisible(true); 

	}
	
    public HashSet<String> dictionnaire = new HashSet<>(); 
	public String[] dictionnaireTab;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//actionPerformed quand le bouton "dictionnaire" est selectionné
		if (e.getSource() == i_dictionnaire){
			
			try {
				suggestBox.setText("Dictionnaire en cours de chargement...\nMerci pour votre patience!");
				Dictionnaire d = new Dictionnaire();
				dictionnaire = d.dictionnaire; //hashSet du dictionnaire
				dictionnaireTab = dictionnaire.toArray(new String[dictionnaire.size()]);	//String[] du dictionnaire	
				suggestBox.setText("Dictionnaire chargé. \nÉcrivez/choisissez un fichier pour le faire corriger!");				
				}
			 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
		}
		
		//actionPerformed quand le bouton "vérifier" est selectionné
		if (e.getSource() == i_verifier){
			
			TraitementTextArea tta = new TraitementTextArea(textArea,suggestBox,dictionnaireTab, dictionnaire);
			try {
				tta.verifier();
				suggestBox.setText("Texte vérifié. \nSelectionnez un mot pour le corriger.");	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				suggestBox.setText("Correction impossible.\nVeuillez charger un dictionnaire!" );
			}
		}
		
		//actionPerformed quand le bouton "ouvrir" un fichier est selectionné
		if (e.getSource() == i_ouvrir){
		
			TraiterFichier file = new TraiterFichier();
		    String texteLu;
		    
		    try {
			    texteLu = file.lireFichier(); //string contient toutes les lignes du fichier lu
			    textArea.setText(texteLu); //Place le texte lu dans le textArea
		        } catch (IOException e1) {
			      e1.printStackTrace();
		          }
		}
		
		//actionPerformed quand le bouton "enregistrer" un fichier est selectionné
		if (e.getSource() == i_save){
			
			String textToSave = textArea.getText(); //stockage du texte corrigé qu'on veut sauvegarder dans un fichier
			TraiterFichier file = new TraiterFichier();
			file.saveInAFile(textToSave);
			suggestBox.setText("Texte sauvegardé. \nÀ la prochaine!");
		}
		
	}
		

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		//Traitement pour un mot selectionné à corriger
		if (e.getSource() == textArea) {
           
                try{
                   //Lignes données par Jian-Yun Nie pour reconnaître mot selectionné
                    int offset = textArea.viewToModel( e.getPoint() );
                    int start = Utilities.getWordStart(textArea,offset);
                    int end = Utilities.getWordEnd(textArea, offset);
                    String word = textArea.getDocument().getText(start, end-start);
                    textArea.select(start, end);

                    TraitementTextArea tta = new TraitementTextArea(textArea,suggestBox,dictionnaireTab,dictionnaire);
                    tta.motsPlusProche(word); //méthode servant à proposer à l'utilisateur les mots plus proches du dictionnaire
                }
                catch (Exception e2) {}
           
		}//fin du if textArea
		
		//Traitement pour remplacer mot mal écrit par correction choisie
		if (e.getSource() == suggestBox) {
			
            try {//Lignes données par Jian-Yun Nie pour reconnaître mot selectionné
            	int offset = suggestBox.viewToModel( e.getPoint() );
				int start1 = Utilities.getWordStart(suggestBox,offset);
	            int end1 = Utilities.getWordEnd(suggestBox, offset);
	            String motChoisi = suggestBox.getDocument().getText(start1, end1-start1); //enregistre dans un String la correction choisie
	            suggestBox.select(start1, end1);
                textArea.replaceSelection(motChoisi); //remplace le mot mal écrit par la correction choisie
                
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


            
            //Vérifie le texte avec le nouveau mot corrigé 
            TraitementTextArea tta = new TraitementTextArea(textArea,suggestBox,dictionnaireTab, dictionnaire);
			try {
				tta.verifier();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			suggestBox.setText("Mot corrigé.");  //clear le texte se trouvant dans la suggestBox
            
            
		}// fin du if suggestBox
		
	}//fin du mouseClicked
		
  

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	




}