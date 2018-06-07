/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.sortmethods
 * @author: hudson
 * @date:2018年6月7日 下午2:43:50
 */
package com.hudson.intsort;

import java.util.Arrays;

/**
 * @className: ShellSort
 * @Description:
 * @author: hudson
 * @date: 2018年6月7日 下午2:43:50
 * @version: 1.0
 */
public class ShellSort {

	public static void main(String[] args) {
		int num[] = { 1, 54, 6, 8, 9, 52, 6, 98, 45 };
		// Arrays.sort(num);
		// System.out.println(Arrays.toString(num));
		// insertSort2(num);
		// shellSort(num);
		// selectSort(num);
		// heapSort(num);
		// bubbleSort(num);
		// quickSort(num);
		System.out.println(Arrays.toString(num));
	}
	
	

	/**
	 * 选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
	 * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分
	 * 
	 * @Title quickSort
	 * @Description 快速排序
	 * @author hudson
	 * @lastmodified 2018年6月7日下午5:35:22
	 * @modifiedBy
	 */
	private static void quickSort(int[] num) {
		if (num.length > 0) {
			_quickSort(num, 0, num.length - 1);
		}
	}

	/**
	 * @Title _quickSort
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月7日下午5:38:04
	 * @modifiedBy
	 */
	private static void _quickSort(int[] num, int low, int high) {//[1, 54, 6, 8, 9, 52, 6, 98, 45]
		if (low < high) {
			int middle = getMiddle(num, low, high);
			_quickSort(num, low, middle-1);
			_quickSort(num, middle+1, high);
		}

	}

	/**
	 * @Title getMiddle
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月7日下午5:38:52
	 * @modifiedBy
	 */
	private static int getMiddle(int[] num, int low, int high) {
		int tmp = num[low];// 默认第一个为中轴
		while (low < high) {
			while (low < high && num[high] >= tmp) {
				high--;
			}
			num[low] = num[high];//比中轴小的记到低端
			while (low < high && num[low] <= tmp) {
				low++;
			}
			num[high] = num[low];//比中轴大的记到高端
		}
		num[low] = tmp;
		return low;//中轴位置
	}

	/**
	 * 在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒
	 * 
	 * @Title bubbleSort
	 * @Description 冒泡排序
	 * @author hudson
	 * @lastmodified 2018年6月7日下午5:16:32
	 * @modifiedBy
	 */
	private static void bubbleSort(int[] num) {
		int temp = 0;
		for (int i = 0, len = num.length; i < len - 1; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (num[j] > num[j + 1]) {
					temp = num[j + 1];
					num[j + 1] = num[j];
					num[j] = temp;
				}
			}
		}
		System.out.println("bubbleSort:" + Arrays.toString(num));
	}

	/**
	 * @Title heapSort
	 * @Description 堆排序
	 * @author hudson
	 * @lastmodified 2018年6月7日下午4:41:35
	 * @modifiedBy
	 */
	private static void heapSort(int[] num) {
		int len = num.length;
		// 循环建堆
		for (int i = 0; i < len; i++) {
			// 建堆
			buildMaxHeap(num, len - i - 1);
			// 交换堆顶和最后一个元素
			swap(num, 0, len - 1 - i);
			System.out.println("heapSort:" + Arrays.toString(num));
		}

	}

	/**
	 * @Title buildMaxHeap
	 * @Description 堆排序，建堆方法
	 * @author hudson
	 * @lastmodified 2018年6月7日下午4:44:45
	 * @modifiedBy
	 */
	private static void buildMaxHeap(int[] num, int lastIndex) {
		// 从lastindex处节点(最后一个节点)的父节点开始
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k保存正在判断的节点
			int k = i;
			// 如果当前k节点的子节点存在
			while (k * 2 + 1 <= lastIndex) {
				// k节点的左子节点的索引
				int biggerIndex = 2 * k + 1;
				// 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右节点存在
				if (biggerIndex < lastIndex) {
					// 如果右子节点的值较大
					if (num[biggerIndex] < num[biggerIndex + 1]) {
						// 记录较大子节点的索引
						biggerIndex++;
					}
				}

				if (num[k] < num[biggerIndex]) {
					swap(num, k, biggerIndex);
					k = biggerIndex;
				} else {
					break;
				}
			}
		}

	}

	/**
	 * @Title swap
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月7日下午4:53:23
	 * @modifiedBy
	 */
	private static void swap(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}

	/**
	 * 要排序的一组数中，选出最小的一个数与第一个位置的数交换；
	 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
	 * 
	 * @Title selectSort
	 * @Description 选择排序
	 * @author hudson
	 * @lastmodified 2018年6月7日下午3:55:10
	 * @modifiedBy
	 */
	private static void selectSort(int[] num) {
		int len = num.length;
		int position = 0;
		for (int i = 0; i < len; i++) {
			int j = i + 1;// 从j往后找最小值
			position = i;// 最小值的下标
			int temp = num[i];// 最小值
			for (; j < len; j++) {
				if (num[j] < temp) {
					temp = num[j];
					position = j;
				}
			}
			num[position] = num[i];
			num[i] = temp;
		}
		System.out.println(Arrays.toString(num));
	}

	/**
	 * 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.
	 * 对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。
	 * 当增量减到1时，进行直接插入排序后，排序完成。
	 * 
	 * @Title shellSort
	 * @Description 希尔排序（最小增量排序）
	 * @author hudson
	 * @lastmodified 2018年6月7日下午3:51:03
	 * @modifiedBy
	 */
	public static void shellSort(int[] num) {// 1, 54, 6, 8, 9, 52, 6, 98, 45
		double dl = num.length;
		int temp = 0;
		while (true) {
			dl = Math.ceil(dl / 2);// 下标间隔，每次减半
			int d = (int) dl;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < num.length; i += d) {
					int j = i - d;
					temp = num[i];
					for (; j >= 0 && temp < num[j]; j -= d) {
						num[j + d] = num[j];
					}
					num[j + d] = temp;
				}
			}
			if (d == 1)
				break;
		}
		System.out.println("shellSort:" + Arrays.toString(num));
	}

	/**
	 * 在要排序的一组数中，假设前面(n-1)[n>=2]
	 * 个数已经是排好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序
	 * 
	 * @Title insertSort
	 * @Description 插入排序
	 * @author hudson
	 * @lastmodified 2018年6月7日下午3:17:55
	 * @modifiedBy
	 */
	public static void insertSort(int[] number) {
		int n = number.length;
		int temp, i, j;
		for (i = 1; i < n; i++) {
			temp = number[i];
			for (j = i; j > 0 && number[j - 1] > temp; j--) {
				number[j] = number[j - 1];
			}
			number[j] = temp;
		}
		System.out.println("insertSort:" + Arrays.toString(number));
	}

	public static void insertSort2(int[] num) {
		for (int i = 0, j = i; i < num.length - 1; j = ++i) {
			int ai = num[i + 1];// 插入的值
			while (ai < num[j]) {
				num[j + 1] = num[j];
				if (j-- == 0) {
					break;
				}
			}
			num[j + 1] = ai;
		}
		System.out.println("insertSort2:" + Arrays.toString(num));
	}

}
