package ui;

import model.DealerHand;
import model.Deck;
import model.Player;

import java.util.Scanner;

/**
 * Represents the main game class.
 * Manages the flow of the game, including dealing cards, handling player and dealer turns,
 * and managing bets and outcomes. Allows the user to play rounds of Blackjack until they
 * decide to quit or run out of chips.
 */
public class BlackjackGame {

    private Deck deck;
    private final Player player;
    private final DealerHand dealerHand;
    private final Scanner scanner;

    // Effects: starts a new scanner, makes a new deck with the number of decks the player wants to play with, makes a
    // new player with 1000 balance, makes a new dealer and starts the game.
    public BlackjackGame() {
        this.scanner = new Scanner(System.in);
        int numberOfDecks = getNumberOfDecks();
        this.deck = new Deck(numberOfDecks);
        this.player = new Player(1000);
        this.dealerHand = new DealerHand();

        startGame();
    }

    // Modifies: this
    // Effects: Starts the game loop and keeps going until players balance is lower than 0
    public void startGame() {
        while (player.getBalance() > 0) {
            playRound();
        }

        System.out.println("Game over! You're out of chips.");
    }

    // Modifies: this
    // Effects: Plays one full round by calling the helpers handlePlayerBet, dealFirstHands, playerTurnLogic,
    // handleDealerAndOutcome then clears the hands of player and dealer for next round
    private void playRound() {
        handlePlayerBet();

        if (deck.getDeckSize() < 0) {
            this.deck = new Deck(getNumberOfDecksNoCardsLeft());
        }

        // Dealing cards
        dealFirstHands();

        // Play players turn
        playerTurnLogic();

        // Dealer's turn and outcome of hand
        handleDealerAndOutcome();

        // Resetting hands for next round.
        player.getHand().clear();
        dealerHand.clear();
    }

    // Modifies: this
    // Effects: Checks for blackjack if no blackjack it checks if player is buster. If the player is not busted
    // it plays the dealers turn according to the rules. Once the dealer stops or busts checks for outcome (win, push,
    // lose).
    private void handleDealerAndOutcome() {
        if (player.getHand().isBlackjack()) {
            System.out.println("You win!");
            player.winBet(player.getHand().isBlackjack());
        } else {
            if (!player.getHand().isBusted()) {
                while (dealerHand.shouldDealerHit()) {
                    dealerHand.addCard(deck.getNextCard());
                }
                System.out.println("Dealer's hand: " + dealerHand.handToString(false));

                if (dealerHand.isBusted() || player.getHand().getTotal() > dealerHand.getTotal()) {
                    System.out.println("You win!");
                    player.winBet(player.getHand().isBlackjack());
                } else if (player.getHand().getTotal() < dealerHand.getTotal()) {
                    System.out.println("Dealer wins.");
                    player.loseBet();
                } else {
                    System.out.println("Push.");
                    player.push();
                }
            }
        }
    }

    // Modifies: this
    // Effects: Uses players input to play the round. Hit, Stand or Quit
    private void playerTurnLogic() {
        boolean playerTurn = true;
        while (playerTurn) {
            giveOptions();
            String decision = scanner.next();
            if ("H".equalsIgnoreCase(decision)) {
                if (deck.getDeckSize() == 0) {
                    this.deck = new Deck(getNumberOfDecksNoCardsLeft());
                }
                player.getHand().addCard(deck.getNextCard());
                if (player.getHand().isBusted()) {
                    System.out.println("Bust! You lose.");
                    playerTurn = false;
                }
            } else if ("S".equalsIgnoreCase(decision)) {
                playerTurn = false;
            } else if ("Q".equalsIgnoreCase(decision)) {
                System.out.println("Quitting Game");
                playerTurn = false;
                endGame();
            } else {
                System.out.println("Invalid input. Please enter H, S, or Q.");
            }
        }
    }

    private void giveOptions() {
        System.out.println("Your hand: " + player.getHand().handToString());
        System.out.println("Dealer's hand: " + dealerHand.handToString(true));
        System.out.println("Hit, Stand or Quit? (H/S/Q): ");
    }

    // Effects: Ends the game
    private void endGame() {
        System.out.println("Final chip count: " + player.getBalance());
        System.out.println("Thank you for playing!");

        System.exit(0);
    }

    // Modifies: this
    // Effects: Deals the first two cards to both the player and the dealer.
    private void dealFirstHands() {
        player.getHand().addCard(deck.getNextCard());
        dealerHand.addCard(deck.getNextCard());
        player.getHand().addCard(deck.getNextCard());
        dealerHand.addCard(deck.getNextCard());
    }

    // Modifies: this
    // Effects: Handles the betting for the player
    private void handlePlayerBet() {
        System.out.println("You have " + player.getBalance() + " chips.");
        System.out.print("Place your bet: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.next();
            System.out.print("Place your bet: ");
        }

        int bet = scanner.nextInt();

        while (bet > player.getBalance() || bet <= 0) {
            System.out.println("Invalid bet. You have " + player.getBalance() + " chips.");
            System.out.print("Place your bet: ");
            bet = scanner.nextInt();
        }
        player.placeBet(bet);
    }

    // Effects: Gets the number of decks the player wants to play with at the start
    private int getNumberOfDecks() {
        System.out.println("Welcome to Blackjack!");
        System.out.print("Enter the number of decks to use: ");

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Invalid input. Please enter a numeric value for the number of decks.");
            System.out.print("Enter the number of decks to use: ");
        }


        int numberOfDecks = scanner.nextInt();

        while (numberOfDecks <= 0) {
            System.out.println("Invalid number of decks. Please enter a positive number.");
            System.out.print("Enter the number of decks to use: ");
            numberOfDecks = scanner.nextInt();
        }
        return numberOfDecks;
    }

    // Effects: Gets the number of decks the player wants to play after the shoe has ended
    private int getNumberOfDecksNoCardsLeft() {
        System.out.println("End of the shoe!");
        System.out.print("Enter the number of decks to use: ");

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Invalid input. Please enter a numeric value for the number of decks.");
            System.out.print("Enter the number of decks to use: ");
        }


        int numberOfDecks = scanner.nextInt();

        while (numberOfDecks <= 0) {
            System.out.println("Invalid number of decks. Please enter a positive number.");
            System.out.print("Enter the number of decks to use: ");
            numberOfDecks = scanner.nextInt();
        }
        return numberOfDecks;
    }
}
