package org.example;

import java.io.PrintWriter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayers();

        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        game.displayCurrentPlayer(output);
        game.promptFinishTurn(input, output);
    }

    // Decks
    ArrayList<Card> eventDeck = new ArrayList<>();
    ArrayList<Card> adventureDeck = new ArrayList<>();


    // Players
    Player player1 = new Player("P1");
    Player player2 = new Player("P2");
    Player player3 = new Player("P3");
    Player player4 = new Player("P4");
    ArrayList<Player> playerList = new ArrayList<>();

    // Game
    int currentPlayerIndex = 0;
    Card currentEventCard;

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

        public Player(String name) {
            this.playerName = name;
            shields = 0;
            adventureHand = new ArrayList<>();
        }

        public void addAdventureCard(Card card) {
            adventureHand.add(card);
            adventureHand.sort(new CardComparator());
        }

        public void removeAdventureCard(int i) {
            adventureHand.remove(i);
        }

        public void setAdventureHand(ArrayList<Card> hand) {
            adventureHand = hand;
        }

        public int getPlayerAdventureHandSize() {
            return adventureHand.size();
        }

        public void addShield(int i) {
            shields = shields + i;
        }

        public void removeShields(int i) {
            shields = shields - i;
        }

        public int getShields() {
            return shields;
        }

        public String getPlayerName() {
            return playerName;
        }
    }

    public static class CardComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            // Foes come before weapons
            if (c1.type.equals("Foe") && c2.type.equals("Weapon")) {
                return -1; // c1 (foe) is less than c2 (weapon)
            }
            if (c1.type.equals("Weapon") && c2.type.equals("Foe")) {
                return 1;  // c2 (foe) is less than c1 (weapon)
            }

            // If both are foes, sort by strength
            if (c1.type.equals("Foe") && c2.type.equals("Foe")) {
                if (c1.value != c2.value) {
                    return Integer.compare(c1.value, c2.value);
                }
            }

            // If both are weapons, sort swords before horses, then by strength
            if (c1.type.equals("Weapon") && c2.type.equals("Weapon")) {
                // Swords before horses
                if (c1.name.equals("S10") && c2.name.equals("H10")) {
                    return -1;
                }
                if (c1.name.equals("H10") && c2.name.equals("S10")) {
                    return 1;
                }

                // If both are the same type of weapon, sort by strength
                return Integer.compare(c1.value, c2.value);
            }

            // If types and other criteria are equal, return 0
            return 0;
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

    public void initializePlayers() {
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        for (int i = 0; i < 12; i++) {
            player1.addAdventureCard(drawAdventureCard());
            player2.addAdventureCard(drawAdventureCard());
            player3.addAdventureCard(drawAdventureCard());
            player4.addAdventureCard(drawAdventureCard());
        }
    }

    public boolean checkForWinningPlayers() {
        for (Player player : playerList) {
            if (player.shields >= 7) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getWinningPlayersId() {
        ArrayList<String> winningPlayers = new ArrayList<>();
        for (Player player : playerList) {
            if (player.getShields() >= 7) {
                winningPlayers.add(player.getPlayerName());
            }

        }
        return winningPlayers;
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public Player getCurrentPlayer() {
        return playerList.get(currentPlayerIndex);
    }

    public void startPlayerTurn() {
        Player currentPlayer = getCurrentPlayer();
        Card drawnEventCard = drawEventCard();
        currentEventCard = drawnEventCard;

        if (Objects.equals(drawnEventCard.name, "Plague")) {
            currentPlayer.removeShields(2);
        }
        if (Objects.equals(drawnEventCard.name, "Queen's favor")) {
            for (int i = 0; i < 2; i++) {
                Card drawnAdventureCard = drawAdventureCard();
                currentPlayer.addAdventureCard(drawnAdventureCard);
            }
        }
        if (Objects.equals(drawnEventCard.name, "Prosperity")) {
            for (Player player : playerList) {
                for (int i = 0; i < 2; i++) {
                    Card drawnAdventureCard = drawAdventureCard();
                    player.addAdventureCard(drawnAdventureCard);
                }
            }
        }
    }

    // Interface
    public void winnersPrompt(PrintWriter output) {
        ArrayList<String> winnersList = getWinningPlayersId();
        String winners = String.join(", ", winnersList);
        output.println("WINNERS: " + winners);
    }

    public void displayCurrentPlayer(PrintWriter output) {
        Player player = getCurrentPlayer();
        ArrayList<Card> hand = player.adventureHand;
        output.println("CURRENT PLAYER: " + player.playerName);
        output.print("HAND: ");
        for (Card card : hand) {
            output.print(card.name + " ");
        }
    }

    public void displayEventCard(PrintWriter output) {
        String effect = currentEventCard.effect;
        String cardName = currentEventCard.name;

        output.println("DRAWN CARD: " + cardName + ", " + effect);
    }

    public void displayAdventureHand() {
    }

    public void promptFinishTurn(Scanner input, PrintWriter output) {
        output.println("");
        output.println("Press <return> key to your finish turn and clear display.");
        output.flush();

        input.nextLine();
        output.print("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        output.flush();
    }

    public void promptTrimHand(Scanner input, PrintWriter output) {
        Player player = getCurrentPlayer();

        while (player.getPlayerAdventureHandSize() > 12) {
            output.println("Please select a card to discard from your hand:");
            for (int i = 0; i < player.getPlayerAdventureHandSize(); i++) {
                output.print(i + "." + player.adventureHand.get(i).name + "   ");
            }
            output.flush();
            output.println();

            int index = Integer.parseInt(input.nextLine());
            player.removeAdventureCard(index);

            output.println("Updated Hand:");
            for (int i = 0; i < player.getPlayerAdventureHandSize(); i++) {
                output.print(i + "." + player.adventureHand.get(i).name + "   ");
            }
            output.flush();
            output.println();
        }
    }

    public void promptSponsorQuest(Scanner input, PrintWriter output){

    }


    // Helper

    public void overwriteAdventureHand(Player player, int index, Card card){
        player.adventureHand.remove(index);
        player.adventureHand.add(index, card);
    }
}