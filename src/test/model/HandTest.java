package model;

import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    private Hand hand;

    @BeforeEach
    void runBefore() {
        hand = new Hand();
    }

    @Test
    void testHand() {
        Hand testHand = new Hand();
        assertEquals(0, testHand.hand.size());
    }

    @Test
    void testAddCard() {
        Card card = new Card(Rank.THREE, Suit.HEARTS);
        hand.addCard(card);
        assertEquals(1, hand.hand.size());
    }

    @Test
    void testGetTotalWithAceAsEleven() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE, Suit.SPADES));
        assertEquals(20, hand.getTotal());
    }

    @Test
    void testGetTotalWithAceAsOne() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.KING, Suit.SPADES));
        hand.addCard(new Card(Rank.TWO, Suit.DIAMONDS));
        assertEquals(13, hand.getTotal());
    }

    @Test
    void testIsBusted() {
        hand.addCard(new Card(Rank.KING, Suit.SPADES));
        hand.addCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        assertTrue(hand.isBusted());
    }

    @Test
    void testIsNotBusted() {
        hand.addCard(new Card(Rank.NINE, Suit.HEARTS));
        hand.addCard(new Card(Rank.TWO, Suit.SPADES));
        assertFalse(hand.isBusted());
    }

    @Test
    void testIsBlackjack() {
        hand.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS));
        assertTrue(hand.isBlackjack());
    }

    @Test
    void testIsNotBlackjack() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.TWO, Suit.SPADES));
        assertFalse(hand.isBlackjack());
    }

    @Test
    void testIsSoftHand() {
        hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.THREE, Suit.CLUBS));
        assertTrue(hand.isSoftHand());
    }

    @Test
    void testIsNotSoftHand() {
        hand.addCard(new Card(Rank.KING, Suit.HEARTS));
        hand.addCard(new Card(Rank.EIGHT, Suit.SPADES));
        assertFalse(hand.isSoftHand());
    }

    @Test
    void testClear() {
        hand.addCard(new Card(Rank.KING, Suit.SPADES));
        hand.clear();
        assertEquals(0, hand.hand.size());
    }
}
