/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import static java.lang.System.out;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WXU
 */
public class Performance {
    static int calc(int i)
    {        
        int ts=i+2;
        int d = 10+ts;
        int d2=20+ts;
        int d3=30+ts;
        if (d>d2)
            d = d2;
        if (d>d3)
            d=d3;
        return d;
    }
    static int calc2(List<int[]> alldist, int s, int ts)
    {
        int t = s+ts+1;
        // step one node back
        int d = alldist.get(ts-1)[s]+alldist.get(0)[t-1];        
        // step two nodes back
        int d2 = alldist.get(ts-1)[s]+alldist.get(0)[t-2];
        if ( d>d2 )
            d = d2;       
        // step three nodes back
        int d3 = alldist.get(ts-1)[s]+alldist.get(0)[t-3];
        return d>d3?d3:d;        
    }
    static void arrayVlist()
    {
        int loops=100000000;
        long total=0;
        int[] arr = new int[loops];
        List<Integer> list = new ArrayList<>(loops);
        List<int[]> alldist = new ArrayList<>();
        alldist.add(arr);
        Instant start = Instant.now();
        for (int i=0; i<loops; i++) {  // loop alone .03 ns per loop
            total += i;         // .4 nsec per add int, .47 ns adding long
            arr[i]=i;       // .61 nsec per array assignment
            // both statements above, .8 to 1.0
            //total += calc(i);           // 1.25 ns
            //total += calc2(alldist, 1, 1);  // .5 ns
            //total = calc2(alldist, 1, 1);     // 1.25 ns
            //arr[i] = calc2(alldist, 1, 1);  // 1.25 ns
            //list.add(i);        // 18 ns with pre alloc, 46 ns without
        }
        Instant end = Instant.now();
        out.println("usec "+ChronoUnit.MICROS.between(start, end));
        out.println("nsec "+ChronoUnit.NANOS.between(start, end));
    }
    
    public static void main(String[] args)
    {
        arrayVlist();
    }
}
