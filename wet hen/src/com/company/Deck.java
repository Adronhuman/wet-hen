package com.company;

import java.util.Random;
import com.company.Suit;
import com.company.Rank;
import com.company.Card;
import com.company.*;

public class Deck {

    public static final int CARD_COUNT = 36;
    public int cardNumber = 0;

    public Card[] cards = new Card[CARD_COUNT];

    public Deck() {
        for (int rank = 0; rank < Rank.values.length; rank++) {
            for (int suit = 0; suit < Suit.values.length; suit++) {
                cards[cardNumber] = new Card(Rank.values[rank], Suit.values[suit]);
                cardNumber++;
            }
        }
    }


    //Перемішує колоду у випадковому порядку
    public void shuffle() {
        cardNumber = 35;
        for(int i = 0;i<36;i++){
            Random random = new Random();
            int x1 = random.nextInt(36);
            int x2 = random.nextInt(36);
            Card temp = cards[x1];
            cards[x1] = cards[x2];
            cards[x2] = temp;
        }
    }

    //сортування (повернення колоди до стану щойно розпакованої)//
    public void order() {
        cardNumber = CARD_COUNT;
        int counterr = 0;
        for(Suit suit1 :Suit.values){
            for (Rank rank1 : Rank.values){
                Card card = new Card(rank1,suit1);
                cards[counterr] = card;
                counterr++;
            }
        }
    }
    //Показує чи можна витягти карту
    public boolean hasNext() {
        if (cardNumber>0){
            return true;
        }
        else{
            return false;
        }
    }


    //"Виймає" одну карту з колоди якщо це можливо
    public Card drawOne() {
        if (cardNumber>0){
            cardNumber--;
            return cards[cardNumber];
        }else{
            System.out.println("Колода закінчилась");
            return null;
        }
    }
    //виводить на екран ранг і масть всіх карт в такому порядку в якому вони знаходяться в колоді
    public void print_na_ekran(){
        for(Card card :cards){
            System.out.print(card.getRank().getName() +" "+card.getSuit().getName()+" ");
        }
        System.out.println();
    }

}
