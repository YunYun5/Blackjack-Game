package model;

/**
 * Represents a player's hand in a game of Blackjack. Extends the Hand class to include
 * a handToString method to return the string with the cards.
 */
public class PlayerHand extends Hand {

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
}
