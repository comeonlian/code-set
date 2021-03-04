package com.leolian.code.fragment.jdk8.others.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Basics {
	Basic[] value();
}