import java.util.Scanner;

class MySorting {
	private int[] arr ;
	private int order = 1;
	public int n; // 1: Ascending, 2: Descending

	public void setArray() {
		Scanner scan = new Scanner(System.in);
		System.out.print("No. of elements you want to insert: ");
		this.n = scan.nextInt();
		this.arr = new int[this.n];
		System.out.println("Enter array elements -");
		for(int i = 0; i < this.n; i ++)
			this.arr[i] = scan.nextInt();
		System.out.println("Array has been inserted ...");
		this.displayArray();
	}

	public void setOrder() {
		Scanner scan = new Scanner(System.in);
		int temp = 0;
		while(true) {
			System.out.print("1: Ascending --- OR --- 2: Descending ? - ");
			temp = scan.nextInt();
			if(temp == 1 || temp == 2)
				break;
			else
				System.out.println("Invalid choice. Try again.");		
		}
		this.order = temp;
	}

	public void displayArray() {
		System.out.print("Array: ");	
		for(int i = 0; i < this.n; i ++)
			System.out.print(this.arr[i] + " ");
		System.out.print("\n");
	}

	public void bubbleSort() { 
		int[] a = this.arr;
		for(int i = this.n - 1; i > 0; i --) {
			boolean anyswap = false;
			for(int j = 0; j < i; j ++) {
				boolean swap = (this.order == 1) ? (a[j] > a[j + 1]) : (a[j] < a[j + 1]);
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

	public void insertionSort(boolean doPrint) { // 1: Ascending, 2: Descending
		int[] a = this.arr;
		for(int i = 0; i < this.n - 1; i ++) {
			int j = i, num = a[i + 1];
			while((j >= 0) && ((this.order == 1) ? (num < a[j]) : (num > a[j]))) {
				a[j + 1] = a[j];
				j --;
			}
			a[j + 1] = num;
		}
		this.arr = a;
		if(doPrint) {
			System.out.println("Array sorted ...");
			this.displayArray();
		}
	}

	public void selectionSort() { // 1: Ascending, 2: Descending
		int[] a = this.arr;
		for(int i = 0; i < n - 1; i ++) {
			int mj = i;
			for(int j = i + 1; j < n ; j ++) {
				if((this.order == 1) ? (a[mj] > a[j]) : (a[mj] < a[j]))
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

	private void merge(int first, int mid, int last) {
		int[] a = this.arr;
		int[] temp = new int[last - first + 1];
		int i = 0, j = first, k = mid + 1;
		while(j <= mid && k <= last) {
			if((this.order == 1) ? (a[j] <= a[k]) : (a[j] >= a[k]))
				temp[i ++] = a[j ++];
			else
				temp[i ++] = a[k ++];
		}
		while(j <= mid)
			temp[i ++] = a[j ++];
		while(k <= last)
			temp[i ++] = a[k ++];
		for(int l = 0; l < i; l ++)
			a[first + l] = temp[l];
		this.arr = a;
	}

	public void mergeSort(int first, int last) {
		if(first == last)
			return;
		int mid = (first + last) / 2;
		mergeSort(first, mid);
		mergeSort(mid + 1, last);
		this.merge(first, mid, last);
	}

	public void shellSort() { // Uses Insertion sort, Improvement over Insertion sort
		int[] a = this.arr; // Copy here using loop, not assign
		int k = 1, m = this.n;
		while(3 * k + 1 <= m)
			k = 3 * k + 1;
		while(k > 1) {
			for(int i = 0; (i + k) < m; i ++) {
				int size = (int)(Math.floor((m - i - 1) / k)) + 1;
				int[] temp = new int[size];
				for(int j = i, l = 0; j < m; j += k, l ++)
					temp[l] = a[j];
				this.arr = temp;
				this.n = size;
				this.insertionSort(false);
				temp = this.arr;
				for(int j = i, l = 0; j < m; j += k, l ++)
					a[j] = temp[l];
			}
			k = (k - 1) / 3;
		}
		this.arr = a;
		this.n = m;
		this.insertionSort(false);
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
			System.out.println("1: Insert new array\n2: Bubble sort\n3: Insertion sort\n4: Selection sort\n5: Display array\n6: Exit\n" + 
				"7: Merge sort\n8: Shell sort");
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
						ms.setOrder();
						ms.bubbleSort();
					}
					break;
				}
				case 3: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {
						ms.setOrder();
						ms.insertionSort(true);
					}
					break;
				}
				case 4: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {
						ms.setOrder();
						ms.selectionSort();
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
				case 7: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {
						ms.setOrder();
						ms.mergeSort(0, ms.n - 1);
						System.out.println("Array sorted ...");
						ms.displayArray();
					}
					break;
				}
				case 8: {
					if(ms.n == 0)
						System.out.println("Array is empty. Insert new one.");
					else {
						ms.setOrder();
						ms.shellSort();
					}
					break;
				}
				default: {
					System.out.println("Invalid input. Try again.");
				}
			}
		}
	}
}