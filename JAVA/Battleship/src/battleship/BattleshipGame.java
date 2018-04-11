package battleship;

import java.util.Scanner;

public class BattleshipGame {

	Ocean ocean;
	boolean playFlag;

	public BattleshipGame() {
		playFlag = true;
	}

	/**
	 * set up the game
	 */
	public void setUp(){
		this.ocean = new Ocean(); 
		this.ocean.placeAllShipsRandomly();
	}

	/**
	 * play method
	 */
	public void play(){
		int row;
		int column;		
		Scanner scanner = new Scanner(System.in);
		while (playFlag) {
			setUp();
			System.out.println("*****************************************");
			System.out.println("Welcome to the Battleship Game!");
			while (!this.ocean.isGameOver()) {
				System.out.println("*****************************************");
				System.out.println("Total Fired:	" + ocean.getShotsFired());
				System.out.println("Total Hit:	" + ocean.getHitCount());
				System.out.println("Total Sunk:	" + ocean.getShipsSunk());
				System.out.println("*****************************************");
				this.ocean.print();
				System.out.println("*****************************************");
				System.out.println("Please enter row and column number:");
				System.out.print("Row:");
				String rowIn = scanner.next();
				System.out.print("Column:");
				String columnIn = scanner.next();
				if (rowIn.matches("\\d") && columnIn.matches("\\d")) {
					row = Integer.parseInt(rowIn);
					column = Integer.parseInt(columnIn);
					if (ifInRange(row, column)) {
						this.ocean.shootAt(row, column);
					}
					else{
						System.out.println("Illegal Position! Please enter again!");
					}		
				}
				else
				{
					System.out.println("Illegal Position! Please enter again!");
				}
			}
			System.out.println("*****************************************");
			System.out.println("Total Fired:	" + ocean.getShotsFired());
			System.out.println("Total Hit:	" + ocean.getHitCount());
			System.out.println("Total Sunk:	" + ocean.getShipsSunk());
			System.out.println("*****************************************");
			this.ocean.print();
			System.out.println("*****************************************");
			System.out.println("Game Over!");
			System.out.println("Your Score is " + ocean.getShotsFired() + "!");
			System.out.println("*****************************************");
			System.out.println("Do you want to play again? [y/n]");
			playFlag = scanner.next().toLowerCase().equals("y");
		}
		System.out.println("Program Quit!");
		scanner.close();
	}	

	/**
	 * check if row and column in the legal range
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean ifInRange(int row, int column){
		if (row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {
		BattleshipGame battleshipGame = new BattleshipGame();
		battleshipGame.play();
	}

}
