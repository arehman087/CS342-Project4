import java.util.HashMap;

public class Player {
	
	private HashMap <Character, Ship> shipSize;	//holds the type and the ship.
	private int fleet;		//number ships left in players fleet
	
	private Grid grid;		//instantiate a grid
	
	/**
	 * initializes the player
	 * and their fleets
	 * @param the grid used globally
	 */
	public Player(Grid g) {
		this.grid = g;
		
		this.fleet = 5;
		
		this.shipSize = new HashMap<Character, Ship>();
		shipSize.put('A', new Ship('H', 'A', 5));
        shipSize.put('B', new Ship('H', 'B', 4));
        shipSize.put('D', new Ship('H', 'D', 3));
        shipSize.put('S', new Ship('H', 'S', 3));
        shipSize.put('P', new Ship('H', 'P', 2));
	}
	
	/**
	 * @return number of ships
	 */
	public int getFleet(){
		return this.fleet;
	}
	
	/**
	 * @return the ship
	 */
	public Ship getShip(char s){
		return this.shipSize.get(s);
	}
	
	/**
	 * changes the orientation of the ship;
	 * @param s takes in the type of ship;
	 */
	public void changeOrient(char s){
		getShip(s).switchOrient();
	}
	
	/**
	 * checks if the ship is placeable on the board
	 * @param o orientation of the ship
	 * @param r row in which the ship is being placed
	 * @param c col in which the ship is being placed
	 * @param size ships size
	 * @return true if it can be place else false
	 */
	public boolean isPlaceable(char o, int r, int c, int size){
		if (o == 'H' && (c+size) > Grid.GRID_SIZE){
			System.out.println("ERROR H");
			return false;
		}
		else if (o == 'V' && (r+size) > Grid.GRID_SIZE){
			System.out.println("ERROR V");
			return false;
		}
		
		if (o == 'H'){
			for (int i = c; i < c+size; ++i){
				if (this.grid.getCell(r, i).getContents() != ' '){
					return false;
				}
			}
		}
		else {
			for (int i = r; i < r+size; ++i){
				if (this.grid.getCell(i, c).getContents() != ' '){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * places a ship on the board
	 */
	public void placeShip(int r, int c, char t){
		Ship temp = this.shipSize.get(t);
		int size = temp.getVessal().length;
		char orient = temp.getOrient();
		
		if(!isPlaceable(orient, r, c, size)){
			return;
		}
		
		this.shipSize.get(t).setRow(r);
		this.shipSize.get(t).setCol(c);
		
		if (orient == 'H'){
			for (int i = 0; i < size; ++i){
				this.grid.getCell(r, c+i).setContents(t);
				
			}
		}
		else {
			for (int i = 0; i < size; ++i){
				this.grid.getCell(r+i, c).setContents(t);
				
			}
		}
	}
	
	/**
	 * checks if any ship the player has is destroyed.
	 */
	public void checkFleet(){
		int count = 0;
		for (Character s : this.shipSize.keySet()){
			if (this.shipSize.get(s).isSunk()){
				count++;
			}
		}
		if (count == 5){
			this.fleet = 0;
		}
		else {
			this.fleet = 5-count;
		}
	}
	
	/**
	 * player attacks opponents 
	 * @param opG opponent player's grid
	 * @param op oponnent player
	 * @param r row of the cell
	 * @param c col or the cell
	 * @return true or false it the ship has been hit or not (may need fixing so that 'X' is not over written)
	 */
	
	public boolean attackOP(Grid opG, Player op, int r, int c){
		if (opG.getCell(r, c).getContents() == ' ' ){
			opG.getCell(r, c).setContents('M');
			return false;
		}
		else if(opG.getCell(r, c).getContents() != 'M' && opG.getCell(r, c).getContents() != ' ' && opG.getCell(r, c).getContents() != 'X' ){
			char orient = op.getShip(opG.getCell(r, c).getContents()).getOrient();
			
			if (orient == 'H'){
				int pos = c - op.getShip(opG.getCell(r, c).getContents()).getCol();
				op.getShip(opG.getCell(r, c).getContents()).getVessal()[pos] = 'X';
				op.checkFleet();
				
			}
			else {
				int pos = r - op.getShip(opG.getCell(r, c).getContents()).getRow();
				op.getShip(opG.getCell(r, c).getContents()).getVessal()[pos] = 'X';
				op.checkFleet();
			}
			
			opG.getCell(r, c).setContents('X');
			return true;
		}
		else {
			//may work?
			if (opG.getCell(r, c).getContents() == 'X'){
				return true;
			}
			return false;
		}
		
	}
	/**
	 * @return gives the players instamce of the grid;
	 */
	public Grid getGrid(){
		return this.grid;
	}
	
	
	
	
	
}
