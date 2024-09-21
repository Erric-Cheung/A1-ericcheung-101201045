package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Test if adventure deck has correct number of cards and their different types")
    void RESP_01_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();

        int deckSize = game.getAdventureDeckSize();

        // test 1 - adventure deck should have correct number of adventure cards

        String[] cardNames = {"F5", "F10", "F15", "F20", "F25", "F30", "F35", "F40", "F50", "F70", "D5", "H10", "S10", "B15", "L20", "E30"};
        Map<String, Integer> cardCountMap = new HashMap<>();

        for (String name : cardNames) {
            cardCountMap.put(name, 0);
        }

        for (int i = 0; i < deckSize; i++) {
            Main.Card newCard = game.drawAdventureCard();

            if (cardCountMap.containsKey(newCard.name)) {
                cardCountMap.put(newCard.name, cardCountMap.get(newCard.name) + 1);
            }
        }

        assertTrue(
                (cardCountMap.get("F5") == 8) &&
                        (cardCountMap.get("F10") == 7) &&
                        (cardCountMap.get("F15") == 8) &&
                        (cardCountMap.get("F20") == 7) &&
                        (cardCountMap.get("F25") == 7) &&
                        (cardCountMap.get("F30") == 4) &&
                        (cardCountMap.get("F35") == 4) &&
                        (cardCountMap.get("F40") == 2) &&
                        (cardCountMap.get("F50") == 2) &&
                        (cardCountMap.get("F70") == 1) &&
                        (cardCountMap.get("D5") == 6) &&
                        (cardCountMap.get("H10") == 12) &&
                        (cardCountMap.get("S10") == 16) &&
                        (cardCountMap.get("B15") == 8) &&
                        (cardCountMap.get("L20") == 6) &&
                        (cardCountMap.get("E30") == 2)
        );
    }

    @Test
    @DisplayName("Test if event deck has correct number cards and their different types")
    void RESP_01_test_02() {
        Main game = new Main();
        game.initializeEventDeck();
        int deckSize = game.getEventDeckSize();

        // test 2 - event deck should have correct number of event cards

        String[] cardNames = {"Q2", "Q3", "Q4", "Q5", "Plague", "Queen's favor", "Prosperity"};
        Map<String, Integer> cardCountMap = new HashMap<>();

        for (String name : cardNames) {
            cardCountMap.put(name, 0);
        }

        for (int i = 0; i < deckSize; i++) {
            Main.Card newCard = game.drawEventCard();

            if (cardCountMap.containsKey(newCard.name)) {
                cardCountMap.put(newCard.name, cardCountMap.get(newCard.name) + 1);
            }
        }

        assertTrue(
                (cardCountMap.get("Q2") == 3) &&
                        (cardCountMap.get("Q3") == 4) &&
                        (cardCountMap.get("Q4") == 3) &&
                        (cardCountMap.get("Q5") == 2) &&
                        (cardCountMap.get("Plague") == 1) &&
                        (cardCountMap.get("Queen's favor") == 2) &&
                        (cardCountMap.get("Prosperity") == 2)

        );
    }

    @Test
    @DisplayName("Check if each player has 12 adventure cards")
    void RESP_02_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        // test 1 - each player should have 12 cards

        assertTrue(game.player1.getPlayerAdventureHandSize() == 12 &&
                game.player2.getPlayerAdventureHandSize() == 12 &&
                game.player3.getPlayerAdventureHandSize() == 12 &&
                game.player4.getPlayerAdventureHandSize() == 12);
    }

    @Test
    @DisplayName("Check if each deck is updated after distribution")
    void RESP_02_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        // test 2 - deck should have 52 cards after distributing to 4 players
        assertEquals(52, game.getAdventureDeckSize());
    }

    @Test
    @DisplayName("Test if players have 7 shields.")
    void RESP_03_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();
        game.player1.addShield(7);
        game.player2.addShield(7);

        // test 1 - should determine there is a winner
        boolean hasWinner = game.checkForWinningPlayers();
        assertTrue(hasWinner);
    }

    @Test
    @DisplayName("Game displays id of each winner")
    void RESP_04_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        game.player1.addShield(7);
        game.player3.addShield(7);

        StringWriter output = new StringWriter();

        // test 1 - should display ids of each winner

        game.winnersPrompt(new PrintWriter(output));
        assertTrue(output.toString().contains("P1") && output.toString().contains("P3"));
    }

    @Test
    @DisplayName("Correct turn order")
    void RESP_05_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        // test 1 - after 5 turns, current player should be player 2
        for (int i = 0; i < 5; i++) {
            game.nextPlayer();
        }

        assertEquals("P2", game.getCurrentPlayer().getPlayerName());
    }

    @Test
    @DisplayName("Game displays current player name")
    void RESP_06_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        StringWriter output = new StringWriter();

        // test 1 - displays current player
        game.nextPlayer();
        game.displayCurrentPlayer(new PrintWriter(output));
        System.out.println(output);
        assertTrue(output.toString().contains("CURRENT PLAYER: P2"));
    }

    @Test
    @DisplayName("Game displays current player's hand and is sorted")
    void RESP_06_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        StringWriter output = new StringWriter();

        // Rig deck
        Main.Player player = game.getCurrentPlayer();
        Main.Card card1 = new Main.Card("F10", 10, "Foe", null);
        Main.Card card7 = new Main.Card("D5", 5, "Weapon", null);
        Main.Card card2 = new Main.Card("F5", 5, "Foe", null);
        Main.Card card3 = new Main.Card("F70", 70, "Foe", null);
        Main.Card card4 = new Main.Card("L20", 20, "Weapon", null);
        Main.Card card5 = new Main.Card("H10", 10, "Weapon", null);
        Main.Card card6 = new Main.Card("S10", 10, "Weapon", null);

        player.adventureHand = new ArrayList<>();
        player.adventureHand.add(card1);
        player.adventureHand.add(card2);
        player.adventureHand.add(card3);
        player.adventureHand.add(card4);
        player.adventureHand.add(card5);
        player.adventureHand.add(card6);
        player.adventureHand.add(card7);

        // test 2 - displays hand in correct order
        game.displayCurrentPlayer(new PrintWriter(output));
        System.out.println(output);
        assertTrue(output.toString().contains("F5 F10 F70 D5 S10 H10 L20"));
    }


}

