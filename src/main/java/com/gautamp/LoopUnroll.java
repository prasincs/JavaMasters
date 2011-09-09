package com.gautamp;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/9/11
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoopUnroll {
    public void slowMethod(){
        int i;
            for (i = 0; i < Integer.MAX_VALUE; i+=254){
                System.out.println(i);
            }
        System.out.println("End result == "+i +" "+ Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        LoopUnroll loopUnroll = new LoopUnroll();
        loopUnroll.slowMethod();
        loopUnroll.slowMethod();
    }
}
