/**
 * Copy Right here
 */
package com.eagle.interview.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map utils
 *
 * @author eagle liu
 * @date 2020-02-11
 */
public class MapUtils {
	public static Map<Integer,List<String>> initMap(){
		Map<Integer,List<String>> digitMap = new ConcurrentHashMap<>();
		digitMap.put(0, Arrays.asList());
		digitMap.put(1, Arrays.asList());
		digitMap.put(2, Arrays.asList("A","B","C"));
		digitMap.put(3, Arrays.asList("D","E","F"));
		digitMap.put(4, Arrays.asList("G","H","I"));
		digitMap.put(5, Arrays.asList("J","H","L"));
		digitMap.put(6, Arrays.asList("M","N","O"));
		digitMap.put(7, Arrays.asList("P","Q","R","S"));
		digitMap.put(8, Arrays.asList("T","U","V"));
		digitMap.put(9, Arrays.asList("W","X","Y","Z"));
		return digitMap;
	}
}
