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
import java.util.Arrays;
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
    static int calc3(int[][] alldist, int s, int ts)
    {
        int t = s+ts+1;
        int d = 10;
        return d;
    }
    static int calc4(int[][] alldist, int s, int ts)
    {
        int t = s+ts+1;
        int d = alldist[ts-1][s];
        return d;
    }
    static int calc5(int[] alldist, int s, int ts)
    {
        int t = s+ts+1;
        int d = alldist[ts-1];
        return d;
    }
    static int calc6(int[][] alldist, int s, int ts)
    {
        int t = s+ts+1;
        // step one node back
        int d = alldist[ts-1][s]+alldist[0][t-1];    
        // step two nodes back
        int d2 = alldist[ts-1][s]+alldist[0][t-2];
        if ( d>d2 )
            d = d2;       
        // step three nodes back
        int d3 = alldist[ts-1][s]+alldist[0][t-3];
        return d>d3?d3:d;
    }
    static int calc7(int next1, int next2, int next3, int r1, int r2, int r3, int s, int ts)
    {
        int t = s+ts+1;
        // step one node back
        int d = next1+r1;    
        // step two nodes back
        int d2 = next2+r2;
        if ( d>d2 )
            d = d2;       
        // step three nodes back
        int d3 = next3+r3;
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
    
    static void arrayAccessTime()
    {
        final int N=100000;
        int[] elem=new int[N];
        int[] elem2=new int[N];
        int[][] alldist = new int[2][];
        alldist[0] = elem;
        alldist[1] = elem2;
        int val = 10;
        Arrays.fill(elem, val);
        Arrays.fill(elem2, val);
        Instant start = Instant.now();
        long total=0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++){
                //total += 10;          // 3 msec
                //total += val;           // 3 msec
                //total += elem[j];     // 2.7 sec
                //total += elem[i];       // 2.7 sec
                //total += elem[0];       // 2.7 sec
                //total += elem[0]+elem[1];       // 2.9 sec
                //total += calc3(alldist, 1, 1);  // 5 msec
                //total += calc4(alldist, 1, 1);  // 4.9 sec
                //total += calc5(elem, 1, 1);       // 2.9 sec
                //total += calc6(alldist, 1, 1);  // 13.5 sec
                //calc6(alldist, 1, 1);  // 11.5 sec
                //calc7(30, 20, 10, 1, 2, 3, 1, 1);  // 4 milli sec
                calc7(alldist[0][j], alldist[0][j+1-1], alldist[0][j+2-2], alldist[1][j], alldist[1][j+1-1], alldist[1][j+2-2], 1, 1);  // 6.8 sec
            }
        }
        Instant end = Instant.now();
        out.println("nsec "+ChronoUnit.NANOS.between(start, end));
    }
    public static void main(String[] args)
    {
        arrayAccessTime();
    }
}
