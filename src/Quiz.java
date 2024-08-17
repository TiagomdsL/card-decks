import java.util.*;

public class Quiz{
    Deck deck;
    int rounds;
    int score;
    int rondasStart;
    
    public Quiz (Deck deck, int rondas){
        this.deck = deck;
        this.rounds = rondas;
        this.rondasStart = rondas;
        this.score = 0;
    }

    public void question(Carta card, Scanner sc){
        System.out.println(card.getCarta() + "\n Qual a tradução desta carta?");
        if(sc.next().equals(card.getTrad())){
            this.score++; 
            System.out.println("Correto!\n" + this.score + "pontos");
            card.acerta(); 
        }
        else{
            System.out.println("Errado.\n" + this.score + "pontos");
            card.erra();
        }
        this.rounds--;
    }

    public void quizGame(Scanner sc){
        while(this.rounds > 0){
            question(deck.cartaAleatoria(), sc);
        }
        System.out.println("Fim do quiz \n Pontuação: " + this.score + "\n Acertaste " + ((this.score / this.rondasStart) * 100) + "% das perguntas!");
    }
}