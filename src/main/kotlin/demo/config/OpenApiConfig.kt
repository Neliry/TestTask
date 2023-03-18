package demo.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(
        @Value("\${api.title}") apiTitle: String,
        @Value("\${api.description}") apiDescription: String,
        @Value("\${api.version}") apiVersion: String
    ): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title(apiTitle)
                    .description(apiDescription)
                    .version(apiVersion)
            )
    }
}
