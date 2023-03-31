package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface MyAfter {
    String title();
    int version() default 1;
    String[] text();
}
