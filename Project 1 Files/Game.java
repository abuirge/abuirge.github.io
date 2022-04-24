
import java.util.Random;
import java.util.Scanner;

/**
 * Game is the driver for playing Hug the Angry Jarvis game
 * @author Andrew Buirge
 *
 */
public class Game {

	/**
	 * main gets the desired height and width for the board, creates the board, creates a Player and Jarvis and places on the board. Then main
	 * loops through the two threads run functions until hugged is true, signifying the end of the game and ending execution.
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		Scanner otherIn = new Scanner(System.in);
		int height;
		int width;
		do {
			System.out.println("Please enter board height between 1-100");
			height = otherIn.nextInt();
			if(height > 100 || height < 1) {
				System.out.println("Incorrect size");
			}
		}while(height > 100 || height < 1);

		do {
			System.out.println("Please enter board width between 1-100");
			width = otherIn.nextInt();
			if(width > 100 || width < 1) {
				System.out.println("Incorrect size");
			}
		}while(width > 100 || width < 1);

		Board board = new Board(height, width);
		Player p = new Player(board);
		Jarvis j = new Jarvis(board);
		
	
		board.placeElement(p, 0, 0);
		board.placeElement(j, rand.nextInt(height), rand.nextInt(width));
		board.printBoard();

		Thread s1 = new Thread(p);
		Thread j1 = new Thread(j);
		
		s1.start();
		j1.start();
		
		do {
			s1.run();
			j1.run();
		}while(!board.beenHugged());

		System.out.println("Jarvis has been soothed by your hug! Thanks for playing!");
		otherIn.close();


	}

}
