package com.gautamp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/8/11
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReflectionTest {
    static class Test{
        int test;
        int test2;

        void testMethod(Object... objects){

        }

    }

    public static void main(String[] args) {
        Class c  = Test.class;

        for(Method method: c.getDeclaredMethods()){
            System.out.println(method.getName()+ ": "+ method.isVarArgs());
        }
    }
}
