package com.example.database_user.configs.security;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class LanguageConfig implements WebMvcConfigurer {

  @Bean
  public LocaleResolver localeResolver() {
    return new SmartLocaleResolver();
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Bean(name = "messageSource")
  public MessageSource getMessageResource()  {
    ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

    messageResource.setBasename("classpath:messages");
    messageResource.setDefaultEncoding("UTF-8");
    return messageResource;
  }

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  public Locale getLocale() {
    return LocaleContextHolder.getLocale();
  }

  private class SmartLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(final HttpServletRequest request) {
      return request.getLocale();
    }

    @Override
    public void setLocale(final HttpServletRequest request, final HttpServletResponse response, final Locale locale) {
      LocaleContextHolder.setLocale(locale);
    }
  }
}
}
