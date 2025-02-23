package com.kashibuchikyamin.htmxdemo.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	RestClient.Builder restClientBuilder(RestClientBuilderConfigurer configurer) {
		ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.defaults()
				.withConnectTimeout(Duration.ofSeconds(5)) // 接続タイムアウト
				.withReadTimeout(Duration.ofSeconds(5)); // 読み取りタイムアウト

		RestClient.Builder builder = RestClient.builder()
				.defaultStatusHandler(
						// ステータスコードが4xx・5xxの場合に例外が出ないようにする
						status -> true,
						(request, response) -> {
							/* 何もしない */ })
				.requestFactory(ClientHttpRequestFactoryBuilder.simple().build(settings));
		// これが無いとLocalDateが配列に変換されてしまう
		configurer.configure(builder);
		return builder;
	}

//	@Bean
//	RestClient restClient(RestClient.Builder restClientBuilder, @Value("${hello-service.base-url}") String baseUrl) {
//		RestClient restClient = restClientBuilder.baseUrl(baseUrl).build(); // テスト時に使われないように、baseUrlのみこちらで設定
//		return restClient;
//	}
}
