package sample.Parts;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Выбор файла
public class OpenFile {

    private String path = ""; // Строка пути файла
    private String textFromFile = ""; // Строка содержимого

    // Геттеры
    public String getPath() {
        return path;
    }

    public String getTextFromFile() {
        return textFromFile;
    }

    // Конструктор
    public OpenFile() {

        // FileChooser для выбора файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        // Фильтр .txt
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".txt, .net", "*.txt", "*.net");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());

        path = file.getAbsolutePath();

        try {
            FileReader fileReader = new FileReader(file);
            Scanner scan = new Scanner(fileReader);
            // Добавление строк из файла в строку textFromFile
            while (scan.hasNextLine()) {
                textFromFile += scan.nextLine() + "\n";
            }

            fileReader.close(); // Закрыть поток

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
