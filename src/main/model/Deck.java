package model;

import model.enums.Rank;
import model.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents a deck of playing cards used in the game.
 * Lets you create a specified number of decks, shuffling, and dealing cards.
 */
public class Deck {

    private final int amount;
    private final ArrayList<Card> deck;

    // Requires: amount is greater 0
    // Modifies: this
    // Effects: Creates a new deck with a empty list of cards and the given amount. Then uses the createDeck to populate
    // the list of cards. Then shuffles the deck using the shuffleDeck method.
    public Deck(int amount) {
        this.amount = amount;
        this.deck = new ArrayList<>();
        createDeck();
        shuffleDeck();
    }

    // Modifies: this
    // Effects: Populates the deck with 13 ranks of each suit for the amount variable.
    public void createDeck() {
        for (int i = 0; i < amount; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    Card card = new Card(rank, suit);
                    deck.add(card);
                }
            }
        }
    }

    // Modifies: this
    // Effects: shuffles the deck of cards by shuffling the list
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    // Modifies: this
    // Effects; Returns the next card on the list and removes it from the deck
    public Card getNextCard() {
        Card toReturn = this.deck.get(0);
        this.deck.remove(toReturn);
        return toReturn;
    }

    // Effects: Returns the amount of cards in the deck
    public int getDeckSize() {
        return this.deck.size();
    }
}

