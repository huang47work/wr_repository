package com.wangrun.swagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("wr.com.controller")
public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//        		.groupName("全部API")
//        		.forCodeGeneration(true).select()
//        		.apis(RequestHandlerSelectors.any())
//                //过滤生成链接
//                .paths(PathSelectors.regex("/*/.*"))
//                .build()
//                .apiInfo(apiInfo());
//    }
	 private  Docket API(String groupName,String path) {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName(groupName)
	                .genericModelSubstitutes(DeferredResult.class)
	                .useDefaultResponseMessages(false)
	                .forCodeGeneration(true)
	                .pathMapping("/")
	                .select()
	                .paths(or(regex(path)))
	                .build()
	                .apiInfo(apiInfo());
	    }

	    @Bean
	    public Docket adviceAPI() {
	        return API("建议API","/advice.*");
	    }
	    @Bean
	    public Docket productsAPI() {
	        return API("金融产品API","/products.*");
	    }
	    @Bean
	    public Docket agreementAPI() {
	        return API("授信批复API","/agreement.*");
	    }
	    @Bean
	    public Docket fileAPI() {
	        return API("上传接口API","/file.*");
	    }
	    @Bean
	    public Docket BBInfoAPI() {
	        return API("借款单API","/BBInfo.*");
	    }
	    @Bean
	    public Docket invoiceApplyAPI() {
	        return API("供应链款项API","/invoiceApply.*");
	    }
	    @Bean
	    public Docket invoiceAPI() {
	        return API("发票API","/invoice.*");
	    }
	    @Bean
	    public Docket menuAPI() {
	        return API("菜单API","/menu.*");
	    }
	    @Bean
	    public Docket RBInfoAPI() {
	        return API("还款单API","/RBInfo.*");
	    }
	    
	    @Bean
	    public Docket regionAPI() {
	        return API("地区接口API","/region.*");
	    }
	    @Bean
	    public Docket enterticketAPI() {
	        return API("入库单API","/enterticket.*");
	    }
	    @Bean
	    public Docket loanApplyAPI() {
	        return API("借款申请单API","/loanApply.*");
	    }
	    @Bean
	    public Docket messageAPI() {
	        return API("消息中心API","/message.*");
	    }
	    
	    @Bean
	    public Docket userAPI() {
	        return API("登录注册API","/user.*");
	    }
    private ApiInfo apiInfo() {
                ApiInfo apiInfo = new ApiInfo(
                        "网润科技后台 REST API",
                        "This is a description of your API.",
                        "version:1.0",
                        "API TOS",
                        "wangrn@qq.com",
                        "API License",
                        "API License URL"
                );
//        Contact contact=new Contact("章国东","http://zhangguodong.me","gudegg@gmail.com");
//        ApiInfo apiInfo = new ApiInfoBuilder().license("Apache License Version 2.0").title("商品秒杀rest接口").description("商品秒杀接口").contact(contact).version("1.0").build();

        return apiInfo;
    }
}