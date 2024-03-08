package persistance;

import model.*;
import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // no exception excpected
        }
    }

    @Test
    void testReaderEmptyGameState() {
        try {
            GameState gState = new GameState(new Player(1000), new DealerHand(), new Deck(1));
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyGameState.json");
            writer.open();
            writer.write(gState);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyGameState.json");
            GameState gameState = reader.read();
            assertNotNull(gameState.getPlayer());
            assertNotNull(gameState.getDealerHand());
            assertNotNull(gameState.getDeck());
        } catch (IOException e) {
            fail("Failed to read");
        }
    }

    @Test
    void testReaderGeneralGameState() {
        try {
            PlayerHand hand = new PlayerHand();
            hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
            DealerHand dHand = new DealerHand();
            dHand.addCard(new Card(Rank.TWO, Suit.SPADES));
            GameState gState = new GameState(new Player(1000, hand, 0), dHand, new Deck(1));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralGameState.json");
            writer.open();
            writer.write(gState);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralGameState.json");
            GameState gameState = reader.read();
            Player player = gameState.getPlayer();
            DealerHand dealerHand = gameState.getDealerHand();
            Deck deck = gameState.getDeck();

            assertEquals(1000, player.getBalance());
            assertFalse(player.getHand().getCards().isEmpty());
            assertFalse(dealerHand.getCards().isEmpty());
            assertTrue(deck.getDeckSize() > 0);
        } catch (IOException e) {
            fail("Failed to read from a general game state file.");
        }
    }
}
