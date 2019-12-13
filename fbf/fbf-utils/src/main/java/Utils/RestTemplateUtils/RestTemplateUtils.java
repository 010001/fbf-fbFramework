package Utils.RestTemplateUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class RestTemplateUtils {
	
    /** The log. */
    private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	private static RestTemplateUtils restTemplate = new RestTemplateUtils();
	
	public static RestTemplateUtils getInstence(){
		return restTemplate;
	}
	
	public String get(String url, Map<String, String> headerMap, boolean innercall) {
		String result = null;
		if(StringUtils.isNotEmpty(url)){
			if(!url.trim().toLowerCase().startsWith("https:"))
			{
				//"HTTPS"
				result = this.httpsGet(url,headerMap,innercall);
			} else {
				result = this.httpGet(url,headerMap,innercall);
			}
		}
		return result;
	}
	
	public String put(String url, String jsonBodyStr, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		if(StringUtils.isNotEmpty(url)){
			if(!url.trim().toLowerCase().startsWith("https:"))
			{
				//"HTTPS"
				result = this.httpsPut(url,jsonBodyStr,headerMap,innercall);
			} else {
				result = this.httpPut(url,jsonBodyStr,headerMap,innercall);
			}
		}
		return result;
	}
	
	public String delete(String url, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		if(StringUtils.isNotEmpty(url)){
			if(!url.trim().toLowerCase().startsWith("https:"))
			{
				//"HTTPS"
				result = this.httpsDelete(url,headerMap,innercall);
			} else {
				result = this.httpDelete(url,headerMap,innercall);
			}
		}
		return result;
	}
	
	public String post(String url, String jsonBodyStr, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		if(StringUtils.isNotEmpty(url)){
			if(!url.trim().toLowerCase().startsWith("https:"))
			{
				//"HTTPS"
				result = this.httpsPost(url,jsonBodyStr,headerMap,innercall);
			} else {
				result = this.httpPost(url,jsonBodyStr,headerMap,innercall);
			}
		}
		return result;
	}
	
	// http方法

	public String httpGet(String url, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate();
		
		// 请求head头信息设置
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		String responseBody = response.getBody();
		HttpStatus statusCode = response.getStatusCode();
		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpGet " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}
	
	public String httpPut(String url, String jsonBodyStr, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate();

		// 请求head头信息设置
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		HttpEntity<Object> requestEntity = new HttpEntity<>(JSON.parse(jsonBodyStr), headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
		String responseBody = response.getBody();
		HttpStatus statusCode = response.getStatusCode();
		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpDelete " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}
	
	public String httpDelete(String url, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate();

		// 请求head头信息设置
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
		String responseBody = response.getBody();
		HttpStatus statusCode = response.getStatusCode();
		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpDelete " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}
	
	public String httpPost(String url, String jsonBodyStr, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate();

		// 请求head头信息设置
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		HttpEntity<Object> postEntity = new HttpEntity<>(JSON.parse(jsonBodyStr),headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url,postEntity,String.class);
		String responseBody = response.getBody();
		HttpStatus statusCode = response.getStatusCode();
		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpPost " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}

	public void httpDownload(String url, String param, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

		// 解决响应数据中文乱码问题
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		converterList.remove(1);//移除原来的转换器
		// 设置字符编码为 utf-8
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		// 添加新的转换器 convert顺序错误会导致失败
		converterList.add(1, converter);
		restTemplate.setMessageConverters(converterList);
		// 请求头信息
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		//对象
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
		//URI
		URI uri = URI.create(url);

		ResponseEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, byte[].class);

		// 响应码
		HttpStatus statusCode = response.getStatusCode();
		// 相应体
		byte[] body = response.getBody();
		InputStream inputStream = new ByteArrayInputStream(body);
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		OutputStream outputStream = null;
		try {
			outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
			byte[] buffer = new byte[1024];
			int i;
			i = bis.read(buffer);
			while(i != -1) {
				outputStream.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}finally {
			try {
				if(null != inputStream) {
					inputStream.close();
				}
				if(null != outputStream) {
					outputStream.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}
	
	// https方法
	
	public String httpsGet(String url, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
		
		// 解决响应数据中文乱码问题
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		converterList.remove(1);//移除原来的转换器
		// 设置字符编码为 utf-8
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		// 添加新的转换器 convert顺序错误会导致失败
		converterList.add(1, converter);
		restTemplate.setMessageConverters(converterList);
		// 请求头信息
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		//对象
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
		//URI
		URI uri = URI.create(url);
		
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
		
		// 响应码
		HttpStatus statusCode = response.getStatusCode();
		// 相应体
		String responseBody = response.getBody();
		
		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpsGet " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}
	
	public void httpsDownload(String url, String param, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
		
		// 解决响应数据中文乱码问题
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		converterList.remove(1);//移除原来的转换器
		// 设置字符编码为 utf-8
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		// 添加新的转换器 convert顺序错误会导致失败
		converterList.add(1, converter);
		restTemplate.setMessageConverters(converterList);
		// 请求头信息
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		//对象
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
		//URI
		URI uri = URI.create(url);
		
		ResponseEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, byte[].class);
		
		// 响应码
		HttpStatus statusCode = response.getStatusCode();
		// 相应体
		byte[] body = response.getBody();
		InputStream inputStream = new ByteArrayInputStream(body);
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		OutputStream outputStream = null;
		try {
			outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
			byte[] buffer = new byte[1024];
	        int i;
	        i = bis.read(buffer);
			while(i != -1) {
	        	outputStream.write(buffer, 0, i);
	        	i = bis.read(buffer);
	        }
			outputStream.flush();
		    outputStream.close();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}finally {
			try {
				if(null != inputStream) {
					inputStream.close();
				}
				if(null != outputStream) {
					outputStream.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}

		/*https://blog.csdn.net/justry_deng/article/details/82531306



		https://blog.csdn.net/ron_2016/article/details/82884183

		https://blog.csdn.net/j3t9z7h/article/details/94592959

		https://my.oschina.net/sdlvzg/blog/1800395*/
	}
	
	public String httpsPut(String url, String jsonBodyStr, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

		// 解决响应数据中文乱码问题
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		converterList.remove(1);//移除原来的转换器
		// 设置字符编码为 utf-8
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		// 添加新的转换器 convert顺序错误会导致失败
		converterList.add(1, converter);
		restTemplate.setMessageConverters(converterList);
		// 请求头信息
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		//对象
		HttpEntity<String> httpEntity = new HttpEntity<String>(jsonBodyStr, headers);
		//URI
		URI uri = URI.create(url);

		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);

		// 响应码
		HttpStatus statusCode = response.getStatusCode();
		// 相应体
		String responseBody = response.getBody();

		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpsGet " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}
	
	public String httpsDelete(String url, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

		// 解决响应数据中文乱码问题
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		converterList.remove(1);//移除原来的转换器
		// 设置字符编码为 utf-8
		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		// 添加新的转换器 convert顺序错误会导致失败
		converterList.add(1, converter);
		restTemplate.setMessageConverters(converterList);
		// 请求头信息
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		//对象
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
		//URI
		URI uri = URI.create(url);

		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

		// 响应码
		HttpStatus statusCode = response.getStatusCode();
		// 相应体
		String responseBody = response.getBody();

		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpsGet " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}
	
	public String httpsPost(String url, String jsonBodyStr, Map<String, String> headerMap, boolean innercall) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

		// 请求head头信息设置
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String key : headers.keySet()) {
			headers.add(key, headerMap.get(key));
		}
		if(innercall) {
			// 内部调用
			headers.add("incloud_innercall","true");
		}
		HttpEntity<Object> postEntity = new HttpEntity<>(JSON.parse(jsonBodyStr),headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url,postEntity,String.class);
		String responseBody = response.getBody();
		HttpStatus statusCode = response.getStatusCode();
		JSONObject obj = new JSONObject();
		try {
			if(StringUtils.isNotEmpty(responseBody)) {
				obj = JSON.parseObject(responseBody);
			}
		} catch (Exception e) {
			log.error("RestTemplateUtils  httpPost " + url + " fail",e);
		}
		if(null == obj) {
			obj = new JSONObject();
		}
		obj.put("statusCode", statusCode.value());
		result = obj.toString();
		return result;
	}

}
