package com.leolian.code.fragment.jdk8.functional;

import java.util.function.Consumer;

public class CookingDemo {

	public void doTask(String meterial, Consumer<String> consumer) {
		consumer.accept(meterial);
	}

	public static void main(String[] args) {
		CookingDemo cookingDemo = new CookingDemo();
		cookingDemo.doTask("蔬菜", meterial -> System.out.println("清洗" + meterial));
		cookingDemo.doTask("蔬菜", meterial -> System.out.println(meterial + "切片"));
		cookingDemo.doTask("食用油", meterial -> System.out.println(meterial + "烧热"));
		cookingDemo.doTask("", meterial -> System.out.println("炒菜"));
	}

}