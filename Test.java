public class Test {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = a;
		int[] c = {6, 7, 8};
		a = c;
		for(int i = 0; i < a.length; i ++)
			System.out.print(a[i] + " ");
		System.out.print("\n");
		for(int i = 0; i < b.length; i ++) 
			System.out.print(b[i] + " ");
		System.out.print("\n");
	}
}