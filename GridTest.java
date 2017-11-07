import static org.junit.Assert.*;

import org.junit.Test;

public class GridTest {

	@Test
	public void makeGridTest() {
		Grid g = new Grid();
		
		Player p = new Player(g);
		
		p.changeOrient('A');
		
		p.placeShip(5, 6, 'A');
		p.placeShip(6, 5, 'S');
		
		for (int i = 5; i <10; ++i ){
			assertEquals(g.getCell(i, 6).getContents(), 'A');
		}
		
		for (int i = 5; i <8; ++i ){
			assertNotEquals(g.getCell(i, 6).getContents(), 's');
		}
		
	}

}
