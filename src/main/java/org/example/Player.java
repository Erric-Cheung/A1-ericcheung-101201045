package org.example;

import java.util.ArrayList;

public class Player {
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
        adventureHand.sort(new Main.CardComparator());
    }

    public Card removeAdventureCard(int i) {
        return adventureHand.remove(i);
    }

    public Card getAdventureCard(int index) {
        return adventureHand.get(index);
    }

    public ArrayList<Card> getAdventureHand(){
        return adventureHand;
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
        if (shields < 0) {
            shields = 0;
        }
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


}