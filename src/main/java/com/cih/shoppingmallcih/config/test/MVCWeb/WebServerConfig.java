package com.cih.shoppingmallcih.config.test.MVCWeb;

import com.cih.shoppingmallcih.common.test.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServerConfig implements WebMvcConfigurer {

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.setUseTrailingSlashMatch(true)
//                .addPathPrefix("v2", HandlerTypePredicate.forAnnotation(RestController.class,
//                        Controller.class))
//                .setPathMatcher(new AntPathMatcher())
//                .setUrlPathHelper(new UrlPathHelper());
//    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//
//        // /v1/hotels?contentType=json 으로 요청하면 JSON문서로 응답
//
//        configurer.parameterName("contentType") // 콘텐츠 협상 기능을 사용하기 위한 URI 파리마티로 contentType으로 설정
//                .ignoreAcceptHeader(true)   //accept헤더를 사용한 콘텐츠 협상 기능을 사용하지 않도록 설정
//                .defaultContentType(MediaType.APPLICATION_JSON) // 적합한 값을 찾지 못하면, 기본값 application/json으로 설정
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 클라이언트가 '/css' 경로로 요청하는 모든 정적 파일을 코드베이스의 '/static/css' 경로에서 찾는 예제
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("classpath:/static/css/");
//        registry.addResourceHandler("/html/**")
//                .addResourceLocations("classpath:/static/html/");
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("main");
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        // 클라이언트가 전달하는 Accept 헤더 값에 따라 XML 혹은 JSON 메시지를 응답하도록
//        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new MappingJackson2XmlHttpMessageConverter());
//    }
//

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // board 경로에 대해 interceptor 발생
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/board/*")
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }



}
