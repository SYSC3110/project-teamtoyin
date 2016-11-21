package JUniTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author Lina El Sadek
 * @date 11/1/2016
 * Modified Toyin Odujebe
 *
 */
@RunWith(Suite.class)
@SuiteClasses({MessageTest.class, NetworkTest.class, RandomAlgorithmTest.class, NodeTest.class, NodeGraphicTest.class,
	FloodingAlgorithmTest.class, ShortestPathAlgorithmTest.class, DepthFirstAlgorithmTest.class})
public class AllTests {
}
