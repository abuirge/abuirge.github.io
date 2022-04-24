import java.io.IOException;
import java.util.Scanner;

/**
 * Player is a Mobile object that is the users to move. It is able to be run in a Thread
 * @author Andrew Buirge
 *
 */
public class Player extends Mobile{

	/**
	 * board is a Board object to hold the board of Cells
	 */
	private Board board;
	/**
	 * input is used for getting user input
	 */
	private Scanner input = new Scanner(System.in);

	/**
	 * delayTime is used when a Player object is shared with a HomeworkTrap and the delay method is called
	 */
	private long delayTime;


	@Override
	public boolean isVisible() {
		return true;
	}

	/**
	 * Player constructor takes a Board object and uses the board for moving the Player object
	 * @param board of type Board passed in the creation of the Player object
	 */
	public Player(Board board) {
		super(board);
		this.board = board;

	}

	/**
	 * move scans for user input then enters and if statement. Within the statement, it determines what key input corresponds with the enumeration
	 * direction then invokes the Board move method with the correct direction and the stylus object itself
	 * @return If the user input does not match any key input false is returned, if any proper input true is returned
	 */
	protected void move() {
		delay();
		
		String userIn = input.nextLine();
		
		try {
			while(System.in.available()>0) {
				int buffer = System.in.available();
				byte x [] = new byte[buffer];
				System.in.read(x);
			}
		} catch (IOException e1) {
		}

		if(userIn.equalsIgnoreCase("q")) {
			board.move(Direction.UP_LEFT, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("w")) {
			board.move(Direction.UP, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("e")) {
			board.move(Direction.UP_RIGHT, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("a")) {
			board.move(Direction.LEFT, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("d")) {
			board.move(Direction.RIGHT, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("z")) {
			board.move(Direction.DOWN_LEFT, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("x")) {
			board.move(Direction.DOWN, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("c")) {
			board.move(Direction.DOWN_RIGHT, this);
			board.printBoard();
		}else if(userIn.equalsIgnoreCase("s")){
			board.printBoard();
		}else {

		}


	}

	/**
	 * share determines that Player object can not be in a cell with any other element
	 */
	public boolean share(Boardable elem) {

		return false;
	}

	/**
	 * setDelay sets the instance variable delayTime
	 * @param time long value of the delay to be set
	 */
	public void setDelay(long time) {
		this.delayTime = time;
	}

	/**
	 * delay checks if the delayTime is greater than 0, if so the Thread is put to sleep for the delayTime. After delay is over,
	 * delayTime is reset to 0
	 */
	private void delay() {

		if(this.delayTime > 0) {
			
			try {
				Thread.sleep(this.delayTime);
				setDelay(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				while(System.in.available()>0) {
					int buffer = System.in.available();
					byte x [] = new byte[buffer];
					System.in.read(x);
				}
			} catch (IOException e1) {
			}
		}

	}


	/**
	 * toString returns an asterisk for the Player to be represented on the board
	 */
	public String toString() {
		return "*";
	}

	@Override
	public synchronized void run() {
		do {
			move();
		}while(board.beenHugged() == false);

	}


}
