package com.eagle.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
	public static int[] twoSum(int[] nums, int target) {
		long start = System.currentTimeMillis();
		int result = 0;
		int[] idx = new int[2];
		boolean done = false;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				result = nums[i]+nums[j];
				if(i !=j && result == target){
					idx[0] = i;
					idx[1] = j;
					done = true;
				}
			}
			if(done) break;
		}
		long end = System.currentTimeMillis();
		System.out.println("twoSum cost "+(end-start)+"ms");
		return idx;
	}

	public static int[] twoSum2(int[] nums, int target) {
		long start = System.currentTimeMillis();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}

		for (int i = 0; i < nums.length; i++) {
			int val = target - nums[i];
			Integer index = map.get(val);
			if (index != null && index != i) {
				long end = System.currentTimeMillis();
				System.out.println("twoSum2 cost "+(end-start)+"ms");
				return new int[]{i, index};
			}
		}

		return null;
	}

	public static int[] twoSum3(int[] nums, int target) {
		long start = System.currentTimeMillis();
		int[] indexs = new int[2];

		// 建立k-v ，一一对应的哈希表
		HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
		for(int i = 0; i < nums.length; i++){
			if(hash.containsKey(nums[i])){
				indexs[0] = i;
				indexs[1] = hash.get(nums[i]);
				long end = System.currentTimeMillis();
				System.out.println("twoSum3 cost "+(end-start)+"ms");
				return indexs;
			}
			// 将数据存入 key为补数 ，value为下标
			hash.put(target-nums[i],i);
		}

		return indexs;
	}


	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 9;

		twoSum(nums, target);
		twoSum2(nums, target);
		twoSum3(nums, target);
		System.out.println();

	}
}
