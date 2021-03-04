package com.leolian.code.fragment.book.distributed.chapter01.demo;

import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLDemo {

	public static void main(String[] args) {
		Person person = new Person();
		person.setAddress("hangzhou,China");
		person.setAge(18);
		person.setBirth(new Date());
		person.setName("zhangsan");

		XStream xStream = new XStream(new DomDriver());
		xStream.alias("person", Person.class);
		String personXML = xStream.toXML(person);
		
		System.out.println(personXML);
		
		Person zhangsan = (Person) xStream.fromXML(personXML);
		System.out.println(zhangsan);
	}

}
