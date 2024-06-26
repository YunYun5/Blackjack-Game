package model;

import model.enums.Rank;
import model.enums.Suit;
import persistance.Writable;
import org.json.JSONObject;

/**
 * Represents a single playing card with a suit and rank.
 * This class provides methods to determine the card's value in the game,
 * as well as to check if the card is an Ace or a face card.
 */
public class Card implements Writable {
    private final Rank rank;
    private final Suit suit;

    // Requires that rank and suit both exist in the enunms
    // Modifies: this
    // Effects: Creates a single card with a specific rank and suit
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Effects: Returns the cards value, 10 for face cards, 11 for ace, and corresponding numbers for the rest
    public int getCardValue() {
        if (isFaceCard()) {
            return 10;
        } else if (isAce()) {
            return 11;
        } else {
            switch (this.rank) {
                case TWO: return 2;
                case THREE: return 3;
                case FOUR: return 4;
                case FIVE: return 5;
                case SIX: return 6;
                case SEVEN: return 7;
                case EIGHT: return 8;
                case NINE: return 9;
                case TEN: return 10;
                default:
                    return 0;
            }
        }
    }

    // Effects: return true if it is a face card
    public boolean isFaceCard() {
        return this.rank == Rank.JACK || this.rank == Rank.QUEEN || this.rank == Rank.KING;
    }

    // Effects: return true if it is a ace
    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    // Effects: return the card as a string with the rank and suit seperated by "of"
    public String cardToString() {
        return this.rank + " of " + this.suit;
    }

    // Effects: return the path of the card in the data folder
    public String cardToImagePathString() {
        return (this.rank + "_of_" + this.suit).toLowerCase();
    }

    // Effects: Returns object in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("rank", rank.toString());
        json.put("suit", suit.toString());
        return json;
    }
}
