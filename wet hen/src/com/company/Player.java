package com.company;

import java.util.Scanner;

public class Player {
    private int countOfCards = 0;//кількість карт в гравця
    private Card[] set = new Card[Deck.CARD_COUNT];//набір карт грався

    //повертає кількість карт даного гравця
    public int getCountOfCards(){
        return countOfCards;
    };

    //додає карти до набору карт гравця з масиву що передаємо
    public void takeCards(int count,Card [] cards){
        for(int i = 0;i<count;i++){
            set[countOfCards+i] = cards[count-1-i];
            cards[cards.length-1-i]=null;
        }
        countOfCards+=count;
    }
    //додає кату передану як аргумент до набору карт
    public void takeCards(Card card){
        set[countOfCards]=card;
        countOfCards+=1;
    };
    //видаляє і повертає карту з заданим номером з набору карт
    public Card putCard(int number){
        Card buf =  set[number-1];
        for (int i = number-1;i<countOfCards-1;i++){
            set[i] = set[i+1];
        }
        countOfCards--;
        set[countOfCards]=null;
        return buf;
    }
    //виводить на екран карти гравця
    public void printSet(){
        for (int i = 0;i<countOfCards;i++){
            System.out.print(" "+(i+1)+"- ");
            set[i].print();
        }
    }

    //шукає задану карту і повертає її або нуль якщо такої немає
    public Card search(Rank rank){
        for (var x:set){
            if (x.getRank().getName().equals(rank.getName())){
                return x;
            }
        }
        return null;
    }
    //шукає карту масть якої відрізняється від заданої
    public int search(Suit suit){
        int counter = 1;
        for (int i=0;i<countOfCards;i++){
            Card x = set[i];
            if (!x.getSuit().getName().equals(suit.getName())){
                return counter;
            }
            counter++;
        }
        return 0;
    }
    public Card search(Rank rank, Suit suit){
        for (var x:set){
            if (x.getRank().getName().equals(rank.getName())&&(x.getSuit().getName().equals(suit.getName()))){
                return x;
            }
        }
        return null;
    }


}
