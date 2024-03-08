package persistance;

import model.*;
import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\0invalidFileName.json");
            writer.open();
            fail("FileNotFoundException was expected due to invalid file name");
        } catch (FileNotFoundException e) {
            // Expected exception, test passes
        }
    }

    @Test
    void testWriterWithGameState() {
        try {

            Player player = new Player(1000);
            player.getHand().addCard(new Card(Rank.ACE, Suit.HEARTS));

            DealerHand dHand = new DealerHand();
            dHand.addCard(new Card(Rank.TWO, Suit.CLUBS));

            GameState gameState = new GameState(player, dHand, new Deck(1));

            JsonWriter writer = new JsonWriter("./data/testPlayer.json");
            writer.open();
            writer.write(gameState);
            writer.close();

            JsonReader reader = new JsonReader("./data/testPlayer.json");
            GameState gState = reader.read();
            assertEquals(1000, gState.getPlayer().getBalance());
            assertEquals(Rank.ACE, gState.getPlayer().getHand().getCards().get(0).getRank());
            assertEquals(Suit.HEARTS, gState.getPlayer().getHand().getCards().get(0).getSuit());
        } catch (IOException e) {
            fail("Was not excpecting exception");
        }
    }

    @Test
    void testWriterWithHand() {
        try {
            Hand hand = new Hand();
            Card card1 = new Card(Rank.ACE, Suit.HEARTS);
            Card card2 = new Card(Rank.TEN, Suit.SPADES);
            hand.addCard(card1);
            hand.addCard(card2);

            JsonWriter writer = new JsonWriter("./data/testPlayer.json");
            writer.open();
            writer.write(hand);
            writer.close();
        } catch (IOException e) {
            fail("Should not throw error");
        }
    }
}
