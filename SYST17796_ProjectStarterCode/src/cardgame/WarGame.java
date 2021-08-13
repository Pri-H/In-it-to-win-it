package cardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Priyanka
 */
public class WarGame extends Game {

    public WarGame(String givenName) {
        super(givenName);
    }
    static Player player1;
    static Player player2;
    static GroupOfCards deck = new GroupOfCards();

    public static void main(String[] args) {
        WarGame game = new WarGame("War");
        start();
        while (!player1.getHand().isEmpty() && !player2.getHand().isEmpty()) {
            game.play();
        }
    }

    @Override
    public void play() {

        declareWinner();
        System.out.println(player1.getPlayerID() + " has " + player1.getHand().size() + " cards left");
        System.out.println(player2.getPlayerID() + " has " + player2.getHand().size() + " cards left");

        System.out.println(player1.getPlayerID() + "'s card: " + player1.topCard().toString());
        System.out.println(player2.getPlayerID() + "'s card: " + player2.topCard().toString());

        ArrayList<Card> temp = new ArrayList();

        while (!tieBreaker(player1.topCard(), player2.topCard())) {
            declareWinner();
            moveTopCards(temp);
            moveTopCards(temp);
            moveTopCards(temp);
            declareWinner();
            //Changed rules from 1 card face down to 3 because the game took too long to finish otherwise
            System.out.println("The values are tied! Both players will place 3 cards face down and a card face up");
            System.out.println(player1.getPlayerID() + "'s face up card is: " + player1.topCard());
            System.out.println(player2.getPlayerID() + "'s face up card is: " + player2.topCard());

        }

        if (player1.topCard().checkCardValue() > player2.topCard().checkCardValue()) {
            declareWinner();
            moveTopCards(temp);
            System.out.println(player1.getPlayerID() + " won the round!");

            temp.forEach(card -> {
                player1.getHand().add(card);
            });
            declareWinner();
        } else {
            declareWinner();
            moveTopCards(temp);
            System.out.println(player2.getPlayerID() + " won the round!");

            temp.forEach(card -> {
                player2.getHand().add(card);
            });
            declareWinner();
        }
    }

    @Override
    public void declareWinner() {
        if (player1.getHand().isEmpty()) {
            System.out.println(player2.getPlayerID() + " has won the game!");
            end();
        } else if (player2.getHand().isEmpty()) {
            System.out.println(player1.getPlayerID() + " has won the game!");
            end();
        }
    }

    public boolean tieBreaker(Card card1, Card card2) {
        if (card1.checkCardValue() == card2.checkCardValue()) {
            return false;
        } else {
            return true;
        }

    }

    public static void start() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the game of War!");
        System.out.println("Please enter your name");
        String namePlayer1 = input.nextLine();
        player1 = new HumanPlayer(namePlayer1);
        player2 = new MachinePlayer("MachinePlayer");
        distribute();

    }

    public static void end() {
        System.out.println("Game ended");
        System.exit(0);
    }

    public static void distribute() {
        ArrayList<Card> list1 = new ArrayList();
        ArrayList<Card> list2 = new ArrayList();

        deck.createFullDeck();
        deck.shuffle();
        for (int i = 0; i < deck.showCards().size() - 1; i += 2) {
            list1.add(deck.showCards().get(i));
            list2.add(deck.showCards().get(i + 1));
        }
        player1.setHand(list1);
        player2.setHand(list2);
    }

    public boolean checkValue(Player player1, Player player2) {
        Card card1 = player1.getHand().get(0);
        Card card2 = player2.getHand().get(0);

        return card1.checkCardValue() > card2.checkCardValue();
    }

    public void moveTopCards(ArrayList<Card> temp) {
        temp.add(player1.topCard());
        temp.add(player2.topCard());
        player1.removeTopCard();
        player2.removeTopCard();
    }

}
