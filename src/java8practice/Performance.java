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
    static void arrayVlist()
    {
        int loops=1000000;
        long total=0;
        int[] arr = new int[loops];
        List<Integer> list = new ArrayList<>(loops);
        Instant start = Instant.now();
        for (int i=0; i<loops; i++) {  // loop alone .03 ns per loop
            total += i;         // .4 nsec per add int, .47 ns adding long
            //arr[i]=i;       // .61 nsec per array assignment
            list.add(i);        // 18 ns with pre alloc, 46 ns without
            // both statements above, .8 to 1.0
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
