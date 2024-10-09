package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

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
        assertTrue(output.toString().contains("Current Player: P2"));
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
        player.addAdventureCard(card1);
        player.addAdventureCard(card2);
        player.addAdventureCard(card3);
        player.addAdventureCard(card4);
        player.addAdventureCard(card5);
        player.addAdventureCard(card6);
        player.addAdventureCard(card7);

        // test 2 - displays hand in correct order
        game.displayCurrentPlayer(new PrintWriter(output));
        assertTrue(
                output.toString().contains("0.F5") &&
                        output.toString().contains("1.F10") &&
                        output.toString().contains("3.D5") &&
                        output.toString().contains("4.S10") &&
                        output.toString().contains("5.H10") &&
                        output.toString().contains("6.L20"));
    }

    @Test
    @DisplayName("Event card 'Plague' is drawn and effect is correct working")
    void RESP_07_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        // test 1 - player loses two shields after drawing event card
        Main.Player player = game.getCurrentPlayer();
        player.addShield(3);

        game.eventDeck.addFirst(new Main.Card("Plague", 0, "Event", "Lose 2 shields immediately"));
        game.startPlayerTurn();

        assertEquals(1, player.getShields());
    }

    @Test
    @DisplayName("Event card 'Queen's favor' is drawn and effect is correct working")
    void RESP_07_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();


        // test 2 - player gains two adventure cards after drawing event card
        Main.Player player = game.getCurrentPlayer();
        player.setAdventureHand(new ArrayList<>());

        game.eventDeck.addFirst(new Main.Card("Queen's favor", 0, "Event", "Draw 2 adventure cards"));
        game.startPlayerTurn();

        assertEquals(2, player.getPlayerAdventureHandSize());
    }

    @Test
    @DisplayName("Event card 'Prosperity' is drawn and effect is correct working")
    void RESP_07_test_03() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        // test 2 - all players gains two adventure cards after drawing event card
        for (Main.Player player : game.playerList) {
            player.setAdventureHand(new ArrayList<>());
        }

        game.eventDeck.addFirst(new Main.Card("Prosperity", 0, "Event", "All players draw 2 adventure cards"));
        game.startPlayerTurn();

        assertTrue(game.player1.getPlayerAdventureHandSize() == 2 &&
                game.player2.getPlayerAdventureHandSize() == 2 &&
                game.player3.getPlayerAdventureHandSize() == 2 &&
                game.player4.getPlayerAdventureHandSize() == 2);
    }

    @Test
    @DisplayName("Drawn event card is displayed properly")
    void RESP_08_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        // test 1 - should show event card name and effect

        StringWriter output = new StringWriter();

        game.eventDeck.addFirst(new Main.Card("Plague", 0, "Event", "Lose 2 shields immediately"));
        game.startPlayerTurn();
        game.displayEventCard(new PrintWriter(output));

        assertTrue(output.toString().contains("Plague") &&
                output.toString().contains("Lose 2 shields immediately"));
    }

    @Test
    @DisplayName("Display is cleared after player presses return key")
    void RESP_09_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        StringWriter output = new StringWriter();
        String input = "\n";

        game.displayCurrentPlayer(new PrintWriter(output));
        game.promptFinishTurn(new Scanner(input), new PrintWriter(output));

        // System.out.println(output);

        assertTrue(output.toString().contains("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n"));
    }

    @Test
    @DisplayName("Test player has 12 cards after trimming")
    void RESP_10_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.Player player = game.getCurrentPlayer();

        // 14 cards in current player's hand
        player.addAdventureCard(new Main.Card("F5", 5, "Foe", null));
        player.addAdventureCard(new Main.Card("F5", 5, "Foe", null));

        // test - Should trim card the first and last position of hand

        StringWriter output = new StringWriter();
        String input = "0\n12";

        game.promptTrimHand(new Scanner(input), new PrintWriter(output));

        assertEquals(12, player.getPlayerAdventureHandSize());
    }

    @Test
    @DisplayName("Test the correct card is removed from the deck")
    void RESP_10_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.Player player = game.getCurrentPlayer();

        // 14 cards in current player's hand
        player.addAdventureCard(new Main.Card("F5", 5, "Foe", null));
        player.addAdventureCard(new Main.Card("F5", 5, "Foe", null));

        // test - first and last card with names TEST_CARD should be trimmed from deck
        game.overwriteAdventureHand(player, 0, new Main.Card("TEST_CARD", 5, "Foe", null));
        game.overwriteAdventureHand(player, 13, new Main.Card("TEST_CARD", 5, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "13\n0";

        game.promptTrimHand(new Scanner(input), new PrintWriter(output));

        assertTrue(!Objects.equals(player.adventureHand.getLast().name, "TEST_CARD") && !Objects.equals(player.adventureHand.getFirst().name, "TEST_CARD"));
    }

    @Test
    @DisplayName("Test player is prompted to trim cards after exceeding 12")
    void RESP_10_test_03() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Main.Player player = game.getCurrentPlayer();

        // 14 cards in current player's hand
        player.addAdventureCard(new Main.Card("F5", 5, "Foe", null));
        player.addAdventureCard(new Main.Card("F5", 5, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "13\n0";

        game.promptTrimHand(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Please select a card to discard from your hand:"));

    }

    @Test
    @DisplayName("Only current player is prompted to sponsor quest")
    void RESP_11_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        game.nextPlayer();

        Main.Player player = game.getCurrentPlayer();
        String currentPlayerName = player.playerName;

        game.eventDeck.addFirst(new Main.Card("Q4", 4, "Quest", null));
        game.startPlayerTurn();

        StringWriter output = new StringWriter();
        String input = "Y";

        game.promptSponsorQuest(new Scanner(input), new PrintWriter(output));
        assertTrue(output.toString().contains(currentPlayerName + ", enter Y to sponsor the quest"));
    }

    @Test
    @DisplayName("All players are prompted to sponsor quest")
    void RESP_11_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        game.nextPlayer();
        game.eventDeck.addFirst(new Main.Card("Q4", 4, "Quest", null));
        game.startPlayerTurn();

        // test - players decline sponsor and next player is asked
        StringWriter output = new StringWriter();
        String input = "N\nN\nN\nN";

        game.promptSponsorQuest(new Scanner(input), new PrintWriter(output));
        assertTrue(output.toString().contains("P1, enter Y to sponsor the quest") &&
                output.toString().contains("P2, enter Y to sponsor the quest") &&
                output.toString().contains("P3, enter Y to sponsor the quest") &&
                output.toString().contains("P4, enter Y to sponsor the quest"));
    }

    @Test
    @DisplayName("Test sponsor is the correct player")
    void RESP_11_test_03() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        // test - After

        game.eventDeck.addFirst(new Main.Card("Q4", 4, "Quest", null));
        game.startPlayerTurn();

        StringWriter output = new StringWriter();
        String input = "N\nN\nY";

        game.promptSponsorQuest(new Scanner(input), new PrintWriter(output));

        String name = "";
        if (game.currentQuestSponsor != null) {
            name = game.currentQuestSponsor.playerName;
        }

        assertEquals("P3", name);
    }

    @Test
    @DisplayName("Test hand is updated and displayed for the building of each stage")
    void RESP_12_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("0.F5   1.F5   2.D5   3.F20") &&
                output.toString().contains("0.F5   1.D5   2.F20") &&
                output.toString().contains("0.F20"));
    }

    @Test
    @DisplayName("Test player is prompted to select cards in each stage")
    void RESP_12_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Please select a card to include in stage 1") &&
                output.toString().contains("Please select a card to include in stage 2") &&
                output.toString().contains("Please select a card to include in stage 3"));
    }

    @Test
    @DisplayName("Displays 'Stage cannot be empty' when stage has no card associated with it")
    void RESP_13_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "quit\n0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));
        assertTrue(output.toString().contains("A stage cannot be empty"));
    }

    @Test
    @DisplayName("Displays 'Insufficient value for this stage'")
    void RESP_13_test_02() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "quit\n0\nquit\n0\nquit\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Insufficient value for this stage"));
    }

    @Test
    @DisplayName("Displays the selected cards associated with the stage")
    void RESP_13_test_03() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Cards used in the stage 1: F5"));
    }

    @Test
    @DisplayName("Displays 'Multiple foe cards is not valid'")
    void RESP_13_test_04(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 4, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\n1\nquit\n1\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Stage cannot contain more than one foe"));
    }

    @Test
    @DisplayName("Displays 'Repeated weapon cards is not valid'")
    void RESP_13_test_05(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 4, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\n0\nquit\n1\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Stage cannot contain repeated weapons"));
    }

    @Test
    @DisplayName("Displays the currently selected cards for the stage")
    void RESP_13_test_06(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Current cards in stage: F5"));
        assertTrue(output.toString().contains("Current cards in stage: F5   D5"));
        assertTrue(output.toString().contains("Current cards in stage: F20"));
    }

    @Test
    @DisplayName("Quest has correct number of stages")
    void RESP_14_test_01(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        Main.Quest quest = game.currentQuest;
        assertEquals(3, quest.stages.size());
    }

    @Test
    @DisplayName("Quest stages has correct total value from cards selected")
    void RESP_14_test_02(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit\n0\n0\nquit\n0\nquit";
        game.promptBuildQuest(new Scanner(input), new PrintWriter(output));

        Main.Quest quest = game.currentQuest;

        assertEquals(5, quest.getStageValue(0));
        assertEquals(10, quest.getStageValue(1));
        assertEquals(20, quest.getStageValue(2));
    }

    @Test
    @DisplayName("Test hand is displayed and updated for the building of the attack")
    void RESP_15_test_01(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "1\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("0.F5   1.D5   2.D5   3.F20"));
        assertTrue(output.toString().contains("0.F5   1.D5   2.F20"));
    }

    @Test
    @DisplayName("Test player is prompted to select cards for attack or quit")
    void RESP_15_test_02(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("F20", 20, "Foe", null));

        StringWriter output = new StringWriter();
        String input = "quit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Please select a card to include in the attack or quit to finish the attack."));
    }

    @Test
    @DisplayName("Displays the selected cards the attack after entering quit")
    void RESP_16_test_01(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("H10", 10, "Weapon", null));

        StringWriter output = new StringWriter();
        String input = "2\n2\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Cards used in the attack: D5   H10"));
    }

    @Test
    @DisplayName("Displays 'Repeated weapon cards is not valid' when building attack")
    void RESP_16_test_02(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("H10", 10, "Weapon", null));

        StringWriter output = new StringWriter();
        String input = "1\n1\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Repeated weapon cards is not valid"));
    }

    @Test
    @DisplayName("Displays 'Foe cards is not valid' when building attack")
    void RESP_16_test_03(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("H10", 10, "Weapon", null));

        StringWriter output = new StringWriter();
        String input = "0\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Foe cards is not valid"));
    }

    @Test
    @DisplayName("Displays the currently selected cards for the attack")
    void RESP_16_test_04(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("H10", 10, "Weapon", null));

        StringWriter output = new StringWriter();
        String input = "2\n2\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Current cards in attack: D5   H10"));
    }

    @Test
    @DisplayName("Attack has correct number of cards")
    void RESP_17_test_01(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("H10", 10, "Weapon", null));

        StringWriter output = new StringWriter();
        String input = "2\n2\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        assertEquals(2, currentPlayer.getAttack().size());
    }

    @Test
    @DisplayName("Attack has correct value")
    void RESP_17_test_02(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Main.Player currentPlayer = game.getCurrentPlayer();

        game.overwriteAdventureHand(currentPlayer, 0, new Main.Card("F5", 5, "Foe", null));
        game.overwriteAdventureHand(currentPlayer, 1, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 2, new Main.Card("D5", 5, "Weapon", null));
        game.overwriteAdventureHand(currentPlayer, 3, new Main.Card("H10", 10, "Weapon", null));

        StringWriter output = new StringWriter();
        String input = "2\n2\nquit";
        game.promptBuiltAttack(new Scanner(input), new PrintWriter(output));

        int value = currentPlayer.getAttackValue();

        assertEquals(15, value);
    }

    @Test
    @DisplayName("All players other than sponsor prompted to participate in quest")
    void RESP_18_test_01(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        game.nextPlayer();
        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = currentPlayer;

        StringWriter output = new StringWriter();
        String input = "Y\nY\nY";

        game.promptParticipate(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("P1, enter 'Y' to participate in quest, or enter anything else to decline"));
        assertTrue(output.toString().contains("P3, enter 'Y' to participate in quest, or enter anything else to decline"));
        assertTrue(output.toString().contains("P4, enter 'Y' to participate in quest, or enter anything else to decline"));
    }

    @Test
    @DisplayName("Displays eligible participants")
    void RESP_18_test_02(){
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        game.nextPlayer();
        Main.Player currentPlayer = game.getCurrentPlayer();
        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = currentPlayer;

        StringWriter output = new StringWriter();
        String input = "Y\nN\nY";

        game.promptParticipate(new Scanner(input), new PrintWriter(output));
        game.displayParticipants(new PrintWriter(output));
        assertTrue(output.toString().contains("Participants: P1, P4"));
    }
}


