/**
 * Copy Right here
 */
package com.eagle.interview.q1;

import com.eagle.interview.q2.Digit2LetterEnhance;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Test case for {@link Digit2Letter} and {@link Digit2LetterEnhance}
 *
 * @author eagle liu
 * @date 2020-02-12
 */

@Slf4j
public class Digit2LetterTest {

	@Test
	public void convert1() {
		List<Integer> digitList = new ArrayList<>();
		digitList.add(2);
		digitList.add(3);
		digitList.add(1); // will not effect the print
		digitList.add(0); // will not effect the print
		Digit2Letter digit2Letter = new Digit2Letter();
		String result = digit2Letter.convert(digitList);
		log.info(result);
		if(!StringUtils.isEmpty(result)){
			result = result.substring(0,result.length()-1);
			Assert.assertTrue("Test Fail", result.equals("ad,ae,af,bd,be,bf,cd,ce,cf"));
		}else{
			Assert.assertTrue("Test Fail", result.equals("ad,ae,af,bd,be,bf,cd,ce,cf"));
		}

	}

	@Test
	public void convert2() {
		List<Integer> digitList = new ArrayList<>();
		digitList.add(23);
		digitList.add(45);
//		digitList.add(78); //should try more here

		Digit2LetterEnhance digit2LetterEnhance = new Digit2LetterEnhance();
		String result = digit2LetterEnhance.convert(digitList);
		log.info(result);
		if(!StringUtils.isEmpty(result)){
			result = result.substring(0,result.length()-1);
			Assert.assertTrue("Test Fail", result.equals("ag,ah,ai,aj,ah,al,bg,bh,bi,bj,bh,bl,cg,ch,ci,cj,ch,cl,dg,dh,di,dj,dh,dl,eg,eh,ei,ej,eh,el,fg,fh,fi,fj,fh,fl"));
		}else{
			Assert.assertTrue("Test Fail", result.equals("ag,ah,ai,aj,ah,al,bg,bh,bi,bj,bh,bl,cg,ch,ci,cj,ch,cl,dg,dh,di,dj,dh,dl,eg,eh,ei,ej,eh,el,fg,fh,fi,fj,fh,fl"));
		}


	}
}