import java.util.Scanner ;

class Search {
  private int[] arr ;

  public void setArray(int[] arr) {
    this . arr = arr ;
  }

  public int[] getArray() {
    return this . arr ;
  }

  public void displayArray() {
    if(this . arr == null)
      System . out . println("Array is empty ...") ;
    else {
      for(int i = 0 ; i < this . arr . length ; i ++)
        System . out . print(this . arr[i] + " ") ;
      System . out . print("\n") ;
    }
  }

  public void binarySearchIterative(int num) { // O(logn) - Time , O(1) - Space
    int first = 0 , last = arr . length - 1 ;
    while(first <= last) {
      int mid = (first + last) / 2 ;
      if(this . arr[mid] == num) {
        System . out . println(num + " found at pos : " + mid) ;
        return ;
      } else {
        if(this . arr[mid] < num)
          first = mid + 1 ;
        else
          last = mid - 1 ;
      }
    }
    System . out . println(num + " not found ...") ;
  }

  public void ternarySearch(int num) { // Time - O(logn) with log base 3
    // 2Log2n + 1 comparisons in Binary Search , 4Log3n + 1 in Ternary Search
    // Binary Search is better than Ternary Search in worst case because of more comparisons
    int[] arr = this . arr ;
    int low = 0 , high = arr . length - 1 ;
    while(low <= high) {
      if(low == high && arr[low] == num) {
        System . out . println(num + " found at pos : " + low) ;
        return ;
      }
      int mid1 = (low + high) / 3 ;
      int mid2 = high - mid1 ;
      if(arr[mid1] == num) {
        System . out . println(num + " found at pos : " + mid1) ;
        return ;
      }
      if(arr[mid2] == num) {
        System . out . println(num + " found at pos : " + mid2) ;
        return ;
      }
      if(arr[mid1] > num) {
        high = mid1 - 1 ;
      }
      if(arr[mid2] < num) {
        low = mid2 + 1 ;
      }
      if(num > arr[mid1] && num < arr[mid2]) {
        low = mid1 + 1 ;
        high = mid2 - 1 ;
      }
    }
    System . out . println(num + " not found ...") ;
  }

  public void binarySearchRecursive(int first , int last , int num) { // O(logn) - Time , O(logn) - Space
    if(first > last) {
      System . out . println(num + " not found ...") ;
      return ;
    } else {
      int mid = (first + last) / 2 ;
      if(this . arr[mid] == num) {
        System . out . println(num + " found at pos : " + mid) ;
        return ;
      } else {
        if(this . arr[mid] < num)
          binarySearchRecursive(mid + 1 , last , num) ;
        else
          binarySearchRecursive(first , mid - 1 , num) ;
      }
    }
  }

  public void interpolationSearch(int num) { // Improvement over Binary Search , Sorted Array
    // Time - O(log(logn)) , If uniformly distributed ; Worst case - O(n) , Space - O(1)
    int[] arr = this . arr ;
    int low = 0 , high = arr . length - 1 ;
    while(low <= high) {
      int pos = low + (int)((double)(num - arr[low]) * (high - low) / (arr[high] - arr[low])) ;
      if(pos < 0 || pos >= arr . length)
        break ;
      if(arr[pos] == num) {
        System . out . println(num + " found at pos : " + pos) ;
        return ;
      } else {
        if(arr[pos] < num)
          low = pos + 1 ;
        else
          high = pos - 1 ;
      }
    }
    System . out . println(num + " not found ...") ;
  }

  public void exponentialSearch(int num) { // Compares last element of sub-arrays of increasing size by factor 2
    // Time - O(logn) , Space - O(logn) - because of Recursive Binary Search , Sorted Array
    // Used for unbounded searching , i.e. , for infinite sized array
    boolean isBreak = false ;
    int i = 1 ;
    for( ; i <= this . arr . length ; i *= 2) {
      if(this . arr[i - 1] == num) {
        System . out . println(num + " found at pos : " + (i - 1)) ;
        return ;
      } else {
        if(this . arr[i - 1] > num) {
          isBreak = true ;
          break ;
        }
      }
    }
    i /= 2 ;
    if(i == 0) {
      System . out . println(num + " not found ...") ;
      return ;
    }
    if(isBreak) {
      binarySearchRecursive((i - 1) , (2 * i - 1) , num) ;
    } else {
      binarySearchRecursive((i - 1) , (this . arr . length - 1) , num) ;
    }
  }

  public void jumpSearch(int num) { // Works on sorted array , Time - n/m + (m - 1) : O(n^(1/2)) , Space - O(1)
    // Optimal jump size - n^(1/2)
    int len = this . arr . length ;
    int m = (int)Math . sqrt(len) ;
    int i = 0 ;
    boolean isBreak = false ;
    for( ; i < len ; i += m) {
      if(this . arr[i] == num) {
        System . out . println(num + " found at pos : " + i) ;
        return ;
      } else {
        if(this . arr[i] > num) {
          isBreak = true ;
          break ;
        }
      }
    }
    i -= m ;
    int last ;
    if(isBreak) {
      last = i + m ;
    } else {
      last = len ;
    }
    int pos = -1 ;
    for(int j = i + 1 ; j < last ; j ++) {
      if(this . arr[j] == num) {
        pos = j ;
        break ;
      }
    }
    if(pos == -1) {
      System . out . println(num + " not found ...") ;
    } else {
      System . out . println(num + " found at pos : " + pos) ;
    }
  }

  public void linearSearch(int num) { // O(n)
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
      System . out . print("Choose -\n1 : Input array\n2 : Linear Search\n3 : Exit\n4 : Binary" +
      " Search Iterative\n5 : Binary Serach Recursive\n6 : Display Array\n7 : Jump Search\n" +
      "8 : interpolation Search\n9 : Exponential Search\n10 : Ternary Search\nChoice : ") ;
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
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . linearSearch(num) ;
          break ;
        }
        case 3 : {
          break ;
        }
        case 4 : {
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . binarySearchIterative(num) ;
          break ;
        }
        case 5 : {
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . binarySearchRecursive(0 , s1 . getArray() . length - 1 , num) ;
          break ;
        }
        case 6 : {
          s1 . displayArray() ;
          break ;
        }
        case 7 : {
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . jumpSearch(num) ;
          break ;
        }
        case 8 : {
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . interpolationSearch(num) ;
          break ;
        }
        case 9 : {
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . exponentialSearch(num) ;
          break ;
        }
        case 10 : {
          if(s1 . getArray() == null) {
            System . out . println("Array is empty ...") ;
            return ;
          }
          System . out . print("Enter number to search : ") ;
          int num = s . nextInt() ;
          s1 . ternarySearch(num) ;
          break ;
        }
        default : {
          System . out . println("Invalid choice ... Try again ...") ;
        }
      }
    }
  }
}
