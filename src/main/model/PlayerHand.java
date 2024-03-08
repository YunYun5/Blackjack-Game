package model;

import persistance.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a player's hand in a game of Blackjack. Extends the Hand class to include
 * a handToString method to return the string with the cards.
 */
public class PlayerHand extends Hand implements Writable {

    // Effects: Creates a empty hand using super
    public PlayerHand() {
        super();
    }

    // Effects: Returns the string that will be displayed in console when showing the player cards and the total value
    // of the hand.
    public String handToString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card.cardToString()).append(", ");
        }
        if (!hand.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        if (isSoftHand()) {
            return sb + ", " + (getTotal() - 10) + "/" + getTotal();
        } else {
            return sb + ", " + getTotal();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray cardsJsonArray = new JSONArray();
        for (Card card : hand) {
            cardsJsonArray.put(card.toJson());
        }
        json.put("cards", cardsJsonArray);
        return json;
    }
}
