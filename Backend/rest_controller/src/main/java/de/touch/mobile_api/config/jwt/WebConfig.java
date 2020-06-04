package de.touch.mobile_api.config.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")
        .allowedOrigins("*")
        //.allowedOrigins("http://domain2.com")
        .allowedMethods("GET", "PUT", "DELETE", "POST")
        .allowedHeaders("*")
        //.allowedHeaders("Content-Type", "Authorization")
        //.exposedHeaders("header1", "header2")
        .allowCredentials(false)
        .maxAge(3600);
    }
}