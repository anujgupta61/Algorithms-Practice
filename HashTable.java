import java.util.Scanner;

class DataItem {
	int key;
	int data;
}

class MyHashTable {
	private static final int MAXSIZE = 10;
	public DataItem[] ht = new DataItem[MAXSIZE];

	private int hashCode(int key) {
		return key % MAXSIZE;
	}

	public int searchItem(int k) {
		int index = this.hashCode(k);
		if(this.ht[index] != null && this.ht[index].key == k)
			return index;
		int i = index + 1;
		while(i != index) {
			if(this.ht[i] != null && this.ht[i].key == k)
				return i;
			i = (i + 1) % MAXSIZE;
		}
		return -1;
	}

	public void insertItem(int k, int data) {
		int index = this.hashCode(k);
		if(this.ht[index] != null && this.ht[index] != null) { // Use Linear probing since data item is already present at calculated index - find next empty cell index
			int i = index + 1;
			while(this.ht[i] != null && i != index) { // Non-empty cell
				i = (i + 1) % MAXSIZE;
			}
			if(i == index)
				index = -1;
			else
				index = i;	
		}
		if(index == -1)
			System.out.println("Hash table is full . Can not insert more items .");
		else {
			this.ht[index] = new DataItem();
			this.ht[index].key = k;
			this.ht[index].data = data;
			System.out.println("Data item having key " + k + " and value " + data + " has been inserted ...");
		}
	}

	public void deleteItem(int k) {
		int index = this.searchItem(k);
		if(index == -1)
			System.out.println("Data item not found ...");
		else {
			this.ht[index] = null;
			System.out.println("Data item having key " + k + " has been deleted ...");
		}
	}

	public void printHashTable() {
		System.out.println("Hash table -\n\tKey\tValue");
		for(int i = 0; i < MAXSIZE; i ++) {
			DataItem di = this.ht[i];
			if(di == null)
				System.out.println("\t~\t~");
			else
				System.out.println("\t" + di.key + "\t" + di.data);
		}
	}
}

public class HashTable {
	public static void main(String[] args) {
		MyHashTable myht = new MyHashTable();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		while(choice != 5) {
			System.out.println("1: Insert item\n2: Delete item\n3: Search item\n4: Print Hash table\n5: Exit");
			System.out.print("Enter your choice: ");
			choice = scan.nextInt();
			switch(choice) {
				case 1: {
					System.out.print("Enter key of data item to insert: ");
					int key = scan.nextInt();
					System.out.print("Enter value of data item to insert: ");
					int data = scan.nextInt();
					myht.insertItem(key, data);
					break;		
				}
				case 2: {
					System.out.print("Enter key of data item to delete: ");
					int key = scan.nextInt();
					myht.deleteItem(key);
					break;		
				}
				case 3: {
					System.out.print("Enter key to search: ");
					int k = scan.nextInt();
					int index = myht.searchItem(k);
					if(index != -1)
						System.out.println("Data item is found at index " + index + " and value is " + myht.ht[index].data);
					else
						System.out.println("Data item not found ...");
					break;		
				}
				case 4: {
					myht.printHashTable();
					break;		
				}
				case 5: {
					break;
				}
				default: {
					System.out.println("Invalid input ... Try again ...");
				}
			}
		}
	}
}