package org.example;

import java.util.ArrayList;

public class Quest {
    int questValue;
    Player questSponsor;
    ArrayList<ArrayList<Card>> stages;
    ArrayList<Player> participants;
    int currentStageNumber;

    public Quest(Player player, Card questCard) {
        stages = new ArrayList<>();
        participants = new ArrayList<>();
        questValue = questCard.value;
        questSponsor = player;
        currentStageNumber = 1;
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

    public ArrayList<ArrayList<Card>> getStages(){
        return stages;
    }

    public Player getQuestSponsor() {
        return questSponsor;
    }

    public void addParticipant(Player player) {
        participants.add(player);
    }

    public ArrayList<Player> getParticipants() {
        return participants;
    }

    public int getCurrentStageNumber() {
        return currentStageNumber;
    }

    public int getCurrentStageIndex() {
        return currentStageNumber - 1;
    }

    public void incrementStageNumber() {
        currentStageNumber++;
    }

    public int getNumSponsoredCards() {
        int num = 0;
        for (ArrayList<Card> stage : stages) {
            for (Card card : stage) {
                num++;
            }
        }
        return num;
    }
}