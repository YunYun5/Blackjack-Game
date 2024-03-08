package persistance;

import model.*;
import model.enums.Rank;
import model.enums.Suit;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

            String content = Files.readString(Path.of("./data/testPlayer.json"));
            assertTrue(content.contains("balance"));
            assertTrue(content.contains("1000"));
            assertTrue(content.contains("rank"));
            assertTrue(content.contains("ACE"));
            assertTrue(content.contains("suit"));
            assertTrue(content.contains("HEARTS"));

        } catch (IOException e) {
            fail("Was not excpecting exception");
        }
    }
}
