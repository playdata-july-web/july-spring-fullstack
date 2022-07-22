package kr.pe.july.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration //자바 소스가 설정 파일로 쓰인다는 의미
@EnableSwagger2 
public class SwaggerConfig {

	@Bean //스프링 빈으로 등록 의미
    public Docket swaggerApi() {
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		.ignoredParameterTypes(ApiIgnore.class)
        		.apiInfo(swaggerInfo())
        		.select()
                .apis(RequestHandlerSelectors.basePackage("kr.pe.july.controller"))
                .build()
                .useDefaultResponseMessages(false); 
    }
    

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("July Web Project의 API DOC 입니다")
                .description("july-web-project에 대한 문서")
                .license("july-spring-fullstack").licenseUrl("https://github.com/playdata-july-web/july-spring-fullstack")
                .version("1").build();
    }
	
	
}
