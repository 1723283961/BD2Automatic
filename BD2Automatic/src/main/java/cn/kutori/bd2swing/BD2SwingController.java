package cn.kutori.bd2swing;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BD2SwingController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
