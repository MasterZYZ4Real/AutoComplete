import java.lang.*;
import java.util.*;


// Create a term class that contains query string and the corresponding weight
public class Term implements Comparable<Term>{
    public String query;
    public double weight;
    // Constructor initialize data structure of Term
    public Term(String query,double weight){
        if (query == null)
            throw new java.lang.NullPointerException();
        if (weight < 0)
            throw new java.lang.IllegalArgumentException();
        this.query = query;
        this.weight = weight;
    }

    public static Comparator<Term> byReverseWeightOrder(){
        return new Comparator<Term>() {
            public int compare(Term t1, Term t2){
                if (t1.weight > t2.weight)
                    return -1;
                else if (t1.weight == t2.weight)
                    return 0;
                else
                    return 1;
            }
        };
    }
    // Compare terms in lexicographic order using first r_ characters in Term query
    public static Comparator<Term> byPrefixOrder(int r_){
        final int r=r_;
        return  new Comparator<Term>(){
            public int compare(Term t1,Term t2){
                int minLen = Math.min(t1.query.length(),t2.query.length());
                if (minLen >= r){
                    return t1.query.substring(0,r).compareTo(t2.query.substring(0,r));
                }else if(t1.query.substring(0,minLen).compareTo(t2.query.substring(0,minLen)) == 0){
                    if(t1.query.length() == minLen)

                        return -1;
                    else
                        return 1;
                }else
                    return t1.query.substring(0,minLen).compareTo(t2.query.substring(0,minLen));
            }
        };
    }
    // Compare by complete string
    public int compareTo(Term that){
        String s1 = this.query;
        String s2 = that.query;
        return s1.compareTo(s2);
    }

    public String toString(){
        return Double.toString(this.weight) + "\t" + this.query;
    }
}