package com.leolian.code.fragment.book.distributed.chapter01.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * @Description: Hessian序列化
 * @author lianliang
 * @date 2017年9月26日 下午2:47:10
 */
public class HessianSerialize {
	
	public static void main(String[] args) throws Exception{
		Person zhansan = new Person("Hanmeimei", 25);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(os);
		ho.writeObject(zhansan);
		byte[] zhansanBytes = os.toByteArray();
		
		System.out.println("Object array: "+Arrays.toString(zhansanBytes));
		
		ByteArrayInputStream is = new ByteArrayInputStream(zhansanBytes);
		HessianInput hi = new HessianInput(is);
		Person person = (Person) hi.readObject();
		
		System.out.println(person);
	}
	
}
