package model;

import persistance.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents the dealer's hand in a game of Blackjack. Extends the Hand class
 * to include specific behaviors for the dealer, such as determining whether the
 * dealer should take another card based on Blackjack rules.
 */
public class DealerHand extends Hand implements Writable {

    // Effects: Makes a dealer with no cards in the hand using super
    public DealerHand() {
        super();
    }

    // Effects: Returns true if the dealer should hit
    public boolean shouldDealerHit() {
        return getTotal() < 17 || (isSoftHand() && getTotal() == 17);
    }

    // Effects: Returns the string that will be displayed in console of the cards of the dealer. Says hidden instead of
    // the first card if its the firstHand if not returns all the cards and the total value on the side.
    public String handToString(boolean firstHand) {
        if (firstHand) {
            return "[HIDDEN], " + hand.get(0).cardToString() + ", " + hand.get(0).getCardValue();
        } else {
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
    }

    // Effects: Returns object in JSON format
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
