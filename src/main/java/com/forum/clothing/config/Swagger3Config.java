package com.forum.clothing.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringBootConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zb
 */
@SpringBootConfiguration
@EnableOpenApi
public class Swagger3Config {

    /**
     *   application中还配置了mvc，因为springboot2.6.1与swagger3不兼容
     */
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // ture 启用Swagger3.0， false 禁用（生产环境要禁用）
                .enable(false)
                .select()
                // 扫描的路径使用@Api的controller
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("天涯乐途")
                .contact(new Contact("zzb", "https://ysamall.cn", "zhouzb27@gmall.com"))
                .version("1.0")
                .build();
    }
}

