package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Выбор файла
public class OpenFile {

    private String textFromFile = ""; // Строка для текста из файла

    // Геттер
    public String getTextFromFile() {
        return textFromFile;
    }

    // Конструктор
    public OpenFile() {

        // FileChooser для выбора файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        // Фильтр .txt
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());

        try {
            FileReader fileReader = new FileReader(file);
            Scanner scan = new Scanner(fileReader);
            int i = 1;
            // Добавление строк из файла в строку textFromFile
            while (scan.hasNextLine()) {
                textFromFile += i + " : " + scan.nextLine() + "\n";
                i++;
            }

            fileReader.close(); // Закрыть поток

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
