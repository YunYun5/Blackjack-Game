package model;

import org.json.JSONObject;
import persistance.Writable;

public class GameState implements Writable {

    private final Player player;
    private final DealerHand dealerHand;
    private final Deck deck;

    public GameState(Player player, DealerHand dealerHand, Deck deck) {
        this.player = player;
        this.dealerHand = dealerHand;
        this.deck = deck;
    }

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
