package lp.ioc;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Component
public class LpFxmlLoader extends FXMLLoader implements ApplicationContextAware {

  public LpFxmlLoader() throws MalformedURLException {

    super(LpFxmlLoader.class.getResource("/lp/ui/RootPane.fxml"));
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    setControllerFactory(applicationContext::getBean);
  }
}
