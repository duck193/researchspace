package com.sentence;

import java.util.ArrayList;
import java.util.Map;

public class SentenceWordCount {
	
	
	private static SentenceWordCount sentenceWordCountObject;
	private static String sentence = "This is a new way in to count how many times one string, can have two#two or more three!three!three numbers written in four*four*four*four.";
	
	/**
	 * GetSentenceWordCount
	 **
	 * Return a SentenceWordCount object
	 * @return SentenceWordCount
	 */
	public static SentenceWordCount getSentenceWordCount() {
		if(sentenceWordCountObject==null) {
			sentenceWordCountObject = new SentenceWordCount();
			return sentenceWordCountObject;
		}
		else {
			return sentenceWordCountObject;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] mainObject(String[] args) {
		ArrayList al = new ArrayList<String>();
		CountWords cw;
		if(args.length>0) {
			cw = new CountWords(args);
		}
		else {
			cw = new CountWords(sentence);
		}
		
		Map<String, Integer> wordsOriginalOrder = cw.getWords();

		// Key Sort Order
		al.add("Alphanumeric Key Sort");
		Map<String, Integer> wordsKeySortedOrder = cw.mapSorter(wordsOriginalOrder, "Key");
		wordsKeySortedOrder.forEach((k,v) -> al.add(k + " : " + v));
		
		// Value Sort Order
		al.add("\nNumerical Value Sort");
		Map<String, Integer> wordsValueSortedOrder = cw.mapSorter(wordsOriginalOrder, "Value");
		wordsValueSortedOrder.forEach((k,v) -> al.add(k + " : " + v));
		
		return (String[])al.toArray();
	}
	
	public void doStaticCall(String[] parameter) {
		SentenceWordCount.main(parameter);
	}
	
	public static void main(String[] args) {
		SentenceWordCount swc = new SentenceWordCount();
		for (String arrayElement : swc.mainObject(args)) {
			System.out.println(arrayElement);
		}
	}
}
