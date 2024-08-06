package com.chatop.webapp.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi authApi() {
    return GroupedOpenApi.builder()
      .group("Auth")
      .pathsToMatch("/api/auth/**")
      .build();
  }

  @Bean
  public GroupedOpenApi rentalsApi() {
    return GroupedOpenApi.builder()
      .group("Rentals")
      .pathsToMatch("/api/rentals/**")
      .build();
  }

  @Bean
  public GroupedOpenApi messagesApi() {
    return GroupedOpenApi.builder()
      .group("Messages")
      .pathsToMatch("/api/messages/**")
      .build();
  }
}
