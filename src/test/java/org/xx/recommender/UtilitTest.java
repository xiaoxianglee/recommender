package org.xx.recommender;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UtilitTest  extends TestCase{
	   /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UtilitTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( UtilitTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testComputeVectorLength()
    {
    	int[] vector = {1, 2, 3, 4, 5};
    	Double expected = Math.sqrt(55);
    	Double actual = Utility.computeVectorLength(vector);

        assertTrue( actual.compareTo(expected) == 0 );
    }
}
