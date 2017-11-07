import javax.swing.JFrame;

public class Grid {
	public static final int GRID_SIZE = 10;
	private Cell cells[][];
	
	/**
	 * creates an empty grid that will house the ships;
	 */
	public Grid() {
		this.cells = new Cell[GRID_SIZE][GRID_SIZE];
		
		for (int r = 0; r < GRID_SIZE; ++r){
			for (int c = 0; c < GRID_SIZE; ++c){
				this.cells[r][c] = new Cell(r, c);
			}
		}
	}
	
	/**
	 * Returns the reference to the cell at the specified row and column.
	 * @param r The row of the cell.
	 * @param c The column of the cell.
	 * @return The cell reference.
	 */
	protected Cell getCell(int r, int c) {
		assert r >= 0 && r < GRID_SIZE;
		assert c >= 0 && c < GRID_SIZE;
		
		return this.cells[r][c];
	}
	
	/**
	 * Returns a String representation of the Grid.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		// Append the top of the grid box
		stringBuilder.append("+");
		for (int i = 0; i < GRID_SIZE; ++i) {
			stringBuilder.append("---+");
		}
		stringBuilder.append('\n');
		String lineSep = stringBuilder.toString();
		
		// Append each row of the grid. Separate each row of the grid by the
		// same line as used for the top of the grid
		for (int r = 0; r < GRID_SIZE; ++r) {
			for (int c = 0; c < GRID_SIZE; ++c) {
				int contents = this.cells[r][c].getContents();
				
				stringBuilder.append("| ");
				stringBuilder.append(String.format("%c", contents));
				stringBuilder.append(" ");
			}
			stringBuilder.append("|\n");
			stringBuilder.append(lineSep);
		}
		
		return stringBuilder.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println("Project4");
		Grid g = new Grid();
		System.out.println(g);
	}
	
}

