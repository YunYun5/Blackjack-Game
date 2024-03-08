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

public class JsonReader {

    private final String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private GameState parseGameState(JSONObject jsonObject) {
        Player player = parsePlayer(jsonObject.getJSONObject("player"));
        DealerHand dealerHand = parseDealerHand(jsonObject.getJSONObject("dealerHand"));
        Deck deck = parseDeck(jsonObject.getJSONObject("deck"));
        return new GameState(player, dealerHand, deck);
    }

    private Player parsePlayer(JSONObject jsonObject) {
        int balance = jsonObject.getInt("balance");
        int currentBet = jsonObject.getInt("currentBet");
        PlayerHand hand = parsePlayerHand(jsonObject.getJSONObject("playerHand"));
        return new Player(balance, hand, currentBet); // Adjust this constructor based on your Player class
    }

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

    private Deck parseDeck(JSONObject jsonObject) {
        JSONArray cardsJson = jsonObject.getJSONArray("cards");
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardsJson.length(); i++) {
            JSONObject cardJson = cardsJson.getJSONObject(i);
            Card card = parseCard(cardJson);
            cards.add(card);
        }
        return new Deck(cards); // Adjust this constructor based on your Deck class
    }

    private Card parseCard(JSONObject jsonObject) {
        String rankStr = jsonObject.getString("rank");
        String suitStr = jsonObject.getString("suit");
        Rank rank = Rank.valueOf(rankStr); // Convert string back to Rank enum
        Suit suit = Suit.valueOf(suitStr); // Convert string back to Suit enum
        return new Card(rank, suit);
    }

}
