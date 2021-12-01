# ift1025_TP1
Correcteur de texte
    Programme JAVA ayant pour but de corriger le texte se trouvant dans un textArea en 
    cherchant chacun des mots s'y trouvant dans un dictionnaire chargé par l'utilisateur

Description des classes:

Executable
    Implémentation de l'interface graphique


InterfaceGraphique  (Classe principale)

    [26-76] CONSTRUCTEUR
    public IntructeurGraphique() 
        Le constructeur IntructeurGraphique() place dans des Jpanel: 
         -le textArea contenant le texte à corriger,        [28-33]
         -le textArea qui va contenir les 5 mots proposés   [35-41] 
         -le menu principal avec 4 items                    [45-66]
                -Dictionnaire
                -Vérifier
                -Fichier -> Ouvrir
                -Fichier -> Save
         -et ajoute ces panels dans une fenêtre             [68-74]
         
         Menu: chacun des items du menu est relié à un ActionListener. Lorsque cliqué,
               l'évènement généré est capté et traité par la méthode actionPerformed()
   
    [78-136] Méthode qui traite les ActionPerformed 
    public void actionPerformed(ActionEvent e) 
        Contient 4 statements "if", un pour chacun des items du menu
         
         
    
    
    
Comparateur

Dictionnaire

TextAreaHighlight

Tokenizer
TraitementTextArea
TraiterFichier
   
    



