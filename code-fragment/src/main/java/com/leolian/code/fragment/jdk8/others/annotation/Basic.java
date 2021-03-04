package com.leolian.code.fragment.jdk8.others.annotation;

import java.lang.annotation.Repeatable;

@Repeatable(Basics.class)
public @interface Basic {
	String name();
}