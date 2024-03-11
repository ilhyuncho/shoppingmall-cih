package com.cih.shoppingmallcih.config.test.MVCWeb;

import com.cih.shoppingmallcih.common.test.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

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

    @Bean(value = "localeResolver")
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();

        // Accept-Language헤더가 없거나 알수 없던 헤더 값이 전달되면 Locale 객체는 KOREAN으로 설정 됨
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.KOREAN); // Locale객체 생성 못할떄 Locale 객체를 설정
        return acceptHeaderLocaleResolver;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 1.board 경로에 대해 interceptor 발생
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/board/*")
                .excludePathPatterns("/css/**", "/images/**", "/js/**");

        // 2.Locale interceptor 설정
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        // 클라이언트가 웹 서버에 리소스를 요청할때 Accept-Language헤더가 아닌 파라미터로 Locale값을 변경하고 싶다면
        // localeChangeInterceptor 를 사용
        // 사용자가 GET /Hotels?locale=ko로 요청하면 locale 파리미터 값 'ko'를 사용하여 Locale 객체를 생성하고
        //애플리케이션 내부에서 쓸수 있다.
        localeChangeInterceptor.setParamName("locale");
        registry.addInterceptor(localeChangeInterceptor).excludePathPatterns("/favicon.ico");
    }

//    @Bean
//    public FilterRegistrationBean<CharacterEncodingFilter> defaultCharacterEncodingFilter() {
//
//        // 서블릿 필터 예제
//
//        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//        encodingFilter.setEncoding("ASCII");
//        encodingFilter.setForceEncoding(true);  // 서블릿 필터가 적용되는 요청 메시지와 응답 메시지 모두 인코딩 한다
//
//        FilterRegistrationBean<CharacterEncodingFilter> filterBean = new FilterRegistrationBean<>();
//        filterBean.setFilter(encodingFilter); // 서블릿 필터 객체를 설정 함.
//        filterBean.addInitParameter("paramName", "paramValue"); // 초기 파라미터를 설정
//                                    // 이떄 파라미터 이름과 값을 넣으면 서블릿 필터 인터페이스인 Filter의 init() 메서드 인자
//                                    // 인 FilterConfig 객체에서 사용할서 있다
//        filterBean.addUrlPatterns("*");
//        filterBean.setOrder(1);
//        return filterBean;
//    }


}
