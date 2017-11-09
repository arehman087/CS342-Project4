
public class Ship {
	
	private char type;		//ship type
	private char orient;	//vertical or horizontal
	private char vessal[];	//holds the status of the ship segment (hit(X)/normal(M))
	private int row;		//row of beginning segment
	private int col;		//col of beginning segment
	private int size;
	/**
	 * creates the ships and holds their statuses.
	 * @param o holds the orientation of the ship.
	 * @param t holds the type of the ship (ex: patrol, sub, etc).
	 * @param size holds the size of the ship.
	 */
	public Ship( char o, char t, int size) {
		this.orient = o;
		this.type = t;
		this.size = size;
		this.vessal = new char[size];
		for (int i = 0; i < size; ++i){
			this.vessal[i] = 'M';
		}
		
	}
	
	/**
	 * checks if the ship is destroyed
	 */
	public boolean isSunk(){
		int count = 0;
		for (int i = 0; i < this.size; ++i){
			if (this.vessal[i] == 'X'){
				++count;
			}
		}
		if (count == this.size){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * gets the type of the ship.
	 * @return type of the ship
	 */
	public char getType(){
		return this.type;
	}
	
	/**
	 * gets orientation of the ship
	 * @return 'V' or 'H'
	 */
	public char getOrient(){
		return this.orient;
	}
	
	public void switchOrient(){
		if (this.orient == 'H'){
			this.orient = 'V';
		}
		else{
			this.orient = 'H';
		}
	}
	
	/**
	 * gets the array of statuses of the ship
	 * @return array of the ship
	 */
	public char[] getVessal(){
		return this.vessal;
	}
	
	/**
	 * sets the beginning of the ship
	 * @param r row on where the ship starts
	 */
	public void setRow(int r){
		this.row = r;
	}
	
	/**
	 * sets the beginning of the ship
	 * @param c col on where the ship starts
	 */
	public void setCol(int c){
		this.col = c;
	}
	
	/**
	 * gets the beginning of the ship
	 * @return row of where it begins
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * gets the beginning of the ship
	 * @return row of where it begins
	 */
	public int getCol(){
		return this.col;
	}
	
}
