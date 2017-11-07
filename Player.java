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
		//fix
		this.shipSize = new HashMap<Character, Ship>();
		shipSize.put('A', new Ship('H', 'A', 5));
        shipSize.put('B', new Ship('H', 'B', 4));
        shipSize.put('D', new Ship('H', 'D', 3));
        shipSize.put('S', new Ship('H', 'S', 3));
        shipSize.put('P', new Ship('H', 'P', 2));
	}
	
	/**
	 * set the number of ships
	 * @param f number of ships
	 */
	public void setFleet(int f){
		this.fleet = f;
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
	
	public void changeOrient(char s){
		getShip(s).switchOrient();
	}
	
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
		//not done
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
}
