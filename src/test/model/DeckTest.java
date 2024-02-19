package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    private Deck cardDeck;
    private final int AMOUNT = 1;

    @BeforeEach
    void runBefore() {
        cardDeck = new Deck(AMOUNT);

    }

    @Test
    void testDeck() {
        assertEquals(52 * AMOUNT, cardDeck.getDeckSize());
    }

    @Test
    void testCreateDeck() {
        Deck deck = new Deck(2);
        assertEquals(104, deck.getDeckSize());
    }

    @Test
    void testShuffleDeck() {
        Deck unshuffledDeck = new Deck(AMOUNT);
        Deck shuffledDeck = new Deck(AMOUNT);
        shuffledDeck.shuffleDeck();

        assertNotEquals(unshuffledDeck, shuffledDeck);
    }

    @Test
    void testGetNextCard() {
        int initialSize = cardDeck.getDeckSize();
        cardDeck.getNextCard();
        assertEquals(initialSize - 1, cardDeck.getDeckSize());
    }

    @Test
    void testGetDeckSize() {
        assertEquals(52 * AMOUNT, cardDeck.getDeckSize());
    }
}
