package giopollo.progetto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import giopollo.progetto.Exception.E_Between;
import giopollo.progetto.Exception.E_wordNotFound;
import giopollo.progetto.Model.Follower;
import giopollo.progetto.Request.Filter.Location;


@SpringBootTest
class ProgettoOopApplicationTests {

	private Follower f1 = null;
	private Follower f2 = null;
	List<Follower> lf = null;
	Location filterLoc = null;
	
	@BeforeEach
	void setUp() throws Exception {
		filterLoc = new Location();
		lf = new ArrayList<Follower>();
		f1 = new Follower("Paolo","Ancona",10,5);
		f2 = new Follower("Michele","Castelferretti",20,8);
		lf.add(f1);
		lf.add(f2);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals("Paolo", f1.getName());
		assertEquals("Ancona", f1.getLocation());
		assertEquals(10,f1.getFollowers_count());
		assertEquals(5,f1.getFriends_count());
	}
	
	
	@Test 
	void testException(){
		assertThrows(E_wordNotFound.class,()->{filterLoc.word(lf, "Milano");});
		
		ArrayList<Integer> ex = new ArrayList<Integer>();
		ex.add(10); ex.add(8);
		assertThrows(E_Between.class, ()->{filterLoc.between(lf, ex);});
	}
	
	@Test 
	void testLower(){
		lf = filterLoc.lower(lf, 2);
		assertTrue(lf.isEmpty());
	}
	
	@Test 
	void testGreater(){
		lf = filterLoc.greater(lf, 8);
		assertTrue(lf.size()==1);
	}
	
	@Test 
	void testBetween(){
		ArrayList<Integer> ex = new ArrayList<Integer>();
		ex.add(3); ex.add(8);
		lf = filterLoc.between(lf, ex);
		assertTrue(lf.size()==1);
	}

}
