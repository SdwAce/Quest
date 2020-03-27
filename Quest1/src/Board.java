import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board{
	protected Cells[][] boardcells;
	protected char[][] board;
	public int rowofBoard;
	public int colofBoard;
	protected List<Integer> row_nonacc;
	protected List<Integer> row_common;
	protected List<Integer> row_market;
	protected List<Integer> col_nonacc;
	protected List<Integer> col_common;
	protected List<Integer> col_market;
    
	public Board(int row_size,int col_size){
		rowofBoard = row_size;
		colofBoard = col_size;
		board = new char[rowofBoard][colofBoard];
		System.out.print(board[1]);
		random_select();
		
		//initialize the board
		board_init();
		printBoard();
	}
	public void board_init() {
		for (int i=0;i<rowofBoard;i++) {
			for (int j=0;j<colofBoard;j++) {
				if(boardcells[i][j].type.equals("non_accessible")) {
					board[i][j]='X';
				}else if (boardcells[i][j].type.equals("common")) {
				    board[i][j]=' ';
				}else if (boardcells[i][j].type.equals("market")) {
					board[i][j]='M';
				}
			}
		}
	}
	/*Check valid move*/
	public boolean valid_move(Team team,String direction) {
		int future_row = team.curr_row;
		int future_col = team.curr_col;
		if (direction.equals("q")) {
			return true;
		}else if (direction.equals("i")) {
			return true;
		}else if (direction.equals("a")) {
			future_col = team.curr_col-1;
			if (future_col<0 || future_col>=colofBoard || boardcells[future_row][future_col].type.equals("non_accessible")) {
				return false;
			}
		}else if (direction.equals("d")) {
			future_col = team.curr_col+1;
			if (future_col<0 || future_col>=colofBoard || boardcells[future_row][future_col].type.equals("non_accessible")) {
				return false;
			}
		}else if (direction.equals("w")) {
			future_row = team.curr_row-1;
			if (future_row<0 || future_row>=rowofBoard || boardcells[future_row][future_col].type.equals("non_accessible")) {
				return false;
			}
		}else {
			future_row = team.curr_row+1;
			if (future_row<0 || future_row>=rowofBoard || boardcells[future_row][future_col].type.equals("non_accessible")) {
				return false;
			}
		}
		return true;
	}
	
	public void random_select() {
		boardcells = new Cells[rowofBoard][colofBoard];
		Random rand = new Random(); 
		for(int i =0;i<rowofBoard;i++) {
			for(int j =0;j<colofBoard;j++) {
				double random = rand.nextDouble();
				String type1="";
				if (random<0.2) {
					type1 = "non_accessible";
				}else if (random<0.5) {
					type1 = "market";
				}else {
					type1 = "common";
				}
				boardcells[i][j] = new Cells(type1);
			}
			
		}
		
	}
	  
	
	/* print the current state of the board*/
		public void printBoard() {
			Scanner s1 = new Scanner(System.in);
			String input;
			System.out.println("Current Game Board:");
			System.out.println("---------------------------------");
			for(int i=0;i<rowofBoard;i++) {
				System.out.print("| ");
				for(int j=0;j<colofBoard;j++) {
					System.out.print(board[i][j]+" | ");
				}
				System.out.println();
				System.out.println("---------------------------------");
			}
			System.out.println();
		}
		
		
}