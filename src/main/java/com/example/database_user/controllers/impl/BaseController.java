package com.example.database_user.controllers.impl;

import es.mdp.led.config.security.LanguageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BaseController {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private LanguageConfig languageConfig;

  public String getMessage(final String code) {
    try {
      return messageSource.getMessage(code, new Object[0], languageConfig.getLocale());
    } catch (Exception e) {
      return code;
    }
  }

  public String getMessage(final String code, final Locale locale) {
    return messageSource.getMessage(code, new Object[0], locale);
  }


}
