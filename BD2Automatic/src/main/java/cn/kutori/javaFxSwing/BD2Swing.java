package cn.kutori.javaFxSwing;


import cn.kutori.BD2AutomaticDoomsdayBook;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class BD2Swing extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button btn1 = new Button("末日之書");
        Button btn2 = new Button("分解裝備");

        btn2.setOnAction(e -> System.out.println("分解裝備"));

        VBox vbox = new VBox(10);

        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(btn1, btn2);

        btn1.setOnAction(e -> {
            try {
                File f = new File("F:\\ideaCode\\idea\\javaPro\\Auto\\BD2AutomaticDecompositionEquipment\\BD2Automatic\\images\\DoomsdayBook\\Start.png");
                System.out.println(f.exists()); // true 才能读取
                new BD2AutomaticDoomsdayBook().start();
            }catch (Exception e1){
                throw new RuntimeException(e1);
            }
        });

        StackPane root = new StackPane(vbox);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("BD2Automatic");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
