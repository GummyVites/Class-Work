// Guess.java
// Kevin Lee
// 1480757
// pa2
// Guessing number game
import java.util.Random;
import java.util.Scanner;

class Guess{
        public static void main(String[] args){
        int guess1, guess2, guess3;
        int ranNumber = (int)(Math.random() * 11);
        Scanner sc = new Scanner(System.in);
        System.out.println("I'm thinking of an integer in the range 1 to 10. You have three guesses.");
        System.out.println("");
        System.out.print("Enter your first guess: ");
        guess1 =sc.nextInt();
        if (ranNumber > guess1){
                System.out.println ("Your guess is too low.");
                System.out.println("");
        } else if (ranNumber < guess1){
                System.out.println ("Your guess is too high.");
                System.out.println("");
        } else {
                System.out.println ("You win!");
                System.exit(0);
        }
        System.out.print("Enter your second guess: ");
        guess2 =sc.nextInt();
        if (ranNumber > guess2){
                System.out.println ("Your guess is too low.");
                System.out.println("");
        } else if (ranNumber < guess2){
                System.out.println ("Your guess is too high.");
                System.out.println("");
        } else {
                System.out.println ("You win!");
                System.exit(0);
        }
        System.out.print("Enter your Third guess: ");
        guess3 =sc.nextInt();
        if (ranNumber > guess3){
                System.out.println ("Your guess is too low.");
                System.out.println("");
        } else if (ranNumber < guess3){
                System.out.println ("Your guess is too high.");
                System.out.println("");
        } else  {
                System.out.println ("You win!");
                System.exit(0);
        }
                System.out.print("you lose. The number was ");
                System.out.println(+ranNumber+".");
        }
}

