import java.util.*;

public class Lab1d {
  public static void main(String[] args) {
        
        Random rand = new Random(System.currentTimeMillis());
        int ans = rand.nextInt(11);
        Scanner in = new Scanner(System.in);
        System.out.print("Guess a number between 0 and 10: ");
        int num = in.nextInt();

        if(num == ans)
            System.out.println("Right after 1 guess!");
        else{
            int guessCount = 2;
            boolean hasWon = false;
            String[] snarkyMessages = {
                "Better luck next time, genius.",
                "Oops, wrong again! You must be psychic... not.",
                "Did you mean to guess wrong?",
                "Well, that was a valiant effort... not.",
                "<Insert snarky message>",
                "You're on fire! Too bad it's the wrong answer.",
                "Nice try, but no cigar.",
                "Are you even trying?",
                "You're so close... to being completely off.",
                "Is that your final answer, or are you just testing fate?",
                "Wrong! Guess it's time to rethink your life choices."
            };
            int msgCount = rand.nextInt(11);


            while (hasWon == false){
                
                System.out.println(snarkyMessages[msgCount]);
                msgCount = (int)(10.0 * Math. random()) + 1;

                System.out.print("Guess again: ");
                int curr = in.nextInt();

                if(curr == ans){
                    System.out.println("Right after " + guessCount + " guesses!");
                    hasWon = true;
                }
                guessCount++;
            }
        }
  }


}
