
package de.waschnick.challenge.reddit.hard;

import java.util.Random;

/**
 * http://www.reddit.com/r/dailyprogrammer/comments/pii6j/difficult_challenge_1/
 * 
 * we all know the classic "guessing game" with higher or lower prompts. lets do a role reversal; you create a program
 * that will guess numbers between 1-100, and respond appropriately based on whether users say that the number is too
 * high or too low. Try to make a program that can guess your number based on user input and great code!
 */
public class Challange_001 {

  public static void main(String[] args) throws Exception {

    Challange_001 me = new Challange_001();
    me.runGame();
  }

  private void runGame() throws Exception {
    int myGuess = randomNumber();
    long result = guess(myGuess);

    while (result != 0) {
      if (result > 0) {
        myGuess++;
      } else {
        myGuess--;
      }
      result = guess(myGuess);
    }

    System.out.println("WIIINNNN!");

  }

  private int randomNumber() {
    return new Random().nextInt(101);
  }

  private int guess(int number) throws Exception {
    System.out.println("My guess is " + number + ". Am I right? y/n");
    String s = "" + (char) System.in.read();
    if (s.equals("y")) {
      return 0;
    }

    System.out.println("Is the number less than " + number + "? y/n");
    s = "" + (char) System.in.read();
    if (s.equals("y")) {
      return -1;
    }
    return 1;
  }
}
