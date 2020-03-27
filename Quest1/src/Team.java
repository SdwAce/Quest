import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Team{
	protected List<Hero> heros;
	protected int curr_row;
	protected int curr_col;
	protected int num;
	
	public Team(List<Hero> heros,int num,int curr_row, int curr_col) {
		this.heros = heros;
		this.num = num;
		this.curr_row = curr_row;
		this.curr_col=curr_col;
	}
	public void makeMove(Board board1,String direction) {
		//leave current space
		if(board1.boardcells[curr_row][curr_col].type.equals("common")){
				board1.board[curr_row][curr_col] = ' ';
		}else if(board1.boardcells[curr_row][curr_col].type.equals("market")){
			board1.board[curr_row][curr_col] = 'M';
	    } 
		if (direction.equals("a")) {
	        curr_col-=1;
	        board1.board[curr_row][curr_col] = 'P';
		}else if (direction.equals("d")) {
	        curr_col+=1;
	        board1.board[curr_row][curr_col] = 'P';
		}else if (direction.equals("w")) {
			curr_row-=1;
			board1.board[curr_row][curr_col] = 'P';
		}else {
			curr_row+=1;
			board1.board[curr_row][curr_col] = 'P';
			
		}
	}
	
}