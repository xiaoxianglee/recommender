package org.xx.recommender;

import java.util.List;

import org.xx.recommender.itemCFSimpleExample.ItemCF;

import junit.framework.TestCase;

public class ItemCFTest  extends TestCase{

	public void testComputeUserSimilarityMatrix() {
		double[][] expected = {
				{1.00 * 100, 0.27 * 100, 0.79 * 100, 0.32 * 100, 0.98 * 100, 0.00 * 100},
				{0.27 * 100, 1.00 * 100, 0.00 * 100, 0.00 * 100, 0.34 * 100, 0.65 * 100},
				{0.79 * 100, 0.00 * 100, 1.00 * 100, 0.69 * 100, 0.71 * 100, 0.18 * 100},
				{0.32 * 100, 0.00 * 100, 0.69 * 100, 1.00 * 100, 0.32 * 100, 0.49 * 100},
				{0.98 * 100, 0.34 * 100, 0.71 * 100, 0.32 * 100, 1.00 * 100, 0.00 * 100},
				{0.00 * 100, 0.65 * 100, 0.18 * 100, 0.49 * 100, 0.00 * 100, 1.00 * 100},
		};

		int rowCnt = expected.length;
		int columnCnt = expected[0].length;

		for (int i = 0; i < rowCnt; i ++) {
			for (int j = 0; j < columnCnt; j ++) {
				assertEquals(expected[i][j], ItemCF.computeItemSimilarity(i, j) * 100);
			}
		}
	}

	public void testGetSimilarKItemList() {
		int[][] expected = {
				{4, 2, 3},
				{5, 4, 0},
				{0, 4, 3},
				{2, 5, 0},
				{0, 2, 1},
				{1, 3, 2}
		};

		int rowCnt = expected.length;
		int columnCnt = expected[0].length;

		for (int i = 0; i < rowCnt; i ++) {
			List<Integer> actual = ItemCF.getSimilarKItemList(i, 3);
			for (int j = 0; j < columnCnt; j ++) {
				assertEquals( expected[i][j], actual.get(j).intValue());
			}
		}
	}

	public void testRecommend() {
		int actual = ItemCF.recommend(0,  2);
		assertEquals(2, actual);
	}
}
