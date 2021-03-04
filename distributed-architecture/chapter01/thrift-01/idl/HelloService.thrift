namespace java com.leolian.distributed.architecture.chapter01.thrift01.server
include 'UserModel.thrift'

service HelloService {
	string sayHello(1:UserModel.User user);
}