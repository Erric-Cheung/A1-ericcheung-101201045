package org.example;

import java.util.ArrayList;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();
    }

    // Decks
    ArrayList<Card> eventDeck = new ArrayList<>();
    ArrayList<Card> adventureDeck = new ArrayList<>();

    // Players
    Player player1 = new Player("P1");
    Player player2 = new Player("P2");
    Player player3 = new Player("P3");
    Player player4 = new Player("P4");

    public static class Card {
        String name;
        String type;
        int value;
        String effect;

        public Card(String name, int value, String type, String effect) {
            this.name = name;
            this.value = value;
            this.type = type;
            this.effect = effect;
        }
    }

    public static class Player {
        String playerName;
        ArrayList<Card> adventureHand;
        int shields;

        public Player(String name){
            this.playerName = name;
            shields = 0;
            adventureHand = new ArrayList<>();
        }

        public void addAdventureCard(Card card){
            adventureHand.add(card);
        }

        public int getPlayerAdventureHandSize() {
            return adventureHand.size();
        }

        public void addShield(int i){

        }
    }

    public Card drawEventCard() {
        Card draw;
        draw = eventDeck.removeFirst();
        return draw;
    }

    public Card drawAdventureCard() {
        Card draw;
        draw = adventureDeck.removeFirst();
        return draw;
    }

    public void initializeAdventureDeck() {
        adventureDeck = new ArrayList<>();
        // Foe cards
        for (int i = 0; i < 8; i++) adventureDeck.add(new Card("F5", 5, "Foe", null));
        for (int i = 0; i < 7; i++) adventureDeck.add(new Card("F10", 10, "Foe", null));
        for (int i = 0; i < 8; i++) adventureDeck.add(new Card("F15", 15, "Foe", null));
        for (int i = 0; i < 7; i++) adventureDeck.add(new Card("F20", 20, "Foe", null));
        for (int i = 0; i < 7; i++) adventureDeck.add(new Card("F25", 25, "Foe", null));
        for (int i = 0; i < 4; i++) adventureDeck.add(new Card("F30", 30, "Foe", null));
        for (int i = 0; i < 4; i++) adventureDeck.add(new Card("F35", 35, "Foe", null));
        for (int i = 0; i < 2; i++) adventureDeck.add(new Card("F40", 40, "Foe", null));
        for (int i = 0; i < 2; i++) adventureDeck.add(new Card("F50", 50, "Foe", null));
        for (int i = 0; i < 1; i++) adventureDeck.add(new Card("F70", 70, "Foe", null));

        // Weapon cards
        for (int i = 0; i < 6; i++) adventureDeck.add(new Card("D5", 5, "Weapon", null));
        for (int i = 0; i < 12; i++) adventureDeck.add(new Card("H10", 10, "Weapon", null));
        for (int i = 0; i < 16; i++) adventureDeck.add(new Card("S10", 10, "Weapon", null));
        for (int i = 0; i < 8; i++) adventureDeck.add(new Card("B15", 15, "Weapon", null));
        for (int i = 0; i < 6; i++) adventureDeck.add(new Card("L20", 20, "Weapon", null));
        for (int i = 0; i < 2; i++) adventureDeck.add(new Card("E30", 30, "Weapon", null));

        Collections.shuffle(adventureDeck);
    }

    public int getAdventureDeckSize() {
        return adventureDeck.size();
    }

    public void initializeEventDeck() {
        eventDeck = new ArrayList<>();
        // Quest cards
        for (int i = 0; i < 3; i++) eventDeck.add(new Card("Q2", 2, "Quest", null));
        for (int i = 0; i < 4; i++) eventDeck.add(new Card("Q3", 3, "Quest", null));
        for (int i = 0; i < 3; i++) eventDeck.add(new Card("Q4", 4, "Quest", null));
        for (int i = 0; i < 2; i++) eventDeck.add(new Card("Q5", 5, "Quest", null));

        // Event cards
        eventDeck.add(new Card("Plague", 0, "Event", "Lose 2 shields immediately"));
        for (int i = 0; i < 2; i++) eventDeck.add(new Card("Queen's favor", 0, "Event", "Draw 2 adventure cards"));
        for (int i = 0; i < 2; i++)
            eventDeck.add(new Card("Prosperity", 0, "Event", "All players draw 2 adventure cards"));

        Collections.shuffle(eventDeck);
    }

    public int getEventDeckSize() {
        return eventDeck.size();
    }

    public void initializePlayers(){
        for(int i = 0; i < 12; i++){
            player1.addAdventureCard(drawAdventureCard());
            player2.addAdventureCard(drawAdventureCard());
            player3.addAdventureCard(drawAdventureCard());
            player4.addAdventureCard(drawAdventureCard());
        }
    }

    public boolean checkForWinningPlayers(){
        return false;
    }
}