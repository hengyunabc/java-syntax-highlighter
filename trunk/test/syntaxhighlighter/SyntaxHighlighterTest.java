package syntaxhighlighter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighterTest {

  public SyntaxHighlighterTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void test() {
    // there is no testing for brushes in original JavaScript version of SyntaxHighlighter and so far there is no plan for me to create some myself.
    // if you want something more reliable, you can choose Java Prettify (http://java-prettify.googlecode.com), which has much more tests.
    assertTrue(true);
  }
}
