package com.leolian.code.fragment.book.netty.chapter10.xml;

import com.leolian.code.fragment.book.netty.chapter10.xml.pojo.Address;
import com.leolian.code.fragment.book.netty.chapter10.xml.pojo.Customer;
import com.leolian.code.fragment.book.netty.chapter10.xml.pojo.Order;
import com.leolian.code.fragment.book.netty.chapter10.xml.pojo.Shipping;

/**
 * @author Administrator
 * @date 2014年3月1日
 * @version 1.0
 */
public class OrderFactory {

	public static Order create(long orderID) {
		Order order = new Order();
		order.setOrderNumber(orderID);
		order.setTotal(9999.999f);
		Address address = new Address();
		address.setCity("南京市");
		address.setCountry("中国");
		address.setPostCode("123321");
		address.setState("江苏省");
		address.setStreet1("龙眠大道");
		order.setBillTo(address);
		Customer customer = new Customer();
		customer.setCustomerNumber(orderID);
		customer.setFirstName("李");
		customer.setLastName("林峰");
		order.setCustomer(customer);
		order.setShipping(Shipping.INTERNATIONAL_MAIL);
		order.setShipTo(address);
		return order;
	}
}
