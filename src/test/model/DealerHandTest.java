package model;

import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerHandTest {

    @Test
    void testDealerHand() {
        DealerHand dealer = new DealerHand();
        assertEquals(0, dealer.hand.size());
    }

    @Test
    void testShouldDealerHit() {
        DealerHand dealer = new DealerHand();
        dealer.addCard(new Card(Rank.SIX, Suit.CLUBS)); // Total now 6
        assertTrue(dealer.shouldDealerHit());

        dealer.addCard(new Card(Rank.TEN, Suit.HEARTS)); // Total now 16
        assertTrue(dealer.shouldDealerHit());

        dealer.addCard(new Card(Rank.ACE, Suit.SPADES)); // Total could be 17 or 7
        assertFalse(dealer.shouldDealerHit());
    }

    @Test
    void testHandToStringFirstHand() {
        DealerHand dealer = new DealerHand();
        dealer.addCard(new Card(Rank.TEN, Suit.HEARTS)); // Assume face-up card
        dealer.addCard(new Card(Rank.ACE, Suit.SPADES)); // Assume hidden card
        String result = dealer.handToString(true);
        assertTrue(result.startsWith("[HIDDEN]"));
    }

    @Test
    void testHandToStringNotFirstHand() {
        DealerHand dealer = new DealerHand();
        dealer.addCard(new Card(Rank.TEN, Suit.HEARTS));
        dealer.addCard(new Card(Rank.ACE, Suit.SPADES));
        String result = dealer.handToString(false);
        assertFalse(result.startsWith("[HIDDEN]"));
        assertTrue(result.contains("TEN of HEARTS") && result.contains("ACE of SPADES"));
    }
}
