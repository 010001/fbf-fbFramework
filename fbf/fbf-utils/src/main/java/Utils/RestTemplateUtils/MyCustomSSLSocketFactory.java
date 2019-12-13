package Utils.RestTemplateUtils;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyCustomSSLSocketFactory extends SSLSocketFactory{
	private final SSLSocketFactory sslSocketFactory;
	
	public MyCustomSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
	}
	
	private Socket overideProtocol(Socket socket) {
		if(!(socket instanceof SSLSocket)) {
			throw new RuntimeException("an instance of SSLSocket is exception");
		}
		((SSLSocket) socket).setEnabledProtocols(new String[] {"TLSv1"});
		return socket;
	}
	
	// 返回默认启用的密码套件.除非一个列表启用，对SSL连接的握手会使用这些密码套件.
	// 这些默认的服务的最低质量要求保密保护和服务器身份验证
	@Override
	public String[] getDefaultCipherSuites() {
		return sslSocketFactory.getDefaultCipherSuites();
	}
	// 返回的密码套件可用于SSL连接启用的名字
	@Override
	public String[] getSupportedCipherSuites() {
		return sslSocketFactory.getSupportedCipherSuites();
	}
	@Override
	public Socket createSocket(Socket arg0, String arg1, int arg2, boolean arg3) throws IOException {
		Socket socket = sslSocketFactory.createSocket(arg0, arg1, arg2, arg3);
		return overideProtocol(socket);
	}
	@Override
	public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
		Socket socket = sslSocketFactory.createSocket(arg0, arg1);
		return overideProtocol(socket);
	}
	@Override
	public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2, int arg3) throws IOException {
		Socket socket = sslSocketFactory.createSocket(arg0, arg1, arg2, arg3);
		return overideProtocol(socket);
	}
	@Override
	public Socket createSocket(String arg0, int arg1) throws IOException, UnknownHostException {
		Socket socket = sslSocketFactory.createSocket(arg0, arg1);
		return overideProtocol(socket);
	}
	@Override
	public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
			throws IOException, UnknownHostException {
		Socket socket = sslSocketFactory.createSocket(arg0, arg1, arg2, arg3);
		return overideProtocol(socket);
	}
	
}
