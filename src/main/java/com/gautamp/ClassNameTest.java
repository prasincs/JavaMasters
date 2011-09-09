package com.gautamp;

import java.sql.Time;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/8/11
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClassNameTest {
    static class A {
        void f(){}
    }
    static class B extends A{
        void f(){}
    }
    static class C extends B {
        void f(){}
    }

    public static void main(String[] args) {
        C c = new C();
        long time = System.currentTimeMillis();
        c.f();
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}
