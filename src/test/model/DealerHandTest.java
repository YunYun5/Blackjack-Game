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
    void testHandToStringEmpty() {
        DealerHand dealer = new DealerHand();
        assertEquals(", 0", dealer.handToString(false));
    }

    @Test
    void testShouldDealerHit() {
        DealerHand dealer = new DealerHand();
        dealer.addCard(new Card(Rank.SIX, Suit.CLUBS));
        assertTrue(dealer.shouldDealerHit());

        dealer.addCard(new Card(Rank.ACE, Suit.HEARTS));
        assertTrue(dealer.shouldDealerHit());

        dealer.clear();
        dealer.addCard(new Card(Rank.TEN, Suit.HEARTS));
        dealer.addCard(new Card(Rank.TEN, Suit.CLUBS));
        assertFalse(dealer.shouldDealerHit());

        dealer.clear();
        dealer.addCard(new Card(Rank.EIGHT, Suit.CLUBS));
        dealer.addCard(new Card(Rank.ACE, Suit.SPADES));
        assertFalse(dealer.shouldDealerHit());

    }

    @Test
    void testHandToStringFirstHand() {
        DealerHand dealer = new DealerHand();
        dealer.addCard(new Card(Rank.TEN, Suit.HEARTS));
        dealer.addCard(new Card(Rank.ACE, Suit.SPADES));
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

    @Test
    void handToStringNoSoftHand() {
        DealerHand dealer = new DealerHand();
        dealer.addCard(new Card(Rank.TEN, Suit.HEARTS));
        dealer.addCard(new Card(Rank.NINE, Suit.DIAMONDS));
        assertEquals("TEN of HEARTS, NINE of DIAMONDS, 19", dealer.handToString(false));
    }


}
