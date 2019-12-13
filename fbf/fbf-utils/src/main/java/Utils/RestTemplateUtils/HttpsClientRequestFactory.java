package Utils.RestTemplateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {
	/** The log. */
    private Logger log =  LoggerFactory.getLogger(this.getClass());

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
		try {
			if(!(connection instanceof HttpsURLConnection)) {
				throw new RuntimeException("an instance of HttpsClientRequestFactory is expected");
			}
			HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
			TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
						
						@Override
						public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						}
						
						@Override
						public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						}
					}
			};
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new SecureRandom());
			httpsConnection.setSSLSocketFactory(new MyCustomSSLSocketFactory(sslContext.getSocketFactory()));
			HostnameVerifier ignoreHostNameVerifier = new HostnameVerifier() {
				
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			httpsConnection.setDefaultHostnameVerifier(ignoreHostNameVerifier);
			httpsConnection.setHostnameVerifier(ignoreHostNameVerifier);
			super.prepareConnection(httpsConnection, httpMethod);
		} catch (Exception e) {
			log.error("HttpsClientRequestFactory ",e);
		}
	}
	
}


