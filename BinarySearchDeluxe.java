import java.lang.*;
import java.util.*;
import java.util.Comparator;
public class BinarySearchDeluxe{
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if(a==null||key==null||comparator==null)
            throw new java.lang.NullPointerException();

        int lo = 0;
        int hi = a.length-1;
        // Store binary search when there are only 2 elements
        while(lo+1<hi){
            int mid=(lo+hi)/2;
            // if Key <= mid, the mid is the upper bound
            if (comparator.compare(key,a[mid]) <= 0)
                hi=mid;
            else
                lo=mid;
        }
        if (comparator.compare(key,a[lo]) == 0)
            return lo;
        else if(comparator.compare(key,a[hi]) == 0)
            return hi;
        else
            return -1;
    }

    public static <Key> int LastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if(a==null||key==null||comparator==null)
            throw new java.lang.NullPointerException();

        int lo = 0;
        int hi = a.length-1;
        // Store binary search when there are only 2 elements
        while(lo+1<hi){
            int mid=(lo+hi)/2;
            // if Key <= mid, the mid is the upper bound
            if(comparator.compare(key,a[mid]) < 0)
                hi=mid;
            else
                lo=mid;
        }
        if (comparator.compare(key,a[hi]) == 0)
            return hi;
        else if (comparator.compare(key,a[lo]) == 0)
            return lo;
        else
            return -1;
    }
}
