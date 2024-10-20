package org.example;

public class Card {
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