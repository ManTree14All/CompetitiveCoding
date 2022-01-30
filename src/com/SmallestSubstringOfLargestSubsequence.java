package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmallestSubstringOfLargestSubsequence {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {
			// ASCII A-Z: 65-90
			// ASCII a-z: 97-122
			final String reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String input = s.next();

			int n = process(reference, input);
			System.out.println(n);
		}
	}

	private static int process(String reference, String input) {
		if (input.contains(reference)) {
			return 26;
		} else if (reference.contains(input) /* || input.length() < 26 */) {
			return input.length();
		} else {
			// ASCII A-Z: 65-90
			char[] inputArray = input.toCharArray();
			List<StringBuilder> subsequenceList = new ArrayList<>();
			List<Integer> subsequenceIndexTracker = new ArrayList<>();
			subsequenceList.add(new StringBuilder().append(inputArray[0]));
			subsequenceIndexTracker.add((int) input.charAt(0));

			// Index of the Input String Character
			for (int i = 1; i < input.length(); i++) {
				boolean partOfExistingSubsequence = false;
				int count = 0;
				// Updates the index of the existing subsequence and adds the value.
				for (Integer n : subsequenceIndexTracker) {
					if (n + 1 == (int) inputArray[i]) {
						partOfExistingSubsequence = true;
						subsequenceIndexTracker.set(count, (int) inputArray[i]);
						subsequenceList.get(count).append(inputArray[i]);
					} else if (n < 90) { // If subsequence index end reached
						subsequenceList.get(count).append(inputArray[i]);
					}
					count++;
				}
				if (!partOfExistingSubsequence) {
					subsequenceList.add(new StringBuilder().append(inputArray[i]));
					subsequenceIndexTracker.add((int) inputArray[i]);
				}
			}
			
			for(int j =0; j< subsequenceIndexTracker.size(); j++) {
				subsequenceList.get(j).delete(subsequenceList.get(j).toString().indexOf((char)subsequenceIndexTracker.get(j).intValue())+1, subsequenceList.get(j).toString().length());
			}
			int subStringLength = input.length();
			int offset = 0;
			for (StringBuilder s : subsequenceList) {
				int currentOffset = (int) s.toString().charAt(s.toString().length() - 1)- (int) s.toString().charAt(0);
				int currentSubStringLength = s.toString().length();
				if (currentOffset >= offset && currentSubStringLength < subStringLength) {
					subStringLength = currentSubStringLength;
					offset = currentOffset;
				}
			}

			return subStringLength;
		}
	}

}
