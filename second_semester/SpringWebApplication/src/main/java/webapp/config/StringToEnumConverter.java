package webapp.config;

import org.springframework.core.convert.converter.Converter;
import webapp.entity.UserType;

public class StringToEnumConverter implements Converter<String, UserType> {
  @Override
  public UserType convert(String source) {
    return UserType.fromString(source);
  }
}