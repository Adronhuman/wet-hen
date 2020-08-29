package com.company;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    //звичайні гетери і сетери

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    //виводить на екран масть і ранг даної карти
    public void println(){
        System.out.println(this.rank.getName()+" " + this.suit.getName());
    }

    public void print(){
        System.out.print(rank.getName()+" "+suit.getName());
    }
}