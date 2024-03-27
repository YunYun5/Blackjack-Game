package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void runBefore() {
        player = new Player(1000);
    }

    @Test
    void testPlayerInitialization() {
        assertEquals(1000, player.getBalance());
        assertNotNull(player.getHand());
        assertEquals(0, player.getHand().getTotal());
    }

    @Test
    void testPlaceBet() {
        player.placeBet(100);
        assertEquals(900, player.getBalance());
        assertEquals(100, player.getCurrentBet());
    }

    @Test
    void testWinBetWithoutBlackjack() {
        player.placeBet(100);
        player.winBet(false);
        assertEquals(1100, player.getBalance());
        assertEquals(0, player.getCurrentBet());
    }

    @Test
    void testSetCurrentBet() {
        assertEquals(0, player.getCurrentBet());
        player.setCurrentBet(100);
        assertEquals(100, player.getCurrentBet());
    }

    @Test
    void testWinBetWithBlackjack() {
        player.placeBet(100);
        player.winBet(true);
        assertEquals(1150, player.getBalance());
        assertEquals(0, player.getCurrentBet());
    }

    @Test
    void testPush() {
        player.placeBet(100);
        player.push();
        assertEquals(1000, player.getBalance());
        assertEquals(0, player.getCurrentBet());
    }

    @Test
    void testLoseBet() {
        player.placeBet(100);
        player.loseBet();
        assertEquals(900, player.getBalance());
        assertEquals(0, player.getCurrentBet());
    }
}

