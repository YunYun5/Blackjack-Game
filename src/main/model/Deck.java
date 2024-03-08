package model;

import model.enums.Rank;
import model.enums.Suit;
import persistance.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents a deck of playing cards used in the game.
 * Lets you create a specified number of decks, shuffling, and dealing cards.
 */
public class Deck implements Writable {

    private int amount;
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

    // Requries: cards.size() != 0
    // Modifies: this
    // Effects: Creates a new deck with the list of cards that was given to it (used for loading game)
    public Deck(ArrayList<Card> cards) {
        this.deck = new ArrayList<>(cards);
    }

    // Modifies: this
    // Effects: Populates the deck with 13 ranks of each suit for the amount variable.
    public void createDeck() {
        for (int i = 0; i < amount; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    if (!rank.equals(Rank.NULL)) {
                        Card card = new Card(rank, suit);
                        deck.add(card);
                    }
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

    // Effects: Returns object in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray deckJsonArray = new JSONArray();
        for (Card card : this.deck) {
            deckJsonArray.put(card.toJson());
        }
        json.put("cards", deckJsonArray);
        return json;
    }
}

