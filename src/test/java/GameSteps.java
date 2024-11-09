import io.cucumber.java.en.*;
import org.example.Card;
import org.example.Main;
import org.example.Player;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSteps {
    private Main game;

    @Given("a new game of Quests starts rigged")
    public void a_new_game_of_quests_starts_rigged1() {
        game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        // Player Rigged Hands
        game.getPlayer("P1").setAdventureHand(new ArrayList<>());
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P2").setAdventureHand(new ArrayList<>());
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("E30", 30, "Weapon", null));

        game.getPlayer("P3").setAdventureHand(new ArrayList<>());
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P4").setAdventureHand(new ArrayList<>());
        game.getPlayer("P4").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("L20", 20, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("E30", 30, "Weapon", null));

        // Rigged event deck card draw
        game.overwriteEventDeckDraw(0, new Card("Q4", 4, "Quest", null));

        // Rigged adventure deck card draw
        game.overwriteAdventureDeckDraw(0, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(1, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(2, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(3, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(4, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(5, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(6, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(7, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(8, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(9, new Card("L20", 20, "Weapon", null));
    }

    @When("{string} draws a quest of {int} stages and {string} sponsors with input {string}")
    public void quest_drawn_and_sponsored(String playerName, int questValue, String sponsorName, String input){
        StringWriter output = new StringWriter();
        String processedInput = input.replace(", ", "\n");

        game.startPlayerTurn();
        game.promptPlayer(new Scanner(processedInput), new PrintWriter(output));
    }

    @Then("{string} builds stages of the quest with a total value of {int} with input {string}")
    public void player_builds_quest(String playerName, int value, String input){
        StringWriter output = new StringWriter();

        String processedInput = input.replace(", ", "\n");
        game.promptBuildQuest(new Scanner(processedInput), new PrintWriter(output));

        processedInput = "\n";
        game.promptFinishTurn(new Scanner(processedInput), new PrintWriter(output));

        // Assert total value of all cards in stages is correct
        int totalValue = 0;
        for (int i = 0; i < game.getCurrentQuest().getQuestValue(); i++) {
            totalValue = totalValue + game.getCurrentQuest().getStageValue(i);
        }

        assertEquals(value, totalValue);
    }

    @Then("Players who accept to participate are {string} with input {string}")
    public void players_accept_to_participate(String result, String input){
        StringWriter output = new StringWriter();
        String processedInput = input.replace(", ", "\n");

        game.promptParticipate(new Scanner(processedInput), new PrintWriter(output));

        // Assert all participants are correct
        game.displayParticipants(new PrintWriter(output));
        assertTrue(output.toString().contains(result));
    }

    @Then("Participant's attack who pass stage {int} are {string} with input {string}")
    public void participants_stage_attack(int stageNum, String result, String input){
        StringWriter output = new StringWriter();
        String processedInput = input.replace("\\n", "\n");

        game.beginStage(new Scanner(processedInput), new PrintWriter(output));
        game.getCurrentQuest().incrementStageNumber();

        // Assert all participants are correct
        output = new StringWriter();
        game.displayParticipants(new PrintWriter(output));

        String outputString = output.toString().trim();
        assertEquals(result, outputString);
    }

    @Then("{string} has {int} shields with the hand {string} in order")
    public void player_current_shields_and_hand(String playerName, int shields, String hand){
        StringWriter output;
        output = new StringWriter();

        Player player = game.getPlayer(playerName);

        // Assert P1 has no shields and has correct hand in order
        game.displayAdventureHand(new PrintWriter(output), player.getAdventureHand());

        String outputString = output.toString().trim();
        assertEquals(shields, player.getShields());
        assertEquals(hand, outputString);
    }

    @Given("2. a new game of Quests starts rigged")
    public void a_new_game_of_quests_starts_rigged2(){
        game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        // Player Rigged Hands
        game.getPlayer("P1").setAdventureHand(new ArrayList<>());
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P2").setAdventureHand(new ArrayList<>());
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("E30", 30, "Weapon", null));

        game.getPlayer("P3").setAdventureHand(new ArrayList<>());
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F10", 10, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P4").setAdventureHand(new ArrayList<>());
        game.getPlayer("P4").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("L20", 20, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("E30", 30, "Weapon", null));

        // Rigged event deck card draw
        game.overwriteEventDeckDraw(0, new Card("Q4", 4, "Quest", null));
        game.overwriteEventDeckDraw(1, new Card("Q3", 3, "Quest", null));

        // Rigged adventure deck card draw
        game.overwriteAdventureDeckDraw(0, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(1, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(2, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(3, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(4, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(5, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(6, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(7, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(8, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(9, new Card("L20", 20, "Weapon", null));

        game.overwriteAdventureDeckDraw(19, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(20, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(21, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(22, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(23, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(24, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(25, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(26, new Card("S10", 10, "Weapon", null));

    }

    @Then("The winners of the game are {string}")
    public void game_winners(String result){
        StringWriter output;

        // Assert winners displayed are correct
        output = new StringWriter();
        game.winnersPrompt(new PrintWriter(output));

        String outputString = output.toString().trim();
        assertEquals(result, outputString);
    }

    @Given("3. a new game of Quests starts rigged")
    public void a_new_game_of_quests_starts_rigged3(){
        game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        // Player Rigged Hands
        game.getPlayer("P1").setAdventureHand(new ArrayList<>());
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P2").setAdventureHand(new ArrayList<>());
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("E30", 30, "Weapon", null));

        game.getPlayer("P3").setAdventureHand(new ArrayList<>());
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F10", 10, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P4").setAdventureHand(new ArrayList<>());
        game.getPlayer("P4").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("L20", 20, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("E30", 30, "Weapon", null));

        // Rigged event deck card draw
        game.overwriteEventDeckDraw(0, new Card("Q4", 4, "Quest", null));
        game.overwriteEventDeckDraw(1, new Card("Plague", 0, "Event", "Lose 2 shields immediately"));
        game.overwriteEventDeckDraw(2, new Card("Prosperity", 0, "Event", "All players draw 2 adventure cards"));
        game.overwriteEventDeckDraw(3, new Card("Queen's favor", 0, "Event", "Draw 2 adventure cards"));
        game.overwriteEventDeckDraw(4, new Card("Q3", 3, "Quest", null));

        // Rigged adventure deck card draw
        game.overwriteAdventureDeckDraw(0, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(1, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(2, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(3, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(4, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(5, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(6, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(7, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(8, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(9, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(10, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(11, new Card("B15", 15, "Weapon", null));

        game.overwriteAdventureDeckDraw(12, new Card("F5", 5, "Foe", null));
        game.overwriteAdventureDeckDraw(13, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(14, new Card("F15", 15, "Foe", null));
        game.overwriteAdventureDeckDraw(15, new Card("F20", 20, "Foe", null));
        game.overwriteAdventureDeckDraw(16, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(17, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(18, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(19, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(20, new Card("S10", 10, "Weapon", null));

        game.overwriteAdventureDeckDraw(21, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(22, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(23, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(24, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(25, new Card("S10", 10, "Weapon", null));

        game.overwriteAdventureDeckDraw(26, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(27, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(28, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(29, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(30, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(31, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(32, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(33, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(34, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(35, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(36, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(37, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(38, new Card("B15", 15, "Weapon", null));

    }

    @Then("{string} draws the event card {string}")
    public void player_draws_event_card(String playerName, String cardName){
        StringWriter output = new StringWriter();
        String input = "\n";

        game.startPlayerTurn();
        game.nextPlayer();
    }

    @Then("{string} draws the event card {string} and trims hand with input {string}")
    public void player_draws_event_card_and_trims(String playerName, String cardName, String input){
        StringWriter output = new StringWriter();
        String processedInput = input.replace("\\n", "\n");

        game.startPlayerTurn();
        game.promptPlayer(new Scanner(processedInput), new PrintWriter(output));
        game.nextPlayer();
    }

    @Then("{string} draws a quest of {int} stages, possibly trimming hand, and {string} sponsors with input {string}")
    public void player_draws_quest_card(String playerName, int questValue, String sponsorName, String input){
        StringWriter output = new StringWriter();
        String processedInput = input.replace(", ", "\n");

        game.startPlayerTurn();
        game.promptPlayer(new Scanner(processedInput), new PrintWriter(output));
    }

    @Then("{string} draws and trims down to {int} cards with input {string}")
    public void sponsor_draws_and_trims(String playerName, int totalCards, String input){
        StringWriter output = new StringWriter();
        String processedInput = input.replace(", ", "\n");

        Player player = game.getPlayer(playerName);
        game.resolveQuest(new Scanner(processedInput), new PrintWriter(output));

        processedInput = "\n";
        game.promptFinishTurn(new Scanner(processedInput), new PrintWriter(output));
        game.nextPlayer();

        // Assert player has correct number of cards
        assertEquals(totalCards, player.getPlayerAdventureHandSize());
    }

    @Then("{string} has {int} shields")
    public void player_shields(String playerName, int shieldCount){
        Player player = game.getPlayer(playerName);
        assertEquals(shieldCount, player.getShields());
    }

    @Given("4. a new game of Quests starts rigged")
    public void a_new_game_of_quests_starts_rigged4(){
        game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayers();

        // Player Rigged Hands
        game.getPlayer("P1").setAdventureHand(new ArrayList<>());
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P1").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P1").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P2").setAdventureHand(new ArrayList<>());
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P2").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P2").addAdventureCard(new Card("E30", 30, "Weapon", null));

        game.getPlayer("P3").setAdventureHand(new ArrayList<>());
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F10", 10, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P3").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P3").addAdventureCard(new Card("L20", 20, "Weapon", null));

        game.getPlayer("P4").setAdventureHand(new ArrayList<>());
        game.getPlayer("P4").addAdventureCard(new Card("F5", 5, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F15", 15, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("F40", 40, "Foe", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("D5", 5, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("S10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("H10", 10, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("B15", 15, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("L20", 20, "Weapon", null));
        game.getPlayer("P4").addAdventureCard(new Card("E30", 30, "Weapon", null));

        // Rigged event deck card draw
        game.overwriteEventDeckDraw(0, new Card("Q2", 2, "Quest", null));

        // Rigged adventure deck card draw
        game.overwriteAdventureDeckDraw(0, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(1, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(2, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(3, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(4, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(5, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(6, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(7, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(8, new Card("F30", 30, "Foe", null));
        game.overwriteAdventureDeckDraw(9, new Card("L20", 20, "Weapon", null));
        game.overwriteAdventureDeckDraw(10, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(11, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(12, new Card("F5", 5, "Foe", null));
        game.overwriteAdventureDeckDraw(13, new Card("F10", 10, "Foe", null));
        game.overwriteAdventureDeckDraw(14, new Card("F15", 15, "Foe", null));
        game.overwriteAdventureDeckDraw(15, new Card("F20", 20, "Foe", null));
        game.overwriteAdventureDeckDraw(16, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(17, new Card("B15", 15, "Weapon", null));
        game.overwriteAdventureDeckDraw(18, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(19, new Card("S10", 10, "Weapon", null));
        game.overwriteAdventureDeckDraw(20, new Card("S10", 10, "Weapon", null));


    }


}
