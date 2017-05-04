package org.xx.recommender.itemCFSimpleExample;

import java.util.HashMap;
import java.util.Map;

import org.xx.recommender.Book;

public class Test {
	/*
	 * ratingMatrix[i][j]: userI's rate on BookJ
	 * rate value:0-5
	 * 0: userI don't rate on BookJ
	 * */
	public static int[][] ratingMatrix = {
			{4, 3, 0, 0, 5, 0},
			{5, 0, 4, 0, 4, 0},
			{4, 0, 5, 3, 4, 0},
			{0, 3, 0, 0, 0, 5},
			{0, 4, 0, 0, 0, 4},
			{0, 0, 2, 4, 0, 5}
	};

	public static final int userCnt = ratingMatrix.length;
	public static final int bookCnt = ratingMatrix[0].length;

	public static Map<Integer, Book> bookMap = new HashMap<Integer, Book>();

	static{
		bookMap.put(0, new Book(0, "Introduction to Recommender Systems"));
		bookMap.put(1, new Book(1, "Machine learning Paradigms"));
		bookMap.put(2, new Book(2, "Social Network-based Recommender Systems"));
		bookMap.put(3, new Book(3, "Learning Spark"));
		bookMap.put(4, new Book(4, "Recommender Systems Handbook"));
		bookMap.put(5, new Book(5, "Recommender Systems and the Social Web"));
	}

	public static void main(String[] args){
		//int bookId = ItemCF.recommend(0, 2);
		//System.out.println("Book Recommand for user0 is: <" + Test.bookMap.get(bookId) + ">");
	}
}
