/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1b;

/**
 *
 * @author adria
 */
import java.util.*;

public class Lab1b {

    static String getRandomString(int n,String cuv)
    {
        String getRandomString =cuv;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(getRandomString.length()*Math.random());
            sb.append(getRandomString.charAt(index));
        }
        return sb.toString();
    }

    public static void BFS(boolean[][] a, boolean[] visited, int n1, int s) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(s);
        visited[s] = true;

        System.out.print(s + " ");

        while (!queue.isEmpty()) {
            int currentNode = queue.remove();
            visited[currentNode] = true;
            for (int i = 0; i < n1; i++) {
                if (a[currentNode][i] && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    System.out.print(i + " ");
                }
            }
        }
        System.out.println();
    }

    public static boolean connectedGraph(boolean[][] m, int n1) {
        boolean[] visited = new boolean[n1];
        boolean ok;
        for (int i = 0; i < n1; ++i) {
            visited[i] = false;
        }
        BFS(m, visited, n1, 0);
        ok = true;
        for (int i = 0; i < n1; i++) {
            if (visited[i]!=true) {
                ok = false;
                break;
            }
        }
        for (int i = 0; i < n1; i++) {
            if (visited[i]!=true) {
                BFS(m, visited, n1, i);
            }
        }
        return ok;
    }

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int n,k;


        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        String str = args[2];
        k=Integer.valueOf(args[1]);
        n=Integer.valueOf(args[0]);
        Random x=new Random();

        boolean[][] matrix = new boolean[n][n];
        String[] words=new String[n];
        for(int j=0;j<n;j++) {
            words[j]=getRandomString(k,str);
        }
        for(int j=0;j<n;j++) {
            System.out.printf("[ %s ] ",words[j]);
        }
        System.out.printf( "%n");

        int min=n+1;
        int max=0;
         for(int i=0;i<n;i++) {

             int nr=0;
             for (int z =0; z < n; z++) {
                 int ok = 0;
                 char[] first = words[i].toCharArray();
                 char[] second = words[z].toCharArray();
                 for (int j = 0; j < k; j++)
                     for (int r = 0; r < k; r++)
                         if (first[j] == second[r])
                             ok = 1;
                 if (ok == 1) {

                     nr++;
                     matrix[i][z] =true;

                 } else{

                      matrix[i][z] =false;
             }

             }


             if(max<nr)
                 max=nr;

             if(min>nr)
                 min=nr;
         }
         System.out.printf(" %n %n");
         System.out.println(" Numarul Maxim de vecini este "+max);
         System.out.println(" Numarul Minim de vecini este "+min);

         


         if(max==min)
             System.out.println(" Toti au acelasi numar de vecini : "+max);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(" Timpul de executie "+totalTime);


        /*for(int i=0;i<n;i++) {
            System.out.printf(" %n ");
            for (int j = 0; j < n; j++)
                System.out.printf("%B ",matrix[i][j]);
        }*/

        

        System.out.println("");
        System.out.println("Componente conexe:");

        if (connectedGraph(matrix,n)==true) {
            System.out.println("\nGraful conex!");
        } else {
            System.out.println("Graful nu este conex!");
        }


    }

}

