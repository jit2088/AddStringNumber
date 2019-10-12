import org.junit.Assert;
import org.junit.Test;

public class AddStringNumberTest {

	
	AddStringNumber asn = new AddStringNumber();
	
	@Test
	public void add() {
		String numbers = "1,2,3";
		Assert.assertEquals(6, asn.add(numbers));
	}

	@Test
	public void addHandleNewLines() {
		
		String numbers = "\n1,2,3";
		//System.out.println(numbers);
		Assert.assertEquals(6, asn.addHandleNewLines(numbers));
		
	}

	@Test
	public void addWithCustomizedDelimiter() {
		String numbers = "//;\n1;2;3";
		Assert.assertEquals(6, asn.addWithCustomizedDelimiter(numbers));
	}
//
	@Test
	public void addWithNegativeNumber() throws NegativeNumberException {
		String numbers = "//@\n2@3@8@-9@-10";
		try{
		Assert.assertEquals(13, asn.addWithNegativeNumber(numbers));}
		catch(NegativeNumberException e){
			
			System.err.print(e);
		}
		
	}

	@Test
	public void addNoLargerThan1000() throws NegativeNumberException {
		try{
		String numbers1 = "//@\n2@3@8@-9@1001";
		Assert.assertEquals(13, asn.addNoLargerThan1000(numbers1));
		}catch(NegativeNumberException e){
			System.err.print(e);
		}
		
	}

	@Test
	public void arbitraryLengthOfDelimiters() throws NegativeNumberException {
		try{
		String numbers = "//***\n1***2***3***1001";
		Assert.assertEquals(6, asn.arbitraryLengthOfDelimiters(numbers));
		}catch(NegativeNumberException e){
			System.err.print(e);
		}
		
	}

	@Test
	public void allowForMultipleDelimiters() throws NegativeNumberException {
		try{
		String numbers = "//$,@,#\n1$2@3@4#5";
		Assert.assertEquals(15, asn.allowForMultipleDelimiters(numbers));
		}catch(NegativeNumberException e){
			System.err.print(e);
		}
	}

	@Test
	public void allowMutipleDelimitersOfArbitraryLength() throws NegativeNumberException {
		try{
		String numbers = "//$@$,@@@,###\n1$@$2@@@3###5@@@1";
		Assert.assertEquals(12, asn.allowMutipleDelimitersOfArbitraryLength(numbers));
		}catch(NegativeNumberException e){
			System.err.print(e);
		}
	}

}
