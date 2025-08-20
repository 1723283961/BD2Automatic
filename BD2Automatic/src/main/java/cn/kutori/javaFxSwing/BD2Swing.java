package cn.kutori.javaFxSwing;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BD2Swing extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button btn = new Button("点我试试");
        btn.setOnAction(e -> System.out.println("按钮被点击了！"));

        StackPane root = new StackPane(btn);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("JavaFX 示例");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
