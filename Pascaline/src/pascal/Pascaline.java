package pascal;

public class Pascaline {
	
	/*
	 * Each individual wheel of the Pascaline is stored within an array that
	 * is configured at the start to hold the number of wheels specified.
	 */
	private Wheel[] wheels;
	/*
	 * Each wheel of the Pascaline is normally configured to calculate in base 10
	 * arithmetic (each wheel has 10 individual spokes from 0-9). To configure a
	 * Pascaline to calculate in different bases, we can change the number of possible
	 * states each wheel can be configured to (e.g. binary = 2, hexadecimal = 16)
	 */
	private int numSpokes;
	/*
	 * Pascal's calculator has a slider which covers up the 9's complement digits
	 * whenever subtraction is not taking place. This boolean with two values
	 * indicates which digits should be displayed by the calculator (normal  = true, 
	 * complementary to base = false;)
	 */
	private boolean slider;
	/*
	 * This boolean adds support for hexadecimal characters to be represented as
	 * alphanumerical characters (i.e. 10 = A, 11 = B, ...) using each letter in
	 * the English alphabet, support for 26 extra single-digit characters (up to 35) is added.
	 */
	private boolean hex;

	public Pascaline() {
		this(6, 10);
	}
	
	public Pascaline(int numWheels) {
		this(numWheels, 10);
	}
	
	public Pascaline(int numWheels, int numSpokes) {
		this.numSpokes = numSpokes;
		slider = true;
		hex = true;
		wheels = new Wheel[numWheels];
		Wheel next = null;
		while(numWheels > 0) {
			Wheel curr = new Wheel(next);
			wheels[numWheels - 1] = curr;
			next = curr;
			numWheels -= 1;
		}
		display();
	}
	
	/*
	 * For user simplicity, the wheels are 1 through n,
	 * and the values are simply converted to 0 through n-1.
	 */
	public void rotate(int wheel) {
		wheels[wheel - 1].rotate();
		display();
	}
	
	public void slide() {
		slider = !slider;
		display();
	}
	
	public void hex() {
		hex = !hex;
		display();
	}
	
	public void display() {
		char[] alph = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int check;
		for(int i = wheels.length - 1; i >= 0; i--) {
			if(slider) {
				check = wheels[i].value;
				if(hex && check >= 10 && check <= 36) {
					System.out.print("[" + alph[check - 10] + "] ");
				} else {
					System.out.print("[" + check + "] ");
				}
			} else {
				check = wheels[i].complement;
				if(hex && check >= 10 && check <= 36) {
					System.out.print("[" + alph[check - 10] + "] ");
				} else {
					System.out.print("[" + check + "] ");
				}
			}
		}
		System.out.println("");
	}
	
	public int get(int wheel) {
		return wheels[wheel - 1].value;
	}
	
	public int getSize() {
		return wheels.length;
	}
	
	public int getSpokes() {
		return numSpokes;
	}
	
	/* Any standard Pascaline (i.e. Pascal's Calculator) comes equipped with 6
	 * individual wheels, each of which can be set through rotary motion to one
	 * of 10 states corresponding to the digits 0-9. This simulacrum allows
	 * Pascalines with more wheels to be tested, as an original Pascaline will
	 * only hold numbers up to 999,999 before overflowing back to 0.
	 */
	private class Wheel {
		/*
		 * Each wheel has three variables: A *next* wheel, which is modified
		 * whenever a rotation resets the wheel to the base value of '0' and
		 * subsequently performs a carry-over, and two *values*, which hold the
		 * current state of the wheel's rotation, and its value in the
		 * *complementary* form of that particular arithmetic base (e.g. for
		 * decimal numbers, 9's complement is used, generalized as base - 1.)
		 */
		Wheel next;
		int value, complement;
		
		public Wheel() {
			value = 0;
			complement = numSpokes - 1;
		}
		
		public Wheel(Wheel next) {
			this();
			this.next = next;
		}
		
		/*
		 * Performs a single rotation of the wheel. If the rotation results in
		 * the looping back to 0, then a carry function is performed towards
		 * the following wheel in the Pascaline by rotating it once.
		 */
		public void rotate() {
			value += 1;
			complement -= 1;
			if(value >= numSpokes) {
				value = 0;
				complement = numSpokes - 1;
				if(next != null) {
					next.rotate();
				}
			}
		}
	}
}
