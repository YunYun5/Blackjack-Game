package model;

import java.util.ArrayList;

/**
 * Represents a hand in a Blackjack game, containing a list of cards.
 * Provides functionality to add cards, calculate the total value of the hand,
 * determine if the hand is a blackjack, busted, or a soft hand, and to clear the hand of cards.
 */
public class Hand {

    ArrayList<Card> hand;

    // Effects: Creates a empty list of cards as a hand.
    public Hand() {
        this.hand = new ArrayList<>();
    }

    // Modifies: this
    // Effects: Adds a card to the hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Effects: Return the total value of the hand by adding up the value of all the cards. Takes into account aces
    // being both 1 and 11
    public int getTotal() {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            int value = card.getCardValue();
            total += value;

            if (card.isAce()) {
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    // Effects: Returns true if the hand is busted (value over 21)
    public boolean isBusted() {
        return getTotal() > 21;
    }

    // Effects: Returns true if there was a blackjack. (first 2 cards add upto 21)
    public boolean isBlackjack() {
        return hand.size() == 2 && getTotal() == 21;
    }

    // Effects: Returns true if the hand is considered a soft hand.
    public boolean isSoftHand() {
        int total = 0;
        boolean hasAce = false;

        for (Card card : hand) {
            int value = card.getCardValue();
            total += value;

            if (card.isAce()) {
                hasAce = true;
            }
        }

        return hasAce && total <= 21;
    }

    // Effects: Clears the cards in the hand.
    public void clear() {
        hand.clear();
    }
}
