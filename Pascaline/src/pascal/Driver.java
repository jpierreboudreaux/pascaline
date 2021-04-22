package pascal;
import java.util.Scanner;

public class Driver {
	
	public static void main(String args[]) {
		System.out.println("Why Do Geniuses Always See the Gorilla?");
		Pascaline test = new Pascaline();
		Scanner scan = new Scanner(System.in);
		boolean rotateFlag = false;
		String input = scan.nextLine();
		while(!hasStop(input) || !(hasPlease(input))) {
			if(hasStop(input)) {
				System.out.println("Ah ah ah! You didn't say the magic word!\n\t\t\t  -Dennis Nedry ");
			} else if(rotateFlag) {
						if(hasNumber(input) != 0) {
							test.rotate(hasNumber(input));
						} else {
							System.out.println("You didn't specify which wheel to rotate! Ending shortcut...");
							rotateFlag = !rotateFlag;
						}
			} else if(hasRotate(input) >= 1){
						test.rotate(hasRotate(input));
						rotateFlag = true;
			} else if(hasRotate(input) == 0){
						System.out.println("Please specify which wheel to rotate!");
			} else if(hasDisplay(input)) {
						test.display();
			} else if(hasSlide(input)) {
				test.slide();
			} else if(hasHex(input)) {
				test.hex();
			} else if(hasClear(input)){
						clear(test);
						System.out.println("Done clearing!");
			} else {
				System.out.println("Whoops! No valid input detected. Please try again!");
			}
			input = scan.nextLine();
		}
		System.out.println("Stopping input and closing up shop.");
		scan.close();
	}
	
	public static void clear(Pascaline p) {
		for(int i = 0; i < p.getSize(); i++) {
			while(p.get(i + 1) != p.getSpokes()-1) {
				p.rotate(i + 1);
			}
		}
		p.rotate(1);
	}
	
	public static boolean hasStop(String input) {
		int ptr1 = 0; int ptr2 = 0; int ptr3 = 0;
		char[] arr = input.toLowerCase().toCharArray();
		char[] match1 = {'s', 't', 'o', 'p'};
		char[] match2 = {'e', 'n', 'd'};
		char[] match3 = {'t', 'e', 'r', 'm', 'i', 'n', 'a', 't', 'e'};
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == match1[ptr1]) {
				ptr1 += 1;
				if(ptr1 == match1.length) {
					return true;
				}
			} else {
				ptr1 = 0;
			}
			if(arr[i] == match2[ptr2]) {
				ptr2 += 1;
				if(ptr2 == match2.length) {
					return true;
				}
			} else {
				ptr2 = 0;
			}
			if(arr[i] == match3[ptr3]) {
				ptr3 += 1;
				if(ptr3 == match3.length) {
					return true;
				}
			} else {
				ptr3 = 0;
			}
		}
		return false;
	}
	
	public static boolean hasPlease(String input) {
		int ptr1 = 0; int ptr2 = 0; 
		char[] arr = input.toLowerCase().toCharArray();
		char[] match1 = {'p', 'l', 'e', 'a', 's', 'e'};
		char[] match2 = {'p', 'l', 's'};
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == match1[ptr1]) {
				ptr1 += 1;
				if(ptr1 == match1.length) {
					return true;
				}
			} else {
				ptr1 = 0;
			}
			if(arr[i] == match2[ptr2]) {
				ptr2 += 1;
				if(ptr2 == match2.length) {
					return true;
				}
			} else {
				ptr2 = 0;
			}
		}
		return false;
	}
	
	/*
	 * Parses out the text "rotate" + some integer k
	 * such that the operation rotate(k) can be performed
	 * by the Pascaline.
	 */
	public static int hasRotate(String input) {
		int ptr = 0;
		char[] arr = input.toLowerCase().toCharArray();
		char[] match = {'r', 'o', 't', 'a', 't', 'e'};
		for(int i = 0; i <= arr.length - match.length + ptr; i++) {
			if(arr[i] == match[ptr]) {
				ptr += 1;
				if(ptr == match.length) {
					return hasNumber(input.substring(i, input.length()));
				}
			} else {
				ptr = 0;
			}
		}
		return -1;
	}
	
	public static boolean hasDisplay(String input) {
		int ptr = 0;
		char[] arr = input.toLowerCase().toCharArray();
		char[] match = {'d', 'i', 's', 'p', 'l', 'a', 'y'};
		for(int i = 0; i <= arr.length - match.length + ptr; i++) {
			if(arr[i] == match[ptr]) {
				ptr += 1;
				if(ptr == match.length) {
					return true;
				}
			} else {
				ptr = 0;
			}
		}
		return false;
	}
	
	public static boolean hasClear(String input) {
		int ptr = 0;
		char[] arr = input.toLowerCase().toCharArray();
		char[] match = {'c', 'l', 'e', 'a', 'r'};
		for(int i = 0; i <= arr.length - match.length + ptr; i++) {
			if(arr[i] == match[ptr]) {
				ptr += 1;
				if(ptr == match.length) {
					return true;
				}
			} else {
				ptr = 0;
			}
		}
		return false;
	}
	
	public static boolean hasHex(String input) {
		int ptr1 = 0; int ptr2 = 0;
		char[] arr = input.toLowerCase().toCharArray();
		char[] match1 = {'h', 'e', 'x'};
		char[] match2 = {'h', 'e', 'x', 'a', 'd', 'e', 'c', 'i', 'm', 'a', 'l'};
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == match1[ptr1]) {
				ptr1 += 1;
				if(ptr1 == match1.length) {
					return true;
				}
			} else {
				ptr1 = 0;
			}
			if(arr[i] == match2[ptr2]) {
				ptr2 += 1;
				if(ptr2 == match2.length) {
					return true;
				}
			} else {
				ptr2 = 0;
			}
		}
		return false;
	}
	
	public static boolean hasSlide(String input) {
		int ptr = 0;
		char[] arr = input.toLowerCase().toCharArray();
		char[] match = {'s', 'l', 'i', 'd', 'e'};
		for(int i = 0; i <= arr.length - match.length + ptr; i++) {
			if(arr[i] == match[ptr]) {
				ptr += 1;
				if(ptr == match.length) {
					return true;
				}
			} else {
				ptr = 0;
			}
		}
		return false;
	}
	
	public static int hasNumber(String input) {
		boolean flag = false;
		int num = 0;
		char[] arr = input.toLowerCase().toCharArray();
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] >= '0' && arr[i] <= '9') {
				num = num * 10;
				num += arr[i] - '0';
				flag = true;
			} else if(flag) {
				return num;
			}
		}
		return num;
	}
}
