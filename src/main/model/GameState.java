package model;

import org.json.JSONObject;
import persistance.Writable;

/**
 * Stores the state of a game. Includes the player object, dealerHand and deck. Stores
 * all the relevant cards, balances, bets. Used for saving and loading the game
 */
public class GameState implements Writable {

    private final Player player;
    private final DealerHand dealerHand;
    private final Deck deck;

    // Requires: player != null, dealerHand != null, deck != null
    // Modifies: this
    // Effects: Creates a new game state with the given player, dealerHand and deck
    public GameState(Player player, DealerHand dealerHand, Deck deck) {
        this.player = player;
        this.dealerHand = dealerHand;
        this.deck = deck;
    }

    // Effects: Returns object in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", player.toJson());
        json.put("dealerHand", dealerHand.toJson());
        json.put("deck", deck.toJson());
        return json;
    }

    public Player getPlayer() {
        return this.player;
    }

    public DealerHand getDealerHand() {
        return this.dealerHand;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
