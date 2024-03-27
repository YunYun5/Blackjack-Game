package model;

import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCard() {
        Card testCard = new Card(Rank.QUEEN, Suit.HEARTS);
        assertEquals(Rank.QUEEN, testCard.getRank());
        assertEquals(Suit.HEARTS, testCard.getSuit());

        Card testCard2 = new Card(Rank.FIVE, Suit.CLUBS);
        assertEquals(Rank.FIVE, testCard2.getRank());
        assertEquals(Suit.CLUBS, testCard2.getSuit());
    }

    @Test
    void testGetCardValue() {
        Card king = new Card(Rank.KING, Suit.HEARTS);
        assertEquals(10, king.getCardValue());

        Card queen = new Card(Rank.QUEEN, Suit.DIAMONDS);
        assertEquals(10, queen.getCardValue());

        Card jack = new Card(Rank.JACK, Suit.SPADES);
        assertEquals(10, jack.getCardValue());

        Card ace = new Card(Rank.ACE, Suit.CLUBS);
        assertEquals(11, ace.getCardValue());

        assertEquals(2, new Card(Rank.TWO, Suit.HEARTS).getCardValue());
        assertEquals(3, new Card(Rank.THREE, Suit.DIAMONDS).getCardValue());
        assertEquals(4, new Card(Rank.FOUR, Suit.SPADES).getCardValue());
        assertEquals(5, new Card(Rank.FIVE, Suit.CLUBS).getCardValue());
        assertEquals(6, new Card(Rank.SIX, Suit.HEARTS).getCardValue());
        assertEquals(7, new Card(Rank.SEVEN, Suit.DIAMONDS).getCardValue());
        assertEquals(8, new Card(Rank.EIGHT, Suit.SPADES).getCardValue());
        assertEquals(9, new Card(Rank.NINE, Suit.CLUBS).getCardValue());
        assertEquals(10, new Card(Rank.TEN, Suit.HEARTS).getCardValue());

        assertEquals(0, new Card(Rank.NULL, Suit.HEARTS).getCardValue());
    }

    @Test
    void testIsFaceCard() {
        assertTrue(new Card(Rank.JACK, Suit.SPADES).isFaceCard());
        assertTrue(new Card(Rank.QUEEN, Suit.HEARTS).isFaceCard());
        assertTrue(new Card(Rank.KING, Suit.DIAMONDS).isFaceCard());
        assertFalse(new Card(Rank.TEN, Suit.CLUBS).isFaceCard());
    }

    @Test
    void testIsAce() {
        assertTrue(new Card(Rank.ACE, Suit.SPADES).isAce());
        assertFalse(new Card(Rank.KING, Suit.HEARTS).isAce());
    }
    
    @Test
    void testcardToImagePathString() {
        assertEquals("ace_of_spades", new Card(Rank.ACE, Suit.SPADES).cardToImagePathString());
    }

    @Test
    void testGetSuit() {
        assertEquals(Suit.HEARTS, new Card(Rank.TEN, Suit.HEARTS).getSuit());
        assertEquals(Suit.DIAMONDS, new Card(Rank.ACE, Suit.DIAMONDS).getSuit());
    }

    @Test
    void testGetRank() {
        assertEquals(Rank.ACE, new Card(Rank.ACE, Suit.SPADES).getRank());
        assertEquals(Rank.TEN, new Card(Rank.TEN, Suit.CLUBS).getRank());
    }

    @Test
    void testCardToString() {
        assertEquals("ACE of SPADES", new Card(Rank.ACE, Suit.SPADES).cardToString());
        assertEquals("TEN of HEARTS", new Card(Rank.TEN, Suit.HEARTS).cardToString());
    }
}