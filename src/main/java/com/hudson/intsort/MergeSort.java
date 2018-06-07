/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.intsort
 * @author: hudson
 * @date:2018年6月7日 下午5:56:34
 */
package com.hudson.intsort;

import java.util.Arrays;

/**
 * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。
 * 然后再把有序子序列合并为整体有序序列。
 * 
 * @className: MergeSort
 * @Description:归并排序
 * @author: hudson
 * @date: 2018年6月7日 下午5:56:34
 * @version: 1.0
 */
public class MergeSort {

	public static void main(String[] args) {
		int num[] = { 1, 54, 6, 8, 9, 52, 6, 98, 45 };
		sort(num, 0, num.length - 1);
		System.out.println(Arrays.toString(num));
	}

	/**
	 * @Title sort
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月7日下午5:58:06
	 * @modifiedBy
	 */
	private static void sort(int[] num, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;// 中间索引
			sort(num, left, center);// 左边数组递归
			sort(num, center + 1, right);// 右边数组递归
			merge(num, left, center, right);// 合并
		}
	}

	/**
	 * @Title merge
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月7日下午6:00:21
	 * @modifiedBy
	 */
	private static void merge(int[] num, int left, int center, int right) {
		int[] tmpArr = new int[num.length];
		int mid = center + 1;
		int third = left;// 临时数组的索引
		int tmp = left;
		while (left <= center && mid <= right) {
			// 从两个数组中去除最小的放入临时数组
			if (num[left] <= num[mid]) {
				tmpArr[third++] = num[left++];
			} else {
				tmpArr[third++] = num[mid++];
			}
		}

		// 剩余部分一次放入临时数组
		while (mid <= right) {
			tmpArr[third++] = num[mid++];
		}
		while (left <= center) {
			tmpArr[third++] = num[left++];
		}

		// 临时数组的内容复制回原数组
		while (tmp <= right) {
			num[tmp] = tmpArr[tmp++];
		}

	}

}
