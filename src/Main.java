import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Decks app = arguments(args, sc);
		System.out.println("Introduza quiz, para começar um jogo \n" + "Introduza save, para guardar os seus decks");
		System.out.println("Introduza adiciona, para adicionar cartas \n" + "Introduza troca, para mudar a tradução de uma carta");
		System.out.println("Introduza sair, para sair \n" + "Introduza novaLingua, para adicionar uma nova lingua");
		while(true) {
			switch (sc.next()) {
			case  "quiz":
				System.out.println("Que liguagem deseja ser questionado em ?");
				Deck deck = app.decks(sc.next());
				System.out.println("Quantas rondas tendo em conta que tem " + deck.tamanho() + " cartas no deck ?");
				Quiz quiz = new Quiz(deck, sc.nextInt());
				quiz.quizGame(sc);
				break;	
			case "save":
				System.out.println("Introduza a pasta onde deseja guardas os seus decks.");
				File file = new File(sc.next());
				try {
					app.saveDecks(file);
				} catch (FileNotFoundException e) {
					System.err.println("A pasta não foi encontrada!");
				}
				break;
			case "novaLingua":
				System.out.println("Insira a linguagem que quer adicionar");
				String linguaNova = sc.next();
				app.adicionaLinguagem(linguaNova);
				System.out.println("A nova lingua foi adicionada com sucesso");
				break;
			case "adiciona":
				System.out.println("Insira a linguagem á qual adicionar a carta");
				String lingua = sc.next();
				System.out.println("Insira a carta a adicionar");
				String carta = sc.next();
				System.out.println("Insira a sua tradução");
				String trad = sc.next();
				Carta card = new Carta(carta, trad);
				app.decks(lingua).adiciona(card);
				System.out.println("A carta " + carta + " " + trad + " foi adicionada com sucesso");
				break;
			case "troca":
				System.out.println("Insira a linguagem á qual trocar a carta");
				String linguaTroca = sc.next();
				System.out.println("Insira a carta a trocar");
				String cartaTroca = sc.next();
				System.out.println("Insira a sua tradução");
				String tradTroca = sc.next();
				app.decks(linguaTroca).trocaCarta(cartaTroca, tradTroca);
				System.out.println("A carta " + cartaTroca + " " + tradTroca + " foi trocada com sucesso");
				break;
			case "sair":
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Introduza quiz, para começar um jogo \n" + "Introduza save, para guardar os seus decks");
				System.out.println("Introduza adiciona, para adicionar cartas \n" + "Introduza troca, para mudar a tradução de uma carta");
				System.out.println("Introduza sair, para sair");
				break;
			}
		}
	}
	
	public static Decks loadFile(Scanner sc){
		System.out.println("Insira o caminho até o seu arquivo");
		String link = sc.next();
		System.out.println("Insira o seu nome de usuário");
		String user = sc.next();
		System.out.println("Insira a sua password");
		String pass = sc.next();
		Decks app = new Decks(link, user, pass);
		return app;
	}
	public static Decks def(Scanner sc) {
		System.out.println("Insira o seu nome de usuário");
		String user = sc.next();
		System.out.println("Insira a sua password");
		String pass = sc.next();
		System.out.println("Insira a linguagem que pretende aprender");
		String lingua = sc.next();
		Decks deck = new Decks(user, pass);
		deck.adicionaLinguagem(lingua);
		return deck;
	}
	public static Decks arguments(String[] args, Scanner sc) {
		Decks app;
		if(args.length > 0) {
			switch(args[0]) {
			case "load":
				app = loadFile(sc);
				return app;
			case "login":
				app = def(sc);
				return app;	
			default:
				System.out.println("Argumentos inválidos, insira load ou login para o programa funcionar");
				break;
			}
		}
		else {
			app = def(sc);
			return app;
		}
		return null;
	}
}
	
