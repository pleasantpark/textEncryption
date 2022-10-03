package hHW9;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class hHW9Main {

	public static void main(String[] args) {
		try {
			String text = shiftFile(35);//take fileText and shift by 35
			System.out.println( "Encrypted text: "+text);
			text = shiftText(text,1);
			System.out.println("Original text:"+text);
		}catch(FileNotFoundException e) {
			//if file isn't there, probably the case
			String text = "In new research, mathematicians have revealed a new category of “digitally delicate” prime numbers. These infinitely long primes turn back to composites faster than Cinderella at midnight with a change of any individual digit.\r\n" + 
					"\r\n" + 
					"You think math is badass. So do we. Let’s nerd out over numbers together.\r\n" + 
					"\r\n" + 
					"Digitally delicate primes have infinite digits, and changing any digit to any other value bears a composite number outcome instead. To use a more bite-size example, consider 101, which is a prime. Change the digits to 201, 102, or 111, and you have values that are divisible by 3 and therefore compound numbers.\r\n" + 
					"\r\n" + 
					"This idea is decades old, so what’s new? Now, mathematicians from the University of South Carolina have established an even more specific niche of the digitally delicate primes: widely digitally delicate primes. These are primes with added, infinite “leading zeros,” which don’t change the original prime, but make a difference as you change the 0s into other digits to test for delicacy.\r\n" + 
					"\r\n" + 
					"So instead of 101, consider 000101. That value is prime, and the zeros are just there for show, basically. But if you change the zeros, like 000101 to 100101, now you have a composite number that’s divisible by 3. The mathematicians believe there are infinite widely digitally delicate primes, but so far, they can’t come up with a single real example. They’ve tested all the primes up to 1,000,000,000 by adding leading zeros and doing the math.\r\n" + 
					"\r\n" + 
					"South Carolina math professor Michael Filaseta and former graduate student Jeremiah Southwick worked together on the widely digitally delicate number research, publishing their findings in Mathematics of Computation and arXiv. Even without specific examples, they proved the numbers exist in base 10 (meaning numbers that use our 0-9 counting system; compare with binary, base 2, with just 0 and 1) and that there are infinitely many.\r\n" + 
					"\r\n" + 
					"The proof itself relies on a kind of logic that’s like the simple division rules on steroids. Certain families of numbers, like those that contain 9s or whose sum adds up to a certain amount, can be blanket proven and then assigned to separate “buckets.” The more buckets there are, the more of the whole gigantic set of integer values is “covered” by the proof.\r\n" + 
					"\r\n" + 
					"“The situation involving widely digitally delicate primes is more complicated, of course,” Quanta’s Steve Nadis reports. “You’ll need a lot more buckets, something on the order of 1,025,000, and in one of those buckets every prime number is guaranteed to become composite if any of its digits, including its leading zeros, is increased.”\r\n" + 
					"\r\n" + 
					"This isn’t the kind of mathematics that extends to a practical application—it’s number theory that mostly works for its own sake as a way to explore the limits of mathematics. Even since Filaseta and Southwick published their proofs, there are more special cases of digitally delicate numbers in the works as other mathematicians use their research as a jumping off point.\r\n" + 
					"\r\n" + 
					"What if you took 101 and inserted a 1 to get 1011? What if you took one digit away to get 10? The possibilities are digitally unlimited.";
			text = shiftText(text,35);
			System.out.println(text);
			text = shiftText(text,1);
			System.out.println(text);
		}

	}

	//shifts a string by s, returns long string of characters
	public static String shiftText(String toEncrypt, int s) {
		return decode(encode(toEncrypt,s));
	}
	
	//shifts a file, word by word and returns a long string of characters
	public static String shiftFile(int s) throws FileNotFoundException {
		File file = new File("textToEncrypt");
		Scanner sc = new Scanner(file);
		StringBuffer sb = new StringBuffer();
		while(sc.hasNext()) {//shift each word in text and add to bigger string
			String word = sc.next();
			word = shiftText(word,s);
			sb.append(word);
		}
		String result = sb.toString();
		sc.close();
		//return large string of shifted by s characters
		return result;
	}
	
	//intermediate step, returns arrayList of numbers which correspond to characters in given string
	public static ArrayList<Integer> encode(String word) {
		word = word.replaceAll("[^\\w]", "");//gets rid of punctuation
		word = word.strip();
		word = word.toLowerCase();

		char[] lowercases = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
				'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
		ArrayList<Integer> toReturn = new ArrayList<>();

		//iterates through each char in the given string
		for(char c: word.toCharArray()) {
			//replace all letters
			for(int i = 0; i < lowercases.length; i++) {
				if(lowercases[i] == (c)) {
					toReturn.add(i+1);
					break;
				}
			}
			//replace all digits
			for(int d = 0; d < digits.length; d++) {
				if(digits[d] == c) {
					toReturn.add((d+27)%36);
					break;
				}
			}
		}
		return toReturn;
	}
	
	//intermediate step in shifting text, returns arrayList of numbers corresponding to characters shifted by s
	public static ArrayList<Integer> encode(String word, int s) {
		ArrayList<Integer> toShift = encode(word);
		for(int i = 0; i < toShift.size(); i++) {
			toShift.set(i,(toShift.get(i)+s)%36);
		}
		return toShift;
	}
	
	//takes the arrayList of numbers and changes them back into characters/digits
	public static String decode(ArrayList<Integer> encoded) {
		char[] key = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
				'p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		StringBuffer decoded = new StringBuffer();
		
		for(int i = 0; i < encoded.size(); i++) {//iterate through list of encoded numbers
			for(int k = 0; k < key.length; k++) {//iterate through keyList to see which each num matches to
				if(encoded.get(i) == k) {//number is key index
					if(k == 0) {
						decoded.append(key[key.length-1] + " ");
					} else {
						decoded.append(key[(k-1)] +" ");
					}
				}
			}
		}
		return decoded.toString();
	}



}
