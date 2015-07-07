package com.baldrichcorp.toolbox.algorithms;

import java.util.List;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class KMPTest {

	@Test
	public void match() {
		String pattern = "ABCDABD";
		String text = "ABC ABCDAB ABCDABCDABDE";
		KMP kmp = new KMP(pattern);
		List<Integer> matches = kmp.match(text);
		assertEquals(15,matches.get(0).intValue());
	}
}
