package org.xx.recommender.itemCFSimpleExample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.xx.recommender.CF;
import org.xx.recommender.Utility;

public class ItemCF extends CF{
	public static double[][] itemSimilarityMatrix;

	public static int[] getItemVector(int item){
		int len = Test.ratingMatrix.length;
		int[] vector = new int[len];

		for (int i = 0; i < len; i ++) {
			vector[i] = Test.ratingMatrix[i][item];
		}

		return vector;
	}

	public static double computeItemSimilarity(int i1, int i2){
		int[] v1 = getItemVector(i1);
		int[] v2 = getItemVector(i2);

		return Utility.computeSimilarity(v1, v2);
	}

	public static void computeItemSimilarityMatrix() {
		itemSimilarityMatrix = new double[Test.bookCnt][Test.bookCnt];

		for (int book1 = 0; book1 < Test.bookCnt; book1 ++) {
			for (int book2 = 0; book2 < Test.bookCnt; book2 ++) {
				double similarity = computeItemSimilarity(book1, book2);
				itemSimilarityMatrix[book1][book2] = similarity;
				itemSimilarityMatrix[book2][book1] = similarity;
			}
		}
	}

	static{
		computeItemSimilarityMatrix();
	}

	public static List<Integer> getSimilarKItemList(int itemId, int K) {
		double[] similarItemArray = itemSimilarityMatrix[itemId];

		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for (int item = 0; item < similarItemArray.length; item ++) {
			map.put(item, similarItemArray[item]);
		}
		Set<Map.Entry<Integer, Double>> set = new TreeSet<Map.Entry<Integer, Double>>(
				new DescendComparator());

		for (Map.Entry<Integer, Double> entry : map.entrySet() ) {
			if(entry.getKey() != itemId)
				set.add(entry);
		}

		List<Integer> similarKItemList = CF.getPriorNElement(K, set);
		return similarKItemList;
	}

	public static List<Integer> getItemCandidateList(int userId, int K){
		List<Integer> itemCandidateList = new ArrayList<Integer>();

		Set<Integer> ratedBookSet = CF.getRatedBook(userId);
		for(Integer item : ratedBookSet){
			itemCandidateList.addAll(getSimilarKItemList(item, K));
		}

		return itemCandidateList;
	}

	public static int recommend(int userId, int K){
		Set<Integer> ratedBookSet = CF.getRatedBook(userId);

		Map<Integer, Double> itemCandidateMap = new HashMap<Integer, Double>();

		for(Integer item : ratedBookSet){
			List<Integer> similarItemList = getSimilarKItemList(item, K);
			for(Integer similarItem : similarItemList){
				if (ratedBookSet.contains(similarItem))
					continue;

				double score = Test.ratingMatrix[userId][item] * itemSimilarityMatrix[item][similarItem];

				if(itemCandidateMap.containsKey(similarItem) )
					score += itemCandidateMap.get(similarItem);

				itemCandidateMap.put(similarItem, score);
			}
		}

		if(itemCandidateMap.isEmpty())
			return -1;

		Set<Map.Entry<Integer, Double>> entrySet = itemCandidateMap.entrySet();
		Map.Entry<Integer, Double> maxEntry = Collections.max(entrySet, new AscendComparator());
		return maxEntry.getKey();
	}

}
