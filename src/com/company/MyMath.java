package com.company;

import java.lang.Math;
public class MyMath {
    private double array[];
    public MyMath(){}
    public MyMath(int n, double arr[]){
        array = new double[n];
        for(int i = 0; i < n; ++i){
            array[i] = arr[i];
        }
    }
    public int fibonacci(int n) {
        if ((n == 0) || (n == 1)) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n-1)*n;
        }
    }
    public double divide(double a, double b){
        return a / b;
    }
    public double get(int index){
        return array[index];
    }
    public double sum(double a, double b){
        return a+b;
    }
    public double mult(double a, double b){
        return a*b;
    }
    public double D(double a, double b, double c){
        return Math.sqrt(b*b - 4*a*c);
    }
    public double[] x(double a, double b, double c){
        return new double[]{divide(-b+D(a,b,c),2*a),divide(-b-D(a,b,c),2*a)};
    }
}
