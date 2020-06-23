package com.itmacy.dev.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import okhttp3.*;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class GlobalConfig {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5); //核心线程数
		executor.setMaxPoolSize(20); //最大线程数
		executor.setQueueCapacity(25); //队列最大长度
		executor.setKeepAliveSeconds(3000); //线程池维护线程所允许的空闲时间
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());


		return executor;
	}

	/**
	 * 配置异步请求restTemplate
	 * @return
	 */
	@Bean
	public AsyncRestTemplate asyncRestTemplate(){
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(okHttp3ClientHttpRequestFactory());
		return asyncRestTemplate;
	}


	/**
	 * 配置restTemplate
	 *
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}


	/**
	 * 配置restTemplate
	 *
	 * @return
	 */
	@Bean
	public RestTemplate restTemplateByOkHttp() {
		RestTemplate restTemplate = new RestTemplate(okHttp3ClientHttpRequestFactory());
		return restTemplate;
	}


	/**
	 * 配置okhttp3http请求工厂
	 *
	 * @return
	 */
	@Bean
	public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
		return new OkHttp3ClientHttpRequestFactory(okHttpClient());
	}

	/**
	 * 配置okhttp客户端
	 *
	 * @return
	 */
	@Bean
	public OkHttpClient okHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

		//设置代理
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080));

		builder.proxy(proxy);

		Authenticator proxyAuthenticator = new Authenticator() {
			@Override
			public Request authenticate(Route route, Response response) throws IOException {
				String credential = Credentials.basic("", "&DS1**&*@12.scom123");
				return response.request().newBuilder()
						.header("Proxy-Authorization", credential)
						.build();
			}
		};

		return builder.build();
	}


	/**
	 * 设置采用fastjson作为http传输序列化
	 *
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();

//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.BrowserCompatible,SerializerFeature.WriteDateUseDateFormat);

		fastJsonConfig.setSerializerFeatures(
        		SerializerFeature.PrettyFormat,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteEnumUsingName,

				SerializerFeature.IgnoreErrorGetter,
				SerializerFeature.IgnoreNonFieldGetter,
				SerializerFeature.DisableCircularReferenceDetect
		);


//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastConverter.setFastJsonConfig(fastJsonConfig);

		return new HttpMessageConverters(fastConverter);
	}

}
