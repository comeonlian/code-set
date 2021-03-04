package com.leolian.code.fragment.jdk8.lambda;

import java.util.function.Consumer;

public class LambdaConsumerDemo {

	public static void donation(Integer money, Consumer<Integer> consumer) {
		consumer.accept(money);
	}

	public static void main(String[] args) {
		//donation(1000, money -> System.out.println("donate " + money + " rmb"));
		
		Consumer<String> accept = (str) -> {System.out.println(str + " , you're sb!");};
		accept.andThen((str) -> {System.out.println(str + " , you're sb!");}).accept("Rose");
	}
}