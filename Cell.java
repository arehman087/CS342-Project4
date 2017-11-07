
public class Cell {
	private int row;		//row in grid
	
	private int col;		//column in grid
	
	private char contents;	//contents of cell
	
	
	/**
	 * initializes the cell when there are no ships on the board
	 * @param row The row of the cell.
	 * @param col The column of the cell.
	 * @param con The contents of the cell
	 */
	public Cell(int r, int c) {
		this.row = r;
		this.col = c;
		this.contents = ' ';
	}

	
	public void setRow(int r){
		this.row = r;
	}
	
	public void setCol(int c){
		this.col = c;
	}
	
	public void setContents(char con){
		this.contents = con;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public char getContents(){
		return this.contents;
	}
	
	
}
