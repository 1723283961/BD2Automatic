package cn.kutori.bd2swing.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BD2LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BD2LoginApplication.class.getResource("/bd2swing/View/Login/login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500 , 500);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/bd2swing/Css/glass-login.css")).toExternalForm()
        );
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
