package com.company;

import java.lang.reflect.Method;

public class MyMathTest {
    MyMathTest(){}
    @MyTest(title="fibonacci")
    public void fibonacciTest() {
        MyMath instance = new MyMath();
        int x = instance.fibonacci(7);
        assert(x == 21);
    }
    @MyTest(title="factorial")
    public void factorialTest() {
        MyMath instance = new MyMath();
        int x = instance.factorial(10);
        assert(x == 3628800);
    }
    public void runOneTest(Method test) {
        try {
            test.invoke(this);
            System.out.println(test.getName() + " passed");
        } catch (Throwable e) {
            System.out.println(test.getName() + " FAILED by: " +
                    e.toString());
        }
    }
}
