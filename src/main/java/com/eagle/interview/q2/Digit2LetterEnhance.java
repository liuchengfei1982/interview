/**
 * Copy Right here
 */
package com.eagle.interview.q2;

import com.eagle.interview.q1.Digit2Letter;
import com.eagle.interview.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stage 2 - new requirements
 * The program base on {@link Digit2Letter}
 * and need to support converting the digits from 0 to 99 into letters
 * and will ignore itself combinations case
 * eg {23,45}, should has such combinations
 * (24),(25),(34),(35)
 * for combination (23) will implemented later as not have enough testing time
 * so the final combination should be
 * ag,ah,ai,
 * aj,ah,al,
 * bg,bh,bi,
 * bj,bh,bl,
 * cg,ch,ci,
 * cj,ch,cl,
 * dg,dh,di,
 * dj,dh,dl,
 * eg,eh,ei,
 * ej,eh,el,
 * fg,fh,fi,
 * fj,fh,fl
 *
 * @author eagle liu
 * @date 2020-02-11
 */
public class Digit2LetterEnhance {
	// init mapping
	static Map<Integer,List<String>> digitMap = MapUtils.initMap();

	public String convert(List<Integer> digitList){
		if(digitList.isEmpty()) return "";
		List<Integer> copyDigitList = new ArrayList<>();
		digitList.forEach(s ->copyDigitList.add(s));

		StringBuffer sb  = new StringBuffer();
		Integer firstItem = copyDigitList.remove(0);
		int item1 = firstItem / 10;
		int item2 = firstItem % 10;

		this.printList(item1,copyDigitList,sb);
		this.printList(item2,copyDigitList,sb);

		return sb.toString();
	}
	private void printList(int letter, List<Integer> digitList, StringBuffer sb){
		List<String> letterList = digitMap.get(letter);
		// for case {1,2} will print empty
		if(letterList.isEmpty()) return ;
		letterList.forEach( s -> {
			// for case {22}
			if(digitList.isEmpty()){
				sb.append(s.toLowerCase()).append(",");
			}else{
				this.printList(s, digitList,sb);
			}
		});
	}

	private void printList(String letter, List<Integer> digitList, StringBuffer sb){
		boolean allSubDigitIsEmpty = true;
		for (Integer subDigit : digitList) {
			int item1 = subDigit / 10;
			int item2 = subDigit % 10;
			List<String> subDigitList1 = digitMap.get(item1);
			List<String> subDigitList2 = digitMap.get(item2);
			List<String> list1 = this.printSubList(letter, subDigitList1, sb);
			list1.forEach(s -> sb.append(s).append(","));
			List<String> list2 = this.printSubList(letter, subDigitList2, sb);
			list2.forEach(s -> sb.append(s).append(","));
			if(!list1.isEmpty() || !list2.isEmpty()){
				allSubDigitIsEmpty = false;
			}
		}
		if(allSubDigitIsEmpty){
			sb.append(letter.toLowerCase()).append(",");
		}

	}

	private List<String>  printSubList(String letter, List<String> digitList, StringBuffer sb){
		List<String> resultList = digitList.stream()
				.map(s -> letter + s)
				.map(String::toLowerCase)
				.collect(Collectors.toList());
		return resultList;
	}
}
