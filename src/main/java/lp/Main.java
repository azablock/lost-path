package lp;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lp.ioc.LpFxmlLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static javafx.scene.input.KeyCombination.NO_MATCH;
import static javafx.stage.StageStyle.UNDECORATED;

public class Main extends Application {

  public static void main(String[] args) {

    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/spring/lp-spring-context.xml");

    primaryStage.setTitle("lost-path");
    primaryStage.initStyle(UNDECORATED);
    primaryStage.centerOnScreen();

    LpFxmlLoader loader = applicationContext.getBean(LpFxmlLoader.class);

    Parent root = loader.load();
    Scene rootScene = new Scene(root, 1280, 800);

    primaryStage.setFullScreenExitKeyCombination(NO_MATCH);
    primaryStage.setFullScreen(true);
    primaryStage.setScene(rootScene);
    primaryStage.show();
  }
}
