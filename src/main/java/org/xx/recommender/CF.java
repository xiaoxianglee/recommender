package org.xx.recommender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.xx.recommender.userCFSimpleExample.Test;

public abstract class CF {
	public static Set<Integer> getRatedBook(int u){
		int[] bookArray = Test.ratingMatrix[u];

		Set<Integer> bookSet = new HashSet<Integer>();

		for(int bookId = 0; bookId < bookArray.length; bookId ++){
			if(Test.ratingMatrix[u][bookId] != 0)
				bookSet.add(bookId);
		}

		return bookSet;
	}

	public static class DescendComparator implements Comparator<Map.Entry<Integer, Double>>{
		public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
			return o2.getValue().compareTo(o1.getValue());
		}
	}

	public static class AscendComparator implements Comparator<Map.Entry<Integer, Double>>{
		public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
			return o1.getValue().compareTo(o2.getValue());
		}
	}

	public static List<Integer> getPriorNElement(int N, Set<Map.Entry<Integer, Double>> set){
		List<Integer> similarList = new ArrayList<Integer>(N);

		int cnt = 0;
		for(Map.Entry<Integer, Double> entry : set){
			similarList.add(entry.getKey());
			cnt ++;
			if(cnt >= N){
				break;
			}
		}

		return similarList;
	}
}
