package com.itmacy.dev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *
 *  swagger-ui 的访问地址：http://{ip}:{port}/{context-path}/swagger-ui.html
 *  访问knife4j:http://{ip}:{port}/{context-path}/doc.htm
 *
 * 说明：通过创建多个Docket从而达到分组的作用
 *
 * @Author:itmacy
 * @Date:2020/6/22
 */
@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig {

    @Value("${self-config.url}")
    private String HOST;
    @Value("${self-config.project-description}")
    private String DESC;



    /**
     * 不需要token
     * @return
     */
    @Bean
    public Docket unAuthRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//产生文档内容
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itmacy.dev.module"))//控制暴露的接口
                .paths(PathSelectors.any())
                .build()
                .groupName("入口API")
                .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class)
                .host(HOST)
                ;
    }

//    /**
//     * 需要token
//     * @return
//     */
//    @Bean
//    public Docket authRestApi() {
//
//        List<Parameter> pars = new ArrayList<>();
//        ParameterBuilder pb = new ParameterBuilder();
//        pb.name("Authorization")
//                .description("授权头部，由Bearer +token组成")
//                .modelRef(new ModelRef("string"))
//                .defaultValue("Bearer ")
//                .parameterType("header")
//                .required(true) //header中的Authorization参数必填
//                .build();
//        pars.add(pb.build());
//
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())//产生文档内容
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.itmacy.dev.business.admin"))//控制暴露的接口
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars)
//                .groupName("Web端API")
//                .ignoredParameterTypes(HttpServletRequest.class,HttpServletResponse.class)
//                .host(HOST)
//                ;
//    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Restful API")
                .description(DESC+"接口文档")
                .version("1.0.0")
                .contact(new Contact(
                        "itmacy",
                        "https://www.cnblogs.com/itmacy/",
                        "2792889552@qq.com"))
                .build();
    }
}
