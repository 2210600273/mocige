package cn.edu.sdjzu.mocige_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

/**
 * OpenAPI配置类
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mocige API文档")
                        .description("单词记忆应用API文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("SDJZU")
                                .url("http://www.sdjzu.edu.cn")
                                .email("contact@sdjzu.edu.cn")));
    }
}