import java.util.Scanner;

class MySorting {
	private static final int MAXSIZE = 50;
	private int[] arr = new int[MAXSIZE];
	public int n;

	public void setArray() {
		Scanner scan = new Scanner(System.in);
		System.out.print("No. of elements you want to insert: ");
		this.n = scan.nextInt();
		System.out.println("Enter array elements -");
		for(int i = 0; i < this.n; i ++)
			this.arr[i] = scan.nextInt();
		System.out.println("Array has been inserted ...");
		this.displayArray();
	}

	public void displayArray() {
		System.out.print("Array: ");	
		for(int i = 0; i < this.n; i ++)
			System.out.print(this.arr[i] + " ");
		System.out.print("\n");
	}

	public void bubbleSort(int order) { // 1: Ascending, 2: Descending
		int[] a = this.arr;
		for(int i = n - 1; i > 0; i --) {
			boolean anyswap = false;
			for(int j = 0; j < i; j ++) {
				boolean swap = (order == 1) ? (a[j] > a[j + 1]) : (a[j] < a[j + 1]);
				if(swap) {
					anyswap = true;
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				} 
			}
			if(! anyswap)
				break;
		}
		this.arr = a;
		System.out.println("Array sorted ...");
		this.displayArray();
	}

	public void insertionSort(int order) { // 1: Ascending, 2: Descending
		int[] a = this.arr;
		for(int i = 0; i < n - 1; i ++) {
			int j = i, num = a[i + 1];
			while(((order == 1) ? (num < a[j]) : (num > a[j])) && (j >= 0)) {
				a[j + 1] = a[j];
				if(j == 0)
					break;
				j --;
			}
			a[j] = num;
		}
		this.arr = a;
		System.out.println("Array sorted ...");
		this.displayArray();
	}

	public void selectionSort(int order) { // 1: Ascending, 2: Descending
		int[] a = this.arr;
		for(int i = 0; i < n - 1; i ++) {
			int mj = i;
			for(int j = i + 1; j < n ; j ++) {
				if((order == 1) ? (a[mj] > a[j]) : (a[mj] < a[j]))
					mj = j;
			}
			if(mj != i) {
				int temp = a[mj];
				a[mj] = a[i];
				a[i] = temp;
			}
		}
		this.arr = a;
		System.out.println("Array sorted ...");
		this.displayArray();
	}
}

public class Sorting {
	public static void main(String[] args) {
		MySorting ms = new MySorting();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice != 6) {
			System.out.println("1: Insert new array\n2: Bubble sort\n3: Insertion sort\n4: Selection sort\n5: Display array\n6: Exit");
			System.out.print("Choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					ms.setArray();
					break;
				}
				case 2: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {	
						System.out.print("1: Ascending --- OR --- 2: Descending ? - ");
						int order = scan.nextInt();
						ms.bubbleSort(order);
					}
					break;
				}
				case 3: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {
						System.out.print("1: Ascending --- OR --- 2: Descending ? - ");
						int order = scan.nextInt();
						ms.insertionSort(order);
					}
					break;
				}
				case 4: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {
						System.out.print("1: Ascending --- OR --- 2: Descending ? - ");
						int order = scan.nextInt();
						ms.selectionSort(order);
					}
					break;
				}
				case 5: {
					ms.displayArray();
					break;
				}
				case 6: {
					break;
				}
				default: {
					System.out.println("Invalid input. Try again.");
				}
			}
		}
	}
}