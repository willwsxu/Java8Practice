
package classic;

// Move disks from peg A to B via C

import static java.lang.System.out;

public class TowerHanoi {
    static void move(int n, int id, char src, char dest, char temp)
    {
        if ( n==1) {
            out.println("Move "+id+ " from "+src+" to "+dest);
            return;
        }
        move(n-1, n-1, src, temp, dest);
        move(1, n, src, dest, temp);
        move(n-1, n-1, temp, dest, src);
    }
    
    public static void main(String... args)
    {
        move(3, 3, 'A', 'B', 'C');
    }
}
