package com.fastjson.poc;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocApplication {

	  public static void main(String[] args)
	  {
	    SpringApplication.run(PocApplication.class, args);
	  }
	  
	  @Bean
	  public HttpMessageConverters fastJsonHttpMessageConverters()
	  {
	    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
	    FastJsonConfig fastJsonConfig = new FastJsonConfig();
	    fastJsonConfig.setSerializerFeatures(new SerializerFeature[] { SerializerFeature.PrettyFormat });
	    fastConverter.setFastJsonConfig(fastJsonConfig);
	    HttpMessageConverter<?> converter = fastConverter;
	    return new HttpMessageConverters(new HttpMessageConverter[] { converter });
	  }

}
