package org.example;

import java.io.PrintWriter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        game.displayCurrentPlayer(output);
        game.startPlayerTurn();
        game.promptPlayer(input, output);

        game.currentEventCard = new Main.Card("Q3", 3, "Quest", null);
        game.currentQuestSponsor = game.getCurrentPlayer();

        game.promptBuildQuest(input, output);
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
    Player currentQuestSponsor;
    Quest currentQuest;

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

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Card other = (Card) obj;
            if (this.name == null) {
                return other.name == null;
            } else return this.name.equals(other.name);
        }
    }

    public static class Player {
        String playerName;
        ArrayList<Card> adventureHand;
        ArrayList<Card> attackHand;
        int shields;

        public Player(String name) {
            this.playerName = name;
            shields = 0;
            adventureHand = new ArrayList<>();
            attackHand = new ArrayList<>();
        }

        public void addAdventureCard(Card card) {
            adventureHand.add(card);
            adventureHand.sort(new CardComparator());
        }

        public void removeAdventureCard(int i) {
            adventureHand.remove(i);
        }

        public Card getAdventureCard(int index) {
            return adventureHand.get(index);
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

        public ArrayList<Card> getAttack() {
            return attackHand;
        }

        public int getAttackValue() {
            int value = 0;
            for (Card card : attackHand) {
                value = value + card.value;
            }
            return value;
        }

        public void setAttack(ArrayList<Card> attack) {
            this.attackHand = attack;
        }


        public void clearAttack() {

        }
    }

    public static class Quest {
        int questValue;
        Player questSponsor;
        ArrayList<ArrayList<Card>> stages;
        ArrayList<Player> particpants;

        public Quest(Player player, Card questCard) {
            stages = new ArrayList<>();
            particpants = new ArrayList<>();
            questValue = questCard.value;
            questSponsor = player;
        }

        public void addStage(ArrayList<Card> stage) {
            stages.addLast(stage);
        }

        public ArrayList<Card> getStage(int index) {
            return stages.get(index);
        }

        public int getStageValue(int index) {
            int totalValue = 0;
            ArrayList<Card> stage = getStage(index);
            for (Card card : stage) {
                totalValue = totalValue + card.value;
            }

            return totalValue;
        }

        public int getQuestValue() {
            return questValue;
        }

        public Player getQuestSponsor() {
            return questSponsor;
        }

        public void addParticipant(Player player) {
            particpants.add(player);
        }

        public ArrayList<Player> getParticipants() {
            return particpants;
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
        output.println("Current Player: " + player.playerName);
        output.println("Current Hand: ");
        displayCurrentAdventureHand(output);
        output.println();
        output.flush();
    }

    public void displayEventCard(PrintWriter output) {
        String cardName = currentEventCard.name;
        if (Objects.equals(currentEventCard.type, "Quest")) {
            output.println("DRAWN CARD: " + cardName);
        }
        if (Objects.equals(currentEventCard.type, "Event")) {
            String effect = currentEventCard.effect;
            output.println("DRAWN CARD: " + cardName + ", " + effect);
        }

        output.flush();
    }

    public void displayCurrentAdventureHand(PrintWriter output) {
        Player player = getCurrentPlayer();
        for (int i = 0; i < player.getPlayerAdventureHandSize(); i++) {
            output.print(i + "." + player.adventureHand.get(i).name + "   ");
        }
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
            displayCurrentAdventureHand(output);
            output.flush();
            output.println();

            int index = Integer.parseInt(input.nextLine());
            player.removeAdventureCard(index);

            output.println("Updated Hand:");
            displayCurrentAdventureHand(output);
            output.println();
            output.flush();
        }
    }

    public void promptSponsorQuest(Scanner input, PrintWriter output) {
        for (int i = 0; i < playerList.size(); i++) {
            Player player = getCurrentPlayer();
            String playerName = player.getPlayerName();
            output.println(playerName + ", enter Y to sponsor the quest");
            output.flush();
            String inputStr = input.nextLine();
            if (inputStr.equals("Y")) {
                currentQuestSponsor = player;
                return;
            }
            nextPlayer();
        }
    }

    public void promptPlayer(Scanner input, PrintWriter output) {
        displayEventCard(output);

        // Check for over 12 cards
        promptTrimHand(input, output);

        if (Objects.equals(currentEventCard.type, "Quest")) {
            promptSponsorQuest(input, output);
        }

        promptFinishTurn(input, output);
    }

    public void promptBuildQuest(Scanner input, PrintWriter output) {
        currentQuest = new Quest(getCurrentPlayer(), currentEventCard);
        Player questSponsor = currentQuest.getQuestSponsor();
        int prevStageValue = 0;

        for (int i = 0; i < currentQuest.getQuestValue(); i++) {
            ArrayList<Card> stage = new ArrayList<>();
            int stageNumber = i + 1;
            int stageValue = 0;
            boolean containsFoe = false;

            while (true) {
                output.println("Please select a card to include in stage " + stageNumber + " or enter quit to finish stage " + stageNumber + ".");
                displayCurrentAdventureHand(output);
                output.println();
                output.flush();

                String inputStr = input.nextLine();

                if (Objects.equals(inputStr, "quit") && stage.isEmpty()) {
                    output.println("A stage cannot be empty");
                    continue;
                }
                if (Objects.equals(inputStr, "quit") && stageValue <= prevStageValue) {
                    output.println("Insufficient value for this stage");
                    continue;
                }
                if (Objects.equals(inputStr, "quit")) {
                    prevStageValue = stageValue;
                    break;
                }

                int cardIndex = Integer.parseInt(inputStr);
                Card selectedCard = questSponsor.getAdventureCard(cardIndex);

                if (Objects.equals(selectedCard.type, "Foe")) {
                    if (containsFoe) {
                        output.println("Stage cannot contain more than one foe");
                        continue;
                    }
                    containsFoe = true;
                }

                boolean isDuplicate = false;
                if (Objects.equals(selectedCard.type, "Weapon")) {
                    for (Card stageCard : stage) {
                        if (Objects.equals(stageCard.name, selectedCard.name)) {
                            isDuplicate = true;
                            break;
                        }
                    }
                }
                if (isDuplicate) {
                    output.println("Stage cannot contain repeated weapons");
                    continue;
                }

                questSponsor.removeAdventureCard(cardIndex);
                stage.add(selectedCard);
                stageValue = stageValue + selectedCard.value;

                output.print("Current cards in stage: ");
                for (Card card : stage) {
                    output.print(card.name + "   ");
                }
                output.println();
                output.flush();
            }

            output.print("Cards used in the stage " + stageNumber + ": ");
            for (Card card : stage) {
                output.print(card.name + "   ");
            }
            output.println();
            output.flush();

            currentQuest.addStage(stage);
        }

        output.println("Quest stages successfully built.");
        output.flush();
    }

    public void promptBuiltAttack(Scanner input, PrintWriter output) {
        Player currentPlayer = getCurrentPlayer();
        ArrayList<Card> attack = new ArrayList<>();

        while (true) {
            output.println("Please select a card to include in the attack or quit to finish the attack.");
            displayCurrentAdventureHand(output);
            output.println();
            output.flush();

            String inputStr = input.nextLine();
            if (Objects.equals(inputStr, "quit")) {
                break;
            }

            int cardIndex = Integer.parseInt(inputStr);
            Card selectedCard = currentPlayer.getAdventureCard(cardIndex);

            if (Objects.equals(selectedCard.type, "Foe")) {
                output.println("Foe cards is not valid");
                continue;
            }

            if (attack.contains(selectedCard)) {
                output.println("Repeated weapon cards is not valid");
                continue;
            }

            currentPlayer.removeAdventureCard(cardIndex);
            attack.add(selectedCard);

            output.print("Current cards in attack: ");
            for (Card card : attack) {
                output.print(card.name + "   ");
            }
            output.println();
            output.flush();
        }

        output.print("Cards used in the attack: ");
        for (Card card : attack) {
            output.print(card.name + "   ");
        }
        output.println();
        output.flush();

        currentPlayer.setAttack(attack);
    }

    public void promptParticipate(Scanner input, PrintWriter output) {
        Player questSponsor = currentQuest.getQuestSponsor();
        for (Player player : playerList) {
            if (Objects.equals(player.getPlayerName(), questSponsor.getPlayerName())) {
                continue;
            }

            output.println(player.getPlayerName() + ", enter 'Y' to participate in quest, or enter anything else to decline");
            output.flush();

            String inputStr = input.nextLine();
            if (Objects.equals(inputStr, "Y")) {
                currentQuest.addParticipant(player);
            }

        }
    }

    public void displayParticipants(PrintWriter output) {
        output.print("Participants: ");
        for (Player player : currentQuest.getParticipants()) {
            output.print(player.getPlayerName() + ", ");
        }
        output.println();
        output.flush();
    }

    // Helper

    public void overwriteAdventureHand(Player player, int index, Card card) {
        player.adventureHand.remove(index);
        player.adventureHand.add(index, card);
    }
}