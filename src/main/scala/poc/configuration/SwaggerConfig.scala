package poc.configuration

import java.time.{LocalDate, LocalDateTime}

import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors}
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
  * Swagger configuration
  *
  * @author Noel Rodriguez
  */
@Configuration
@EnableSwagger2
class SwaggerConfig {
  @Bean def uiExperienceApi: Docket = {
    return new Docket(
      DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo)
      .groupName("spring-boot-scala-slick")
      .directModelSubstitute(classOf[LocalDate], classOf[String])
      .directModelSubstitute(classOf[LocalDateTime], classOf[String])
      .select.paths(PathSelectors.any).build
  }

  private def apiInfo: ApiInfo = {
    return new ApiInfoBuilder()
      .title("Devstack Slick")
      .license("Apache License Version 2.0")
      .contact("trushn45@gmail.com")
      .version("0.0.1-SNAPSHOT").build
  }


}
