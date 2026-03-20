package cn.kutori.bd2swing.Main.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    private BorderPane contentPane;

    @FXML
    private ListView<String> quickList;

    @FXML
    public void initialize() throws IOException {
        quickList.getItems().addAll(
                "WELCOME2025",
                "BD2EVENT",
                "SPRINGGIFT"
        );
    }

    @FXML
    private void showExchange() throws IOException {
        loadCenter("/view/exchange.fxml");
    }

    @FXML
    private void showHistory() throws IOException {
        loadCenter("/view/history.fxml");
    }

    private void loadCenter(String fxml) throws IOException {
        Parent page = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource(fxml))
        );
        contentPane.setCenter(page);
    }

    @FXML
    private void quickExchange() {
        String code = quickList.getSelectionModel().getSelectedItem();
        if (code != null) {
            System.out.println("快速兑换：" + code);
        }
    }
}