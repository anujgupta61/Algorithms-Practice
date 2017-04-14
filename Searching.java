import java.util.Scanner ;

class Search {
  private int[] arr ;

  public void setArray(int[] arr) {
    this . arr = arr ;
  }

  public int[] getArray() {
    return this . arr ;
  }

  public void linearSearch(int num) {
    int pos = -1 ;
    int[] arr = this . arr ;
    int n = arr . length ;
    for(int i = 0 ; i < n ; i ++) {
      if(arr[i] == num) {
        pos = i ;
        break ;
      }
    }
    if(pos != -1) {
      System . out . println(num + " found at pos : " + pos) ;
    } else {
      System . out . println(num + " not found ...") ;
    }
  }
}

public class Searching {
  public static void main(String[] args) {
    Scanner s = new Scanner(System . in) ;
    int choice = 0 ;
    Search s1 = new Search() ;
    while(choice != 3) {
      System . out . print("Choose -\n1 : Input array\n2 : Linear Search\n3 : Exit\nChoice : ") ;
      choice = s . nextInt() ;
      switch(choice) {
        case 1 : {
          System . out . print("Size : ") ;
          int size = s . nextInt() ;
          int[] arr = new int[size] ;
          System . out . println("Input elements -") ;
          for(int i = 0 ; i < size ; i ++) {
            arr[i] = s . nextInt() ;
          }
          s1 . setArray(arr) ;
          System . out . println("Array has been input successfully ...") ;
          break ;
        }
        case 2 : {
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . linearSearch(num) ;
          break ;
        }
        case 3 : {
          break ;
        }
        default : {
          System . out . println("Invalid choice ... Try again ...") ;
        }
      }
    }
  }
}
