package org.xx.recommender;

public class Utility {
	public static double computeVectorLength(int[] vector){
		long length = 0;

		for(int item : vector){
			length += item * item;
		}

		return Math.sqrt((double)length);
	}

	public static double shru(double d){
		return ((double)((int)((d + 0.005)*100)))/100;
	}

	public static double computeSimilarity(int[] vector1, int[] vector2) {
		float molecular = 0.0f;
		for(int i = 0; i < vector1.length; i ++){
			molecular += vector1[i] * vector2[i];
		}

		double u1Length = computeVectorLength(vector1);
		double u2Length = computeVectorLength(vector2);

		double similarity = molecular / (u1Length * u2Length);

		return shru(similarity);
	}
}
