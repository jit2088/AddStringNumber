import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddStringNumber {

	public AddStringNumber() {
		// constructor
	}

	// Help function
	public String createSingleDelimiterPattern(String[] delimiter) {
		String pattern = "(";
		// replace '*' sign to avoid pattern match issue
		for (int i = 0; i < delimiter.length; i++) {

			if (delimiter[i].equals("*") || delimiter[i].equals("$")) {
				pattern = pattern + "\\" + delimiter[i];
			} else {

				pattern = pattern + delimiter[i];
			}
		}
		pattern = pattern + ")";

		return pattern;
	}

	public String createMultipleDelimitersPattern(String[] arr_delimiter) {
		String delimiters = "";
		for (int i = 0; i < arr_delimiter.length - 1; i++) {
			if (arr_delimiter[i].equals("*") || arr_delimiter[i].equals("$")) {
				delimiters = delimiters + "\\" + arr_delimiter[i] + "|";
			} else {
				delimiters = delimiters + arr_delimiter[i] + "|";
			}
		}

		if (arr_delimiter[arr_delimiter.length - 1].equals("*")
				|| arr_delimiter[arr_delimiter.length - 1].equals("$")) {
			delimiters = delimiters + "\\" + arr_delimiter[arr_delimiter.length - 1];
		} else {
			delimiters = delimiters + arr_delimiter[arr_delimiter.length - 1];
		}
		return delimiters;
	}

	public int addUp(String[] arr_digits) throws NegativeNumberException {

		int total = 0;
		ArrayList<Integer> negatives = new ArrayList<Integer>();
		ArrayList<Integer> valid = new ArrayList<Integer>();
		for (int i = 0; i < arr_digits.length; i++) {

			int a = Integer.parseInt(arr_digits[i]);

			if (a > 0 && a <= 1000) {
				total = total + a;
				valid.add(a);
			}
			if (a < 0) {
				negatives.add(a);
			}

		}
		if (negatives.size() > 0) {
			throw new NegativeNumberException("Negative numbers are not allowed : " + negatives + "\n");
		}

		return total;
	}

	// **************************************************************************************************************
	// 1. Simple String calculator
	public int add(String numbers) {

		if (numbers.length() == 0) {
			return 0;
		}

		String[] arr_digits = numbers.replaceAll("\\s", "").split(",");

		int total = 0;
		for (int i = 0; i < arr_digits.length; i++) {

			total = total + Integer.parseInt(arr_digits[i]);
		}
		return total;
	}

	// 2. Change the add() method to Handle new lines in the input format
	public int addHandleNewLines(String numbers) {

		if (numbers.length() == 0) {
			return 0;
		}
		// To find \\n in a string, we need a regular expression that includes
		// two backslash characters before the n
		String myStr = numbers.replaceAll("\\s", "").replaceAll("\\\\n", "");
		String[] arr_digits = myStr.split(",");

		int total = 0;
		for (int i = 0; i < arr_digits.length; i++) {

			total = total + Integer.parseInt(arr_digits[i]);
		}
		return total;
	}

	// 3. Change the add() method to support a customized delimiter
	public int addWithCustomizedDelimiter(String numbers) {
		if (numbers.length() == 0) {
			return 0;
		}

		String myStr = numbers.replaceAll("\\s", "").replaceAll("\\\\n", "");
		String delimiter = myStr.substring(2, 3);
		if(delimiter.equals("*") || delimiter.equals("$")){
			delimiter = "\\" + delimiter;
		}
		String[] arr_digits = myStr.substring(3).split(delimiter);
		int total = 0;
		for (int i = 0; i < arr_digits.length; i++) {

			total = total + Integer.parseInt(arr_digits[i]);
		}
		return total;
	}

	/*
	 * 4. Calling add with a negative number should throw an exception:
	 * “Negatives not allowed”. The exception should list the number(s) that
	 * caused the exception
	 */

	public int addWithNegativeNumber(String numbers) throws NegativeNumberException {

		if (numbers.length() == 0) {
			return 0;
		}

		String delimiter = numbers.replaceAll("\\s", "").substring(2, 3);
		String[] arr_digits = numbers.replaceAll("\\s", "").substring(3).replaceAll("\\\\n", "").split(delimiter);

		int total = addUp(arr_digits);

		return total;
	}

	// Bones 1
	// 5. Numbers larger than 1000 should be ignored;

	public int addNoLargerThan1000(String numbers) throws NegativeNumberException {

		if (numbers.length() == 0) {
			return 0;
		}
		String myStr = numbers.replaceAll("\\s", "").replaceAll("\\\\n", "");
		String delimiter = myStr.substring(2, 3);
		String[] arr_digits = myStr.substring(3).replaceAll("\\\\n", "").split(delimiter);
		int total = addUp(arr_digits);

		return total;
	}

	// Bones 2
	// 6. Delimiters can be arbitrary length
	public int arbitraryLengthOfDelimiter(String numbers) throws NegativeNumberException {

		if (numbers.length() == 0) {
			return 0;
		}
		String myStrs[] = numbers.split("\\r?\\n", -1);
		String delimiter[] = myStrs[0].substring(2).split("");

		String pattern = createSingleDelimiterPattern(delimiter);

		String[] arr_digits = myStrs[1].split(pattern);
		int total = addUp(arr_digits);

		return total;

	}

	// Bones 3
	// 7. Allow for mulitiple delimiters
	public int allowForMultipleDelimiters(String numbers) throws NegativeNumberException {

		if (numbers.length() == 0) {
			return 0;
		}
		String myStrs[] = numbers.split("\\r?\\n", -1);
		String[] arr_delimiter = myStrs[0].substring(2).split(",");

		String delimiters = createMultipleDelimitersPattern(arr_delimiter);

		// Get digits from string
		String[] arr_digits = myStrs[1].split(delimiters);

		int total = 0;
		ArrayList<Integer> negatives = new ArrayList<Integer>();
		for (int i = 0; i < arr_digits.length; i++) {

			int a = Integer.parseInt(arr_digits[i]);
			if (a > 0 && a < 1000) {
				total = total + a;
			} else {
				negatives.add(a);
			}
		}
		if (negatives.size() > 0) {
			throw new NegativeNumberException("Negative numbers are not allowed : " + negatives + "\n");
		}
		return total;
	}

	// Bones 4
	// 8. Combine 2 and 3 bonus questions. Allow multiple delimiters of
	// arbitrary length

	public int allowMutipleDelimitersOfArbitraryLength(String numbers) throws NegativeNumberException {

		if (numbers.length() == 0) {
			return 0;
		}

		String myStrs[] = numbers.split("\\r?\\n", -1);

		String[] arr_delimiters = myStrs[0].substring(2).split(",");

		String pattern = "";
		for (int i = 0; i < arr_delimiters.length; i++) {

			if (i < arr_delimiters.length - 1) {
				pattern = pattern + createSingleDelimiterPattern(arr_delimiters[i].split("")) + "|";
			}
			if (i == arr_delimiters.length - 1) {
				pattern = pattern + createSingleDelimiterPattern(arr_delimiters[i].split(""));
			}
		}
		
		// Get the start_index for subString of digits part after new line
		String[] arr_digits = myStrs[1].split(pattern);

		int total = 0;
		ArrayList<Integer> negatives = new ArrayList<Integer>();
		for (int i = 0; i < arr_digits.length; i++) {

			int a = Integer.parseInt(arr_digits[i]);
			if (a > 0 && a < 1000) {
				total = total + a;
			} else {
				negatives.add(a);
			}
		}
		if (negatives.size() > 0) {
			throw new NegativeNumberException("Negative numbers are not allowed : " + negatives + "\n");
		}
		return total;
	}

	//
	public static void main(String args[]) {

		AddStringNumber asn = new AddStringNumber();

		// 1. Simple String calculator
		String numbers1 = "1,2,3";
		System.out.println("For " + numbers1 + " expected 6 and got " + asn.add(numbers1));

		// 2. Change the add() method to Handle new lines in the input format
		String numbers2 = "\n1,2,3";
		System.out.println("For " + numbers2 + " expected 6 and got  " + asn.addHandleNewLines(numbers2));

		// 3. Change the add() method to support a customized delimiter
		String numbers3 = "//;\n1;2;3";
		
		System.out.println("For " + numbers3 + " expected 6 and got " + asn.addWithCustomizedDelimiter(numbers3));
		/*
		 * 4. Calling add with a negative number should throw an exception:
		 * “Negatives not allowed”. The exception should list the number(s) that
		 * caused the exception
		 */
		String numbers4 = "//@\n2@3@8@-9@-10";
		try {
			System.out.println("For " + numbers4 + " expected 13 and got " + asn.addWithNegativeNumber(numbers4));
		} catch (NegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Bones 1
		// 5. Numbers larger than 1000 should be ignored;
		String numbers5 = "//@\n2@3@8@-9@1001";
		try {
			System.out.println("For " + numbers5 + " expected 13 and got " + asn.addNoLargerThan1000(numbers5));
		} catch (NegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Bones 2
		// 6. Delimiters can be arbitrary length
		String numbers6 = "//***\n1***2***3***1001";
		try {
			System.out.println("For " + numbers6 + " expected 6 and got " + asn.arbitraryLengthOfDelimiter(numbers6));
		} catch (NegativeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 // Bones 3
		 // 7. Allow for mulitiple delimiters
		 String numbers7 = "//$,@,#\n1$2@3@4#5";
		 try {
		 System.out.println("For " + numbers7 + " expected 15 and got " + asn.allowForMultipleDelimiters(numbers7));
		 } catch (NegativeNumberException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		
		 // Bones 4
		 // 8. Combine 2 and 3 bonus questions. Allow multiple delimiters of
		 // arbitrary length
		 String numbers8 = "//$@$,@@@,###\n1$@$2@@@3###5@@@1";
		 try {
		System.out.println("For " + numbers8 + " expected 12 and got " +  asn.allowMutipleDelimitersOfArbitraryLength(numbers8));
		 } catch (NegativeNumberException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	}

}
