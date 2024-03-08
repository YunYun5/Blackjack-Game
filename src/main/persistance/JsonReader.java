package persistance;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import model.enums.Rank;
import model.enums.Suit;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Class used to read JSON files. Mainly used for loading the game back to
 * the state it was at. The structure of the class was inspired from the demo application
 * given for this phase of the project.
 */
public class JsonReader {

    private final String source;

    // Requires: source != null
    // Modifies: this
    // Effects: Creates a JSONReader with the given source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Effects: Reads the GameState from the source file and returns it
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // Effects: Reads the source file and returns it as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // Effects: Returns a GameState after parsing the JSON file
    private GameState parseGameState(JSONObject jsonObject) {
        Player player = parsePlayer(jsonObject.getJSONObject("player"));
        DealerHand dealerHand = parseDealerHand(jsonObject.getJSONObject("dealerHand"));
        Deck deck = parseDeck(jsonObject.getJSONObject("deck"));
        return new GameState(player, dealerHand, deck);
    }

    // Effects: Returns a Player after parsing the JSON file
    private Player parsePlayer(JSONObject jsonObject) {
        int balance = jsonObject.getInt("balance");
        int currentBet = jsonObject.getInt("currentBet");
        PlayerHand hand = parsePlayerHand(jsonObject.getJSONObject("playerHand"));
        return new Player(balance, hand, currentBet);
    }

    // Effects: Returns a PlayerHand after parsing the JSON file
    private PlayerHand parsePlayerHand(JSONObject jsonObject) {
        JSONArray cardsJson = jsonObject.getJSONArray("cards");
        PlayerHand hand = new PlayerHand();
        for (int i = 0; i < cardsJson.length(); i++) {
            JSONObject cardJson = cardsJson.getJSONObject(i);
            Card card = parseCard(cardJson);
            hand.addCard(card);
        }
        return hand;
    }

    // Effects: Returns a DealerHand after parsing the JSON file
    private DealerHand parseDealerHand(JSONObject jsonObject) {
        JSONArray cardsJson = jsonObject.getJSONArray("cards");
        DealerHand hand = new DealerHand();
        for (int i = 0; i < cardsJson.length(); i++) {
            JSONObject cardJson = cardsJson.getJSONObject(i);
            Card card = parseCard(cardJson);
            hand.addCard(card);
        }
        return hand;
    }

    // Effects: Returns a Deck after parsing the JSON file
    private Deck parseDeck(JSONObject jsonObject) {
        JSONArray cardsJson = jsonObject.getJSONArray("cards");
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardsJson.length(); i++) {
            JSONObject cardJson = cardsJson.getJSONObject(i);
            Card card = parseCard(cardJson);
            cards.add(card);
        }
        return new Deck(cards);
    }

    // Effects: Returns a Card after parsing the JSON file
    private Card parseCard(JSONObject jsonObject) {
        String rankStr = jsonObject.getString("rank");
        String suitStr = jsonObject.getString("suit");
        Rank rank = Rank.valueOf(rankStr);
        Suit suit = Suit.valueOf(suitStr);
        return new Card(rank, suit);
    }

}
