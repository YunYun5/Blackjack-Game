package model;

import persistance.Writable;
import org.json.JSONObject;

/**
 * Represents a player in the game.
 * Manages the player's balance, the bets placed, and the hand of cards.
 * Allows for betting, winning, pushing (tie), and losing bets,
 */
public class Player implements Writable {

    private int balance;
    private final PlayerHand hand;
    private int currentBet;

    // Requires: balance > 0
    // Modifies: this
    // Effects: Creates a new player with the balance specified with a empty hand and no current bet
    public Player(int balance) {
        this.balance = balance;
        this.hand = new PlayerHand();
        this.currentBet = 0;
    }

    public Player(int balance, PlayerHand hand, int currentBet) {
        this.balance = balance;
        this.hand = hand;
        this.currentBet = currentBet;
    }

    // Modifies: this
    // Effects: Sets the current bet to the bet and the balance to the bet subtracted
    public void placeBet(int bet) {
        this.currentBet = bet;
        this.balance -= bet;
    }

    // Modifies: this
    // Effects: Gives the winnings of the round. If it is blackjack they get their bet + 1.5 times their bet. If no
    // blackjack they just get + their bet
    public void winBet(boolean blackjack) {
        if (blackjack) {
            this.balance += (int) (currentBet + (currentBet * 1.5));
        } else {
            this.balance += currentBet * 2;
        }
        this.currentBet = 0;
    }

    // Modifies: this
    // Effects: Its a die the player gets their money back and the current bet gets set to 0
    public void push() {
        this.balance += currentBet;
        this.currentBet = 0;
    }

    // Modifies: this
    // Effects: Sets current bet to 0. Doesn't need to subtract because we already subtract it when we place the bet
    public void loseBet() {
        this.currentBet = 0;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public PlayerHand getHand() {
        return hand;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", balance);
        json.put("playerHand", hand.toJson());
        json.put("currentBet", currentBet);
        return json;
    }

}
