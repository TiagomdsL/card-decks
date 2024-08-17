import java.util.*;
import java.io.*;

public class Decks {
    Map<String, Deck> language;
    String user;
    String password;


    public Decks (String user, String pass){
        this.user = user;
        this.password = pass; 
        this.language = new HashMap<String, Deck>();
    }
    
    public Decks (String filePath, String expectedUser, String expectedPassword) { // pese ao user as merdas e dps cria
    	this.language = new HashMap<String, Deck>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            // Split the content into parts
            String[] parts = fileContent.toString().split("-----------------------------------------\n");

            // Check if the user and password match
            if (parts.length > 0 && parts[parts.length - 1].strip().equals(expectedUser + " " + expectedPassword)) {
                // Initialize Decks object from the string representation
            	this.user = expectedUser;
                this.password = expectedPassword;
                for (int i = 0; i < parts.length - 1; i+=2) {
                    String language = parts[i].strip();
                    String deckString = parts[i+1];
                    this.language.put(language, new Deck(deckString));
                } 
            } else{
            	System.out.println("Erro.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void criaDeck(String linguagem){
        this.language.put(linguagem,new Deck());
    }
    
    public void adicionaLinguagem(String linguagem){
    	Deck deck = new Deck();
        this.language.put(linguagem, deck);
    }

    public Deck decks(String linguagem){
        return this.language.get(linguagem);
    }

    public void alterarCarta(String linguagem, String carta, String traducao){
        if(!this.language.get(linguagem).temCarta(carta))
            this.language.get(linguagem).trocaCarta(carta, traducao);
        else
            System.out.println("A carta não existe.");
    }
    public String toString() {
    	String[] keys = this.language.keySet().toArray(new String[this.language.size()]);
    	StringBuilder sb = new StringBuilder();
    	for(String c : keys) {
    		sb.append(c + "\n" + "-----------------------------------------" + "\n" + this.language.get(c).toString() );
    		sb.append("\n");
    		sb.append("-----------------------------------------");
    	}
    	sb.append(user + " " + password);
    	return sb.toString();
    }
    public void saveDecks(File ficheiro) throws FileNotFoundException {
    	File save = new File(ficheiro.toString().concat("/" + this.user + ".txt"));
    	PrintWriter pw = new PrintWriter(save);
    	pw.println(toString());
    	pw.close();
    }
} 