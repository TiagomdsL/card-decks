
public class Carta{
    private String carta;
    private String traducao;
    private int vezesAcertada;
    private boolean aprendida;
    
    public Carta (String carta, String trad){    // cria cartas
        this.carta = carta;
        this.traducao = trad;
        this.vezesAcertada = 0;
        this.aprendida = false;
    }
    
    public Carta(String cartaString) {		// load cartas
        String[] parts = cartaString.split(" ");
        if (parts.length == 4) {
            this.carta = parts[0];
            this.traducao = parts[1];
            this.vezesAcertada = Integer.parseInt(parts[2]);
            this.aprendida = Boolean.parseBoolean(parts[3]);
        } else {
            throw new IllegalArgumentException("Invalid input format for Carta");
        }
    }

    public String getCarta (){
        return this.carta;
    } 

    public String getTrad (){
        return this.traducao;
    } 
    
    public boolean getAprendida (){
        return this.aprendida;
    } 

    public void acerta(){
        this.vezesAcertada++;
        if(this.vezesAcertada > 5 && !this.aprendida){
            this.aprendida = true;
            System.out.println("Parabéns aprendeste esta carta!");
        }
    }

    public void erra(){
        this.vezesAcertada--;
        if(this.vezesAcertada < 4 && this.aprendida){
            this.aprendida = false;
            System.out.println("Ups! parece que te esqueceste desta carta continua a acerta-la para a aprenderes denovo!");
        } 
    }
    public String toString() {
    	return this.carta + " " + this.traducao + " " + this.vezesAcertada + " " + this.aprendida; 
    }
    
}