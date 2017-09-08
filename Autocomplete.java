import java.lang.*;
import java.util.*;
/*import Term;
import BinarySearchDeluxe;
import StdIn;
import StdOut;
import In;*/

public class Autocomplete{
    public Term[] queries;

    public Autocomplete(Term[] terms){
        if(terms==null)
            throw new java.lang.NullPointerException();
        this.queries=terms;
        Arrays.sort(queries);
    }

    // Return all matches with given prefix, in descending order of weight
    public Term[] allMatches(String prefix){
        Term tempKey=new Term(prefix,0);

        int loID=BinarySearchDeluxe.firstIndexOf(queries,tempKey,Term.byPrefixOrder(prefix.length()));
        int hiID=BinarySearchDeluxe.LastIndexOf(queries,tempKey,Term.byPrefixOrder(prefix.length()));
/*        System.out.printf("loID is %d, hiID is %d,prefix is %s\n",loID,hiID, prefix);
        System.out.printf("%s compares to %s, return %d\n",queries[8].query,prefix,Term.byPrefixOrder(prefix.length()).compare(queries[8],tempKey));
        for (int i=0;i<13;i++)
            System.out.printf(" Term %d is %s \n",i,queries[i].query);*/
        if(loID==-1||hiID==-1)
            throw new java.lang.NullPointerException();
        Term[] matches=new Term[hiID-loID+1];
        matches=Arrays.copyOfRange(queries,loID,hiID+1);
        Arrays.sort(matches,Term.byReverseWeightOrder());
        return matches;
    }

    public static void main(String[] args){
        if(args==null||args.length<2)
            throw new java.lang.IllegalArgumentException();
        //Read in terms from file
        String filename=args[0];
        In in=new In(filename);
        int N=in.readInt();
        Term[] terms=new Term[N];
        for(int i=0;i<N;i++){
            double weight = in.readDouble();
            in.readChar();
            String query=in.readLine();
            terms[i]=new Term(query,weight);
        }
        int k=Integer.parseInt(args[1]);
        Autocomplete autocomplete=new Autocomplete(terms);
        while(StdIn.hasNextLine()){
            String prefix=StdIn.readLine();
            Term[] results=autocomplete.allMatches(prefix);
            for(int i=0;i<Math.min(k,results.length);i++)
                StdOut.println(results[i]);
        }
    }

}