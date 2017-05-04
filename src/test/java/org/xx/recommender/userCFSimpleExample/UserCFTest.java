package org.xx.recommender.userCFSimpleExample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

public class UserCFTest extends TestCase{


    public void testComputeUserSimilarity(){
    	assertEquals(1.00 * 100, UserCF.computeUserSimilarity(0, 0) * 100);
    }

    public void testComputeUserSimilarityMatrix(){
    	double[][] expected  = {
    			{ 1.00 * 100, 0.75 * 100, 0.63 * 100, 0.22 * 100, 0.30 * 100, 0.00 * 100},
    			{ 0.75 * 100, 1.00 * 100, 0.91 * 100, 0.00 * 100, 0.00 * 100, 0.16 * 100},
    			{ 0.63 * 100, 0.91 * 100, 1.00 * 100, 0.00 * 100, 0.00 * 100, 0.40 * 100},
    			{ 0.22 * 100, 0.00 * 100, 0.00 * 100, 1.00 * 100, 0.97 * 100, 0.64 * 100},
    			{ 0.30 * 100, 0.00 * 100, 0.00 * 100, 0.97 * 100, 1.00 * 100, 0.53 * 100},
    			{ 0.00 * 100, 0.16 * 100, 0.40 * 100, 0.64 * 100, 0.53 * 100, 1.00 * 100}
    	};

    	int rowCnt = expected.length;
    	int columnCnt = expected[0].length;

    	for(int i = 0; i < rowCnt; i ++){
    		for(int j = 0; j < columnCnt; j ++){
    			assertEquals(expected[i][j], UserCF.computeUserSimilarity(i, j) * 100);
    		}
    	}
    }

    public void testGetRatedBook(){
    	int ratedBookArray[][] = {
    			{0, 1, 4},
    			{0, 2, 4},
    			{0, 2, 3, 4},
    			{1, 5},
    			{1, 5},
    			{2, 3, 5}
    	};

    	int userCnt = ratedBookArray.length;
    	userCnt = 1;
    	for(int u = 0; u < userCnt; u ++){
    		Set<Integer> actual = UserCF.getRatedBook(u);

    		Set<Integer> expected = new HashSet<Integer>();
    		for(int i : ratedBookArray[u]){
    			expected.add(i);
    		}

    		assertEquals(expected, actual);
    	}
    }

    public void testGetSimilarKUserList(){
    	int expectedAll[][] = {
    			{1, 2, 4},
    			{2, 0, 5},
    			{1, 0, 5},
    			{4, 5, 0},
    			{3, 5, 0},
    			{3, 4, 2}
    	};
    	int userCnt = expectedAll.length;

    	for(int user = 0; user < userCnt; user ++){
    		List<Integer> actual = UserCF.getSimilarKUserList(user, 3);

    		List<Integer> expected = new ArrayList<Integer>();
    		for(int i = 0; i < 3; i ++){
    			expected.add(expectedAll[user][i]);
    		}

    		assertEquals(expected, actual);
    	}
    }

    public void testRecommend(){
    	int actual = UserCF.recommend(0, 2);
    	assertEquals(2, actual);
    }
}
