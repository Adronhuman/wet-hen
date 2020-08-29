package com.company;
import java.util.Scanner;
public class Chicken implements Game{
    private Deck deck = new Deck(); //основна колода карт(карти що розвернуті сорочкою наверх)
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Card[] buffer = new Card[Deck.CARD_COUNT];//купка карт що використовується в грі безпосередньо(карти що вже відкриті для гравців)
    private int countCardInBuff = 0;//кількість карт в buffer
    private boolean status = false;
    private int queue=1;

    public boolean pauseGame(){
        return true;
    }
    public boolean finishGame(){
        status = false;
        return true;
    }
    public boolean startGame(int startCountOfCards){
        status = true;
        deck.shuffle();//тасуємо колоду на початку гри

        //роздаємо кожному з гравців по початковій кількості карт
        for(int i = 0;i<startCountOfCards;i++){
            Card buf1 = deck.drawOne();
            Card buf2 = deck.drawOne();
            player1.takeCards(buf1);
            player2.takeCards(buf2);
        }

        //витягуємо першу карту
        buffer[countCardInBuff] = deck.drawOne();
        countCardInBuff++;

        //основна частина гри
        while(status) {
            if (queue==1) {
                if (player1.getCountOfCards()==0){
                    if (deck.hasNext()){
                        System.out.println("number of cards 2 player - " +player2.getCountOfCards());
                        System.out.print("last card - ");
                        buffer[countCardInBuff - 1].println();
                        System.out.print("Enter 1 to take card from deck");
                        Scanner sc = new Scanner(System.in);
                        sc.nextInt();
                        Card buf = deck.drawOne();
                        System.out.print("You take - ");
                        buf.println();
                        if (buf.getSuit().getName().equals(buffer[countCardInBuff-1].getSuit().getName())){
                            player1.takeCards(countCardInBuff,buffer);
                            countCardInBuff=0;
                            buffer[countCardInBuff]=buf;
                            countCardInBuff++;
                        }
                        else{
                            buffer[countCardInBuff]=buf;
                            countCardInBuff++;
                        }
                        queue=2;
                    }else{
                        System.out.println("player1 win");
                        break;
                    }
                }
                else {
                    System.out.println("number of cards 2 player - " +player2.getCountOfCards());
                    System.out.print("last card - ");
                    buffer[countCardInBuff - 1].println();
                    System.out.println("Your cards - ");
                    player1.printSet();
                    System.out.println();
                    Scanner sc = new Scanner(System.in);
                    int x = sc.nextInt();
                    if (x<1){
                        finishGame();
                        continue;
                    }
                    while(x>player1.getCountOfCards()){
                        System.out.println(":|");
                        x = sc.nextInt();
                    }
                    Card buf = player1.putCard(x);
                    if (buf.getSuit().getName().equals(buffer[countCardInBuff - 1].getSuit().getName())) {
                        player1.takeCards(countCardInBuff, buffer);
                        countCardInBuff = 0;
                        buffer[countCardInBuff] = buf;
                        countCardInBuff++;
                    } else {
                        buffer[countCardInBuff] = buf;
                        countCardInBuff++;
                    }
                    queue = 2;
                }
            }else{
                if (player2.getCountOfCards()==0){
                    if (deck.hasNext()){
                        Card buf = deck.drawOne();
                        if (buf.getSuit().getName().equals(buffer[countCardInBuff-1].getSuit().getName())){
                            player2.takeCards(countCardInBuff,buffer);
                            countCardInBuff=0;
                            buffer[countCardInBuff]=buf;
                            countCardInBuff++;
                        }
                        else{
                            buffer[countCardInBuff]=buf;
                            countCardInBuff++;
                        }
                    }else{
                        System.out.println("player2 win");
                        break;
                    }
                    queue=1;
                }
                else {
                    Card last = buffer[countCardInBuff - 1];
                    int buf = player2.search(last.getSuit());
                    if (buf == 0) {
                        player2.takeCards(countCardInBuff, buffer);
                        countCardInBuff = 0;
                        buffer[countCardInBuff] = player2.putCard(1);
                        countCardInBuff++;
                        queue = 1;
                        continue;
                    }
                    buffer[countCardInBuff] = player2.putCard(buf);
                    countCardInBuff += 1;
                    queue = 1;
                }
            }

        }
        return true;
    }
    public static void main(String[] args){
        Chicken chicken = new Chicken();
        chicken.startGame(5);
        System.out.print("Game ended");
    }
}
