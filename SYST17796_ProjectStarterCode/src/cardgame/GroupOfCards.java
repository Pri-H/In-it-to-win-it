/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package cardgame;

import cardgame.Card.Suit;
import cardgame.Card.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you
 * might want to subclass this more than once. The group of cards has a maximum
 * size attribute which is flexible for reuse.
 *
 * @author dancye
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards() {
        this.cards = new ArrayList<Card>();
    }

    public ArrayList<Card> createFullDeck() {
        //Loop Through Suits
        for (Suit cardSuit : Suit.values()) {
            //Loop through Values
            for (Value cardValue : Value.values()) {
                //Add new card to the mix
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
        return cards;
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> showCards() {
        return cards;
    }

    public void shuffle() {
        //Create a new arraylist to hold the shuffled cards temporarily
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        //Randomly pick from the old deck and copy values to the new deck
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++) {
            //gen random num according to int randomNum = rand.nextInt((max - min) + 1) + min;
            randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
            //throw random card into new deck
            tmpDeck.add(this.cards.get(randomCardIndex));
            //remove picked from old deck
            this.cards.remove(randomCardIndex);
        }
        //set this.deck to our newly shuffled deck
        this.cards = tmpDeck;
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public void setSize(int givenSize) {
        size = givenSize;
    }

}//end class
