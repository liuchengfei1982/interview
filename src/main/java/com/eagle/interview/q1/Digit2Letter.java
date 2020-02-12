/**
 * Copy Right here
 */
package com.eagle.interview.q1;

import com.eagle.interview.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an integer array containing digits from [0, 9],
 * Digit2Letter.java help to print all possible letter combinations that the numbers could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is being followed.
 * Note that 0 and 1 do not map to any letters
 *
 * @author eagle liu
 * @date 2020-02-11
 */
public class Digit2Letter {
	// init mapping
	static Map<Integer,List<String>> digitMap = MapUtils.initMap();

	/**
	 * Main logic here, convert the digit to string
	 *
	 * @param digitList the input param
	 * @return result
	 */
	public String convert(List<Integer> digitList){
		if(digitList.isEmpty()) return "";
		List<Integer> copyDigitList = new ArrayList<>();
		digitList.forEach(s ->copyDigitList.add(s));
		StringBuffer sb  = new StringBuffer();

		Integer firstItem = copyDigitList.remove(0);
		List<String> firstItemList = digitMap.get(firstItem);
		if(firstItemList.isEmpty()) return "";

		firstItemList.forEach( s -> {
			// array size is 1
			if(copyDigitList.isEmpty()){
				sb.append(s.toLowerCase()).append(",");
			}else{
				this.printList(s, copyDigitList,sb);
			}
		});

		return sb.toString();
	}

	/**
	 * Iterate the sub digit list and collect the print result
	 *
	 * @param letter: the first letter to print
	 * @param digitList: sub digit list
	 * @param sb:  the print result
	 */
	private void printList(String letter, List<Integer> digitList, StringBuffer sb){
		boolean allSubDigitIsEmpty = true;
		for (Integer subDigit : digitList) {
			List<String> subDigitList = digitMap.get(subDigit);
			if(subDigitList.isEmpty()) continue;
			allSubDigitIsEmpty = false;
			List<String> subDigitStrList = this.printList(letter, digitMap.get(subDigit));
			subDigitStrList.forEach(s -> sb.append(s).append(","));
		}
		//handle {2,1,1} case
		if(allSubDigitIsEmpty){
			sb.append(letter.toLowerCase()).append(",");
		}
	}

	/**
	 * Return the  letter list to print
	 *
	 * @param letter: the first letter to print
	 * @param digitList: sub digit list
	 * @return the result to print
	 */
	private List<String> printList(String letter, List<String> digitList){
		List<String> resultList = digitList.stream()
				.map(s -> letter + s)
				.map(String::toLowerCase)
				.collect(Collectors.toList());
		return resultList;
	}
}
