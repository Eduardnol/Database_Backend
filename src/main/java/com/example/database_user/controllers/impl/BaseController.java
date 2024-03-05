package com.example.database_user.controllers.impl;

import java.util.Locale;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
public class BaseController {

  @Autowired
  private MessageSource messageSource;

  /*@Autowired
  private LanguageConfig languageConfig;*/

  public String getMessage(final String code) {
    try {
      return messageSource.getMessage(code, new Object[0],
          new Locale("es")); //todo languageConfig.getLocale()
    } catch (Exception e) {
      log.error(e.getMessage());
      return code;
    }
  }

  public String getMessage(final String code, final Locale locale) {
    return messageSource.getMessage(code, new Object[0], locale);
  }


}
