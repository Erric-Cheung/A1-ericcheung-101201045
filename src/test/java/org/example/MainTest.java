package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        // test 2 -

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
}