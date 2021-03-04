/**
 * 
 */
package com.leolian.code.fragment.jdk8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月18日 下午7:23:35
 */
public class StreamDemo1 {

	public static void main(String[] args) throws IOException {
		Property p1 = new Property("叫了个鸡", 1000, 500, 2);
		Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
		Property p3 = new Property("永和大王", 580, 3000, 1);
		Property p4 = new Property("肯德基", 6000, 200, 4);
		List<Property> properties = Arrays.asList(p1, p2, p3, p4);

//		Collections.sort(properties, (x, y) -> x.distance.compareTo(y.distance));
//		String name = properties.get(0).name;
//		System.out.println("距离我最近的店铺是: " + name);
//
//		name = properties.stream().sorted(Comparator.comparingInt(pro -> pro.distance)).findFirst().get().name;
//		System.out.println("距离我最近的店铺是: " + name);
//
//		long count = properties.stream().filter(p -> p.sales > 1000).count();
//		System.out.println("销量最高的店铺有: " + count);
		
		
//		Property property = properties.stream()
//				.max(Comparator.comparingInt(p -> p.priceLevel))
//				.get();
//		System.out.println(property.getName());
		
		
//		List<Property> props = properties.stream()
//				.sorted(Comparator.comparingInt(x -> x.distance))
//				.limit(2)
//				.collect(Collectors.toList());
//		props.forEach(prop -> {System.out.println(prop.getName());});
		
		// 获取所有店铺的名称
//		List<String> names = properties.stream()
//				.map(p -> p.name)
//				.collect(Collectors.toList());
//		names.forEach(System.out::println);
//		
//		// 获取每个店铺的价格等级
//		Map<String, Integer> map = properties.stream()
//				.collect(Collectors.toMap(Property::getName, Property::getPriceLevel));
//		map.forEach((name, price) -> {System.out.println("name:" + name + " - price: " + price);});
//		
//		// 所有价格等级的店铺列表
//		Map<Integer, List<Property>> priceMap = properties.stream()
//				.collect(Collectors.groupingBy(Property::getPriceLevel));
//		System.out.println(priceMap);
		
		// 筛选出价格等级小于4，按照距离排序的2个店铺名
		// 调用 parallelStream 方法即能并行处理
		List<String> collect = properties.parallelStream()
			.filter(p -> p.priceLevel < 4)
			.sorted(Comparator.comparingInt(Property::getDistance))
			.map(Property::getName)
			.limit(2)
			.collect(Collectors.toList());
		collect.forEach(System.out::println);
		
	}

}

class Property {
	String name;
	// 距离，单位:米
	Integer distance;
	// 销量，月售
	Integer sales;
	// 价格，这里简单起见就写一个级别代表价格段
	Integer priceLevel;

	public Property(String name, int distance, int sales, int priceLevel) {
		this.name = name;
		this.distance = distance;
		this.sales = sales;
		this.priceLevel = priceLevel;
	}
	// getter setter 省略

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * @return the sales
	 */
	public Integer getSales() {
		return sales;
	}

	/**
	 * @param sales
	 *            the sales to set
	 */
	public void setSales(Integer sales) {
		this.sales = sales;
	}

	/**
	 * @return the priceLevel
	 */
	public Integer getPriceLevel() {
		return priceLevel;
	}

	/**
	 * @param priceLevel
	 *            the priceLevel to set
	 */
	public void setPriceLevel(Integer priceLevel) {
		this.priceLevel = priceLevel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Property [name=" + name + ", priceLevel=" + priceLevel + "]";
	}
	
}