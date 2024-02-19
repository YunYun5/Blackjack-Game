package model;

import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerHandTest {

    private PlayerHand hand;

    @BeforeEach
    void runBefore() {
        hand = new PlayerHand();
    }

    @Test
    void testPlayerHand() {
        PlayerHand ph = new PlayerHand();
        assertEquals(0, ph.hand.size());
    }

    @Test
    void testHandToStringEmpty() {
        assertEquals(", 0", hand.handToString());
    }

    @Test
    void handToStringSingleCard() {
        hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
        assertEquals("TWO of HEARTS, 2", hand.handToString());
    }

    @Test
    void handToStringMultipleCards() {
        hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand.addCard(new Card(Rank.THREE, Suit.SPADES));
        String expected = "TWO of HEARTS, THREE of SPADES, 5";
        assertEquals(expected, hand.handToString());
    }

    @Test
    void handToStringWithSoftHand() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.TWO, Suit.DIAMONDS));
        String expected = "ACE of HEARTS, TWO of DIAMONDS, 3/13";
        assertEquals(expected, hand.handToString());
    }

    @Test
    void handToStringNoSoftHand() {
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE, Suit.DIAMONDS));
        assertEquals("TEN of HEARTS, NINE of DIAMONDS, 19", hand.handToString());
    }
}

