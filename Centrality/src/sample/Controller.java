package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Controller {
    public Button button_Check;
    public Label label_Center;

    public void button_Check_Press(ActionEvent actionEvent) {

        // Открытие файла
        OpenFile openFile = new OpenFile();

        // Вывод текста в label_Center
        label_Center.setText(openFile.getTextFromFile());


    }
}
