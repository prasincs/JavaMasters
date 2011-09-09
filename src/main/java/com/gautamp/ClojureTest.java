package com.gautamp;

import clojure.lang.*;

import java.io.StringReader;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/8/11
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClojureTest {
    public static void main(String[] args) throws Exception {
        String prog = "(ns user)" +
                "(defn foo [a b]" +
                "(list a b))";
        clojure.lang.Compiler.load(new StringReader(prog));
        Var foo = RT.var("user", "foo");
        Object result = foo.invoke("Hi", "there");
        System.out.println(result);
    }
}
