/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pl.edu.pw.ee;

/**
 *
 * @author seredak
 */
public class Surprise {

    public int countMaxSum(int[] plansza) {

        if (plansza == null) {
            throw new IllegalArgumentException("Plansza cant be null.");
        }

        int n = plansza.length;

        if (n <= 2 && n >= 99999) {
            throw new IllegalArgumentException("Plansza should have length: <2,99999>");
        }

        int c = 1;
        while (c <= n - 1) {

            int max = Integer.MIN_VALUE;
            for (int i = 6; i > 0; i--) {
                if (c - i >= 0 && c - i <= n - 1) {
                    if (plansza[c - i] > max) {
                        max = plansza[c - i];
                    }
                }
            }
            
            if(plansza[c] + max <= Integer.MAX_VALUE && plansza[c] + max >= Integer.MIN_VALUE)
            plansza[c] = plansza[c] + max;

            c++;
        }

        return plansza[n - 1];
    }
}
