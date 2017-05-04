package org.xx.recommender.userCFSimpleExample;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xx.recommender.CF;
import org.xx.recommender.Utility;
import org.xx.recommender.userCFSimpleExample.Test;

import java.util.Set;
import java.util.TreeSet;

public class UserCF extends CF{

	public static double userSimilarityMatrix[][];

	public static int[] getUserVector(int user){
		return Test.ratingMatrix[user];
	}

	public static double computeUserSimilarity(int u1, int u2){
		int[] u1Vector = getUserVector(u1);
		int[] u2Vector = getUserVector(u2);

		return Utility.computeSimilarity(u1Vector, u2Vector);
	}

	public static void computeUserSimilarityMatrix(){
		userSimilarityMatrix = new double[Test.userCnt][Test.userCnt];

		for(int u1 = 0; u1 < Test.userCnt; u1++){
			for(int u2 = 0; u2 < Test.userCnt; u2++){
				double similarity = computeUserSimilarity(u1, u2);   //注意小数位数？？？？？
				userSimilarityMatrix[u1][u2] = similarity;
				userSimilarityMatrix[u2][u1] = similarity;
			}
		}
	}

	static{
		computeUserSimilarityMatrix();
	}


	public static List<Integer> getSimilarKUserList(int user, int K){
		double similarityArray[] = userSimilarityMatrix[user];
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for(int userId = 0; userId < similarityArray.length; userId ++){
			if(user != userId)
				map.put(userId, similarityArray[userId]);
		}

		Set<Map.Entry<Integer, Double>> set = new TreeSet<Map.Entry<Integer, Double> >(
				new DescendComparator());
		for(Map.Entry<Integer, Double> entry : map.entrySet()){
			set.add(entry);
		}

		List<Integer> similarKUserList = CF.getPriorNElement(K, set);
		return similarKUserList;
	}

	public static int recommend(int user, int K){
		Set<Integer> ratedBook = getRatedBook(user);
		List<Integer> similarUserList = getSimilarKUserList(user, K);

		Map<Integer, Double> candidateBookMap = new HashMap<Integer, Double>();

		for(Integer userId : similarUserList){
			int[] ratingArray = Test.ratingMatrix[userId];

			for(int bookId = 0; bookId < ratingArray.length; bookId ++){
				if(ratingArray[bookId] != 0 && ratedBook.contains(bookId) == false){
					double score = userSimilarityMatrix[user][userId] * ratingArray[bookId];

					if(candidateBookMap.containsKey(bookId)){
						score += candidateBookMap.get(bookId);
					}

					candidateBookMap.put(bookId, score);
				}
			}
		}

		if(candidateBookMap.isEmpty())
			return -1;

		Set<Map.Entry<Integer, Double>> entrySet = candidateBookMap.entrySet();
		Map.Entry<Integer, Double> maxEntry = Collections.max(entrySet, new AscendComparator());
		return maxEntry.getKey();
	}
}
