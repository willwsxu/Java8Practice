/*
 * 8 puzzle A* search algorithms
 * or try Meet in the middle, http://www.infoarena.ro/blog/meet-in-the-middle
 */
package sedgewick;

import static java.lang.System.out;

public class Puzzle8 {
    
    int goal[][]=new int [3][3];
    int start[][]=new int [3][3];
    Puzzle8()
    {
        for (int i=0; i<3; i++)
            for (int j=1; j<=3; j++) {
                goal[i][j-1]=i*3+j;
                start[i][j-1] = 9-goal[i][j-1];
            }
        goal[2][2]=0;  // 0 for blank
    }
        
    public static void print(int g[][])  // copied from GridHelper in codechef
    {
        for (int i=0; i<g.length; i++) {
            for (int j=0; j<g[0].length; j++)
                out.print(g[i][j]+" ");
            out.println();
        }
    }
    public static void main(String[] args)
    {
        Puzzle8 p = new Puzzle8();
        print(p.goal);
        print(p.start);
    }
}
