package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static int count = 0;

    public static void main(String[] args) {
        int N = args[1];
        ExecutorService executor = Executors.newFixedThreadPool(N);
        Object objects[] = new Object[N];
        for(int i = 0; i < N; ++i){
            Class<?> clazz = null;
            try {
                clazz = Class.forName(args[i+2]);
            } catch (ClassNotFoundException | NullPointerException e) {
                e.printStackTrace();
            }
            Constructor<?> ctor = null;
            try {
                ctor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                objects[i] = ctor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        while(count < N) {
            Runnable task = ()->{
                Class<?> clazz = objects[count].getClass();
                for (Method test : clazz.getDeclaredMethods()) {
                    if (test.isAnnotationPresent(MyTest.class)) {
                        for(Method before : clazz.getDeclaredMethods()) {
                            if(before.isAnnotationPresent(MyBefore.class)) try {
                                before.invoke(objects[count]);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            test.invoke(objects[count]);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        for(Method after : clazz.getDeclaredMethods()) {
                            if(after.isAnnotationPresent(MyAfter.class)) try {
                                after.invoke(objects[count]);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            count++;
            executor.execute(task);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }
    }
}
