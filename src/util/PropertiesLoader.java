package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

  private PropertiesLoader() {
  }

  public static Properties loadProperties(String path) {
    Properties props = new Properties();
    try (InputStream input = new FileInputStream(path)) {
      props.load(input);
    } catch (IOException ex) {
      //TODO: Make this better
      ex.printStackTrace();
    }
    return props;
  }
}
