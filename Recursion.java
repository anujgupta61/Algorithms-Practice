import java.util.Scanner;

class MyRecursion {
	public int[] fibo; // To store previous calculated fibonacci term

	// Calculate fibonacci having n terms
	public void fib(int n) { // Check other better method, if exists.
		if(n <= 2)
			return;
		fib(n - 1);
		this.fibo[n - 1] = this.fibo[n - 2] + this.fibo[n - 3];
	}

	public static void fibIter(int n) {
		if(n >= 1)
			System.out.print("0 ");
		if(n >= 2)
			System.out.print("1 ");
		int pre1 = 0, pre2 = 1;
		for(int i = 3; i <= n; i ++) {
			System.out.print((pre1 + pre2) + " ");
			int temp = pre1;
			pre1 = pre2;
			pre2 += temp;
		}
	}

	public static long fact(long n) {
		if(n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}

	// 3 towers and n pegs (disks)
	// Minimum moves required: 2^n - 1
	public static void toh(int n, char src, char dest, char aux) {
		if(n == 1)
			System.out.print("Move peg from tower " + src + " to " + dest + "\n");
		else {
			toh(n - 1, src, aux, dest);
			System.out.print("Move peg from tower " + src + " to " + dest + "\n");
			toh(n - 1, aux, dest, src);
		}
	}
}

public class Recursion {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice != 4) {
			System.out.print("1: Print Fibonacci series\n2: Find factorial\n3: Tower of Hanoi\n4: Exit\n");
			System.out.print("Enter your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					System.out.print("Enter number of terms: ");
					int n = scan.nextInt();
					if(n <= 0) {
						System.out.print("Invalid n: n should be greater than 0.\n");
					} else {
						MyRecursion mr = new MyRecursion();
						mr.fibo = new int[n];
						if(n >= 1)
							mr.fibo[0] = 0;
						if(n >= 2)
							mr.fibo[1] = 1;
						mr.fib(n);
						for(int i = 0; i < n; i ++)
							System.out.print(mr.fibo[i] + " ");	
						System.out.print("\n");
					}
					break;
				}
				case 2: {
					System.out.print("Enter number: ");
					int n = scan.nextInt();
					if(n < 0) {
						System.out.print("Invalid n: n should not be negative.\n");
					} else {
						System.out.print(MyRecursion.fact(n) + "\n");
					}
					break;
				}
				case 3: {
					System.out.print("Enter number of pegs: ");
					int n = scan.nextInt();
					if(n <= 0) {
						System.out.print("Invalid n: n should be greater than 0.\n");
					} else {
						MyRecursion.toh(n, 'A', 'C', 'B'); // src - A, dest - C, aux - B
					}
					break;
				}
				case 4: {
					break;
				}
				default: {
					System.out.print("Invalid choice. Try again.");
				}
			}
		}
	}
}