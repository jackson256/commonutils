/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.intsort
 * @author: hudson
 * @date:2018年6月7日 下午7:36:52
 */
package com.hudson.intsort;

import java.util.Arrays;

/**
 * @className: RadixSort
 * @Description:基数排序
 * @author: hudson
 * @date: 2018年6月7日 下午7:36:52
 * @version: 1.0
 */
public class RadixSort {

	public static void main(String[] args) {
		int num[] = { 1, 54, 6, 8, 9, 52, 6, 98, 45, 559, 6584, 596324 };
		radixSort(num);
		System.out.println(Arrays.toString(num));
	}

	/**
	 * @Title sort
	 * @Description 采用最低位优先法（LSD）(Least Significant Digit first)
	 *              最高位优先法（MSD）(Most Significant Digit first)
	 * @author hudson
	 * @lastmodified 2018年6月7日下午7:37:38
	 * @modifiedBy
	 */
	public static void radixSort(int[] num) { // [1, 54, 6, 8, 9, 52, 6, 98, 45]
		if (isEmpty(num))
			return;
		int tmp = getMax(num);
		// get loop count
		int loop = 0;
		do {
			loop++;
		} while ((tmp = tmp / 10) > 0);
		int count = 1, k, lsd;
		int[][] bubble = new int[10][num.length];
		int[] order = new int[10];
		tmp = 1;
		while (count <= loop) {
			for (int i = 0; i < num.length; i++) {
				lsd = (num[i] / tmp) % 10;
				bubble[lsd][order[lsd]] = num[i];
				order[lsd]++;
			}
			k = 0;
			for (int i = 0; i < 10; i++) {
				if (order[i] != 0) {
					for (int j = 0; j < order[i]; j++) {
						num[k] = bubble[i][j];
						k++;
					}
				}
				order[i] = 0;
			}
			tmp *= 10;
			count++;
		}
	}

	private static int getMax(int[] num) {
		if (isEmpty(num)) {
			throw new IllegalArgumentException();
		}
		int max = num[0];
		for (int i = 1; i < num.length; i++) {
			if (max < num[i])
				max = num[i];
		}
		return max;
	}

	private static boolean isEmpty(int[] num) {
		return num == null || num.length == 0;
	}

}
