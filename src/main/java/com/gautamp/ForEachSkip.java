package com.gautamp;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/8/11
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForEachSkip {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2,3,4,5};
        int c = 0;
        for (int i: arr){
            if (++c%2 == 0)
                continue;
            else
                System.out.println(i);
        }
    }
}
