package ui;

import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BlackjackGUI extends JFrame {

    private static final String JSON_FILE_LOCATION = "./data/blackjack.json";
    private static final String[] CHIP_VALUES = {"1", "5", "10", "25", "100", "500", "1000"};

    private JPanel playerHandPanel;
    private JPanel dealerHandPanel;
    private JPanel bettingPanel;
    private JPanel centerActionPanel;
    private JPanel rightActionPanel;
    private JPanel leftActionPanel;

    private JButton hitButton;
    private JButton standButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton newGameButton;

    private JLabel balanceLabel;
    private JLabel betLabel;
    private JLabel playerHandLabel;
    private JLabel dealerHandLabel;

    private Deck deck;
    private Player player;
    private DealerHand dealerHand;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private boolean playerTurn;

    // Effects: Makes a window with size 1200 x 800, names it Blackjack Game then runs the methods to initialize
    // the game and UI then starts the game
    public BlackjackGUI() {
        super("Blackjack Game");
        setSize(1200, 800);

        initializeGame();
        initializeUI();
        setVisible(true);

        startGame();
    }

    // Modifies: this
    // Effects: initializes the game by initializing the writer, reader, the deck size, dealer and a
    // player with 1000 balance
    private void initializeGame() {
        this.jsonWriter = new JsonWriter(JSON_FILE_LOCATION);
        this.jsonReader = new JsonReader(JSON_FILE_LOCATION);
        this.deck = new Deck(4);
        this.player = new Player(1000);
        this.dealerHand = new DealerHand();
    }

    // Effects: Initializes the UI by creating components, panels and listeners, then adds the components to
    // the corresponding panels.
    private void initializeUI() {
        initializeComponents();
        initializePanels();
        addComponentsToPanels();
        initializeListeners();
    }

    // Modifies: this
    // Effects: Initializes the listeners by setting each button to their corresponding method
    private void initializeListeners() {
        hitButton.addActionListener(e -> playerHit());
        standButton.addActionListener(e -> playerStand());
        loadButton.addActionListener(e -> loadGame());
        saveButton.addActionListener(e -> saveGame());
        newGameButton.addActionListener(e -> startNewGame());
    }

    // Modifies: this
    // Effects: Add all the created components to their respective panels. Also makes a button per chip value
    private void addComponentsToPanels() {
        for (String chipValue : CHIP_VALUES) {
            JButton chipButton = createChipButton(chipValue);
            chipButton.addActionListener(this::betButtonClicked);
            bettingPanel.add(chipButton);
        }

        centerActionPanel.add(hitButton);
        centerActionPanel.add(standButton);

        rightActionPanel.add(balanceLabel);
        rightActionPanel.add(betLabel);

        leftActionPanel.add(loadButton);
        leftActionPanel.add(saveButton);
        leftActionPanel.add(newGameButton);

        dealerHandPanel.add(dealerHandLabel);
        playerHandPanel.add(playerHandLabel);
    }

    // Modifies: this
    // Effects: Initializes all the components needed by creating them.
    private void initializeComponents() {
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        newGameButton = new JButton("New Game");

        balanceLabel = new JLabel("Balance: " + player.getBalance());
        betLabel = new JLabel("Current Bet: " + player.getCurrentBet());

        playerHandLabel = new JLabel();
        dealerHandLabel = new JLabel();
    }

    // Modifies: this
    // Effects: Initializes all the panels with correct layouts and names.
    private void initializePanels() {
        // Main 3 Panels
        playerHandPanel = new JPanel(new FlowLayout());
        dealerHandPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());

        dealerHandPanel.setBorder(BorderFactory.createTitledBorder("Dealer's Hand"));
        playerHandPanel.setBorder(BorderFactory.createTitledBorder("Player's Hand"));

        // Components of action panel
        centerActionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        leftActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        // Adding the components of the action panel into the action panel
        actionPanel.add(leftActionPanel, BorderLayout.WEST);
        actionPanel.add(centerActionPanel, BorderLayout.CENTER);
        actionPanel.add(rightActionPanel, BorderLayout.EAST);

        bettingPanel = new JPanel();

        // Combining the action and betting panels into 1 panel
        bottomPanel.add(actionPanel, BorderLayout.NORTH);
        bottomPanel.add(bettingPanel, BorderLayout.SOUTH);

        // Add the 3 main panels
        add(dealerHandPanel, BorderLayout.NORTH);
        add(playerHandPanel, BorderLayout. CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Modifies: this
    // Effects: Starts the game by creating a deck and making sure that the player and dealer have
    // no cards. Also disables buttons until a bet is placed.
    private void startGame() {
        this.deck = new Deck(4);
        this.player.getHand().clear();
        this.dealerHand.clear();
        playerTurn = true;

        enableActionButtons(false);
        updateBetAndBalance();
    }

    // Modifies: this
    // Effects: Helper method to disable or enable the hit and stand button with the given boolean value.
    private void enableActionButtons(boolean enable) {
        hitButton.setEnabled(enable);
        standButton.setEnabled(enable);
    }

    // Modifies: this
    // Effects: Helper method for when a player busts. Shows a message then removes balance from player.
    // Then gives the player the option to play another round or stop
    private void handlePlayerBust() {
        JOptionPane.showMessageDialog(this, "Bust! You lose.");
        player.loseBet();
        enableActionButtons(false);
        giveGameOptions();
    }

    // Modifies: this
    // Effects: Method for when a player clicks the hit button. Gives the player another card checks if they busted
    // then updated the cards
    private void playerHit() {
        if (player.getCurrentBet() > 0) {
            if (deck.getDeckSize() == 0) {
                this.deck = new Deck(4);
            }
            player.getHand().addCard(deck.getNextCard());
            updateHandDisplays();
            if (player.getHand().isBusted()) {
                handlePlayerBust();
            }
        }
    }

    // Modifies: this
    // Effects: Method for when a player clicks the stand button. Adds cards to the dealers hand until the
    // dealers needs to stop hitting. Then updates the cards in hand
    private void playerStand() {
        if (player.getCurrentBet() > 0) {
            playerTurn = false;
            while (dealerHand.shouldDealerHit()) {
                dealerHand.addCard(deck.getNextCard());
                updateHandDisplays();
            }
            updateHandDisplays();
            enableActionButtons(false);
            determineOutcome();
        }
    }

    // Modifies: this
    // Effects: Determines the outcome of the round by checking if dealer is busted or if player has better hand.
    // Also checks if the dealer has better hand or if the hands are equal. Then handles each outcome accordingly by
    // calilng win, lose or push on the player. Then displays outcome on the screen.
    private void determineOutcome() {
        if (dealerHand.isBusted() || player.getHand().getTotal() > dealerHand.getTotal()) {
            player.winBet(false);
            JOptionPane.showMessageDialog(this, "You win!");
        } else if (player.getHand().getTotal() < dealerHand.getTotal()) {
            player.loseBet();
            JOptionPane.showMessageDialog(this, "Dealer wins.");
        } else {
            player.push();
            JOptionPane.showMessageDialog(this, "It's a push.");
        }
        updateBetAndBalance();
        giveGameOptions();
    }

    // Effects: Gives the player the option to play another round or closes game if player doesnt want to play
    private void giveGameOptions() {
        int response = JOptionPane.showConfirmDialog(this, "Round over. Start a new round?",
                "Round Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetRound();
        } else {
            endGame();
        }
    }

    // Effects: Ends the game byb showing player their final chip count then closing the game window.
    private void endGame() {
        JOptionPane.showMessageDialog(this, "Final chip count: " + player.getBalance());
        enableActionButtons(false);
        System.exit(0);
    }

    // Modifies: this
    // Effects: Resets the round by setting everything back to their starting/default values for a round. If the deck
    // is out of cards creates a new deck.
    private void resetRound() {
        player.getHand().clear();
        dealerHand.clear();

        updateBetAndBalance();
        updateHandDisplays();

        if (deck.getDeckSize() < 4) {
            this.deck = new Deck(4);
        }

        player.setCurrentBet(0);
        playerTurn = true;
    }

    // Modifies: this
    // Effects: Method for when a player clicks the load game button.
    // Loads the game from the previous time it was saved. throws IOException
    // if it cannot load the file
    private void loadGame() {
        jsonReader = new JsonReader(JSON_FILE_LOCATION);
        try {
            GameState gameState = jsonReader.read();
            this.player = gameState.getPlayer();
            this.dealerHand = gameState.getDealerHand();
            this.deck = gameState.getDeck();
            JOptionPane.showMessageDialog(this, "Game loaded successfully.");
            enableActionButtons(false);
            updateHandDisplays();
            updateBetAndBalance();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load game: " + e.getMessage());
        }
    }

    // Effects: Method for when a player clicks save game button.
    // Saves the game by writing it to a JSON file. Tells the user where it was saved to
    // throws FileNotFoundException if it cannot find file
    private void saveGame() {
        GameState gameState = new GameState(player, dealerHand, deck);

        try {
            jsonWriter.open();
            jsonWriter.write(gameState);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved the current state of the game to " + JSON_FILE_LOCATION);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_FILE_LOCATION);
        }
    }

    // Effects: Method for when a player clicks the new game button. Creates a new game with default values and
    // starts the game.
    private void startNewGame() {
        initializeGame();
        startGame();
    }

    // Modifies: this
    // Effects: Updates the hands of the dealer and the player according to which round it is. Also updates the
    // value of the hand as cards are being added.
    private void updateHandDisplays() {
        if (player.getCurrentBet() > 0) {
            playerHandPanel.removeAll();
            dealerHandPanel.removeAll();

            for (Card card : player.getHand().getCards()) {
                addCardToPanel(playerHandPanel, card.cardToImagePathString());
                playerHandLabel.setText(player.getHand().handValue());
                playerHandPanel.add(playerHandLabel);
            }

            for (Card card : dealerHand.getCards()) {
                if (dealerHand.getCards().get(0).equals(card) && playerTurn) {
                    addCardToPanel(dealerHandPanel, "backside");
                } else {
                    addCardToPanel(dealerHandPanel, card.cardToImagePathString());
                    dealerHandLabel.setText(dealerHand.handValue(false));
                    dealerHandPanel.add(dealerHandLabel);
                }
            }

            updateText();

            playerHandPanel.revalidate();
            playerHandPanel.repaint();
            dealerHandPanel.revalidate();
            dealerHandPanel.repaint();
        }
    }

    // Modifies: this
    // Effects: Updates the dealers hand value according to if it's the players turn.
    private void updateText() {
        if (playerTurn) {
            dealerHandLabel.setText(dealerHand.handValue(true));
        } else {
            dealerHandLabel.setText(dealerHand.handValue(false));
        }
        dealerHandPanel.add(dealerHandLabel);
    }

    // Modifies: this
    // Effects: Adds the given card name to the given panel by going to through the data/Cards folder and finding
    // the correct card png.
    private void addCardToPanel(JPanel panel, String cardFileName) {
        try {
            ImageIcon icon = new ImageIcon("./data/Cards/" + cardFileName + ".png");
            ImageIcon scaledImage = new ImageIcon(icon.getImage().getScaledInstance(120, 168, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(scaledImage);
            panel.add(label);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Modifies: this
    // Effects: Creates a button for the given chip value and assigns the correct image from the data/Chips directory
    private JButton createChipButton(String chipValue) {
        ImageIcon icon = new ImageIcon("./data/Chips/" + chipValue + "chip.png");
        ImageIcon scaledImage = new ImageIcon(icon.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        JButton button = new JButton(scaledImage);
        button.setActionCommand(chipValue);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }

    // Modifies: this
    // Effects: Method for when a chip button in clicked. Gets the value of the chip that was clicked and deals
    // the initial cards to the players then updates the hands on the screen. Also check if the dealt cards resulted
    // in blackjack for the player and handles in accordingly.
    private void betButtonClicked(ActionEvent e) {
        String chipValue = e.getActionCommand();
        int betAmount = Integer.parseInt(chipValue);
        if (player.getBalance() >= betAmount && betAmount > 0) {
            player.placeBet(betAmount);
            if ((player.getHand().getNumOfCards() == 0 && dealerHand.getNumOfCards() == 0)) {
                player.getHand().addCard(deck.getNextCard());
                dealerHand.addCard(deck.getNextCard());
                player.getHand().addCard(deck.getNextCard());
                dealerHand.addCard(deck.getNextCard());
            }
            updateHandDisplays();
            if (player.getHand().isBlackjack() && player.getCurrentBet() > 0) {
                player.winBet(true);
                enableActionButtons(false);
                JOptionPane.showMessageDialog(this, "Blackjack! You Win 1.5x your bet");
                resetRound();
            } else {
                enableActionButtons(true);
            }
            updateBetAndBalance();
        }
    }

    // Modifies: this
    // Effects: Updates the players balance and their current bet.
    private void updateBetAndBalance() {
        balanceLabel.setText("Balance: " + player.getBalance());
        betLabel.setText("Current Bet: " + player.getCurrentBet());
    }
}
