package gastos.core_microservice_gastos.shared.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

   /* @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tuempresa.tuapp")) // Cambia esto al paquete de tu aplicación
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("API de Ejemplo")
                        .description("Documentación de la API")
                        .version("1.0.0")
                        .build());
    }*/
   @Bean
   public GroupedOpenApi publicApi() {
       return GroupedOpenApi.builder()
               .group("public-api")
               .pathsToMatch("/*")
               .build();
   }
}