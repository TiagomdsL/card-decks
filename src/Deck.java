import java.util.*;

public class Deck implements Iterable<Carta> {
    private Carta[] deck;
    private int fim;

    public Deck() {					// cria
        this.deck = new Carta[100];
        this.fim = 0;
    }
    
    public Deck(String deckString) {   // load 
        String[] parts = deckString.split("\n");
        this.deck = new Carta[100];
        // Initialize deck from the string representation
        for (int i = 0; i < parts.length - 1; i++) {
            Carta carta = new Carta(parts[i]);
            adiciona(carta);
        }

        // Set the 'fim' value
        this.fim = Integer.parseInt(parts[parts.length - 1]);
    }
    
    public int tamanho() {
    	return this.fim;
    }
    
    public void adiciona(Carta card) {
        this.deck[fim] = card;
        fim++;
    }

    public boolean temCarta(String card){
    	for(int i = 0; i < this.fim; i++){
            if(deck[i].getCarta().equals(card))
                return true;
        }
        return false;
    }

    public void trocaCarta (String carta, String trad){
        if(temCarta(carta)){
            for(int i = 0; i < this.fim; i++){
                if(deck[i].getCarta().equals(carta))
                    this.deck[i] = new Carta(carta, trad);
            }
        }
        else 
        	System.out.println("Não tens essa carta!");
    }
    
    public Carta cartaAleatoria (){
        Random rd = new Random();
        return this.deck[rd.nextInt(this.fim)];
    }

    @Override
    public Iterator<Carta> iterator() {
        return new DeckIterator();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fim);
        result = 31 * result + Arrays.hashCode(deck);
        return result;
    }

    private class DeckIterator implements Iterator<Carta> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < fim;
        }

        @Override
        public Carta next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return deck[current++];
        }
    }
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < this.fim; i++) {
    		sb.append(this.deck[i].toString());
    		sb.append("\n");
    	}
    	sb.append(this.fim);
    	return sb.toString();    
    }
}