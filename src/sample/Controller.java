package sample;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    private JFXRadioButton cezar;

    @FXML
    private ToggleGroup cipher;

    @FXML
    private JFXRadioButton vigener;

    @FXML
    private JFXRadioButton rus;

    @FXML
    private ToggleGroup alphablet;

    @FXML
    private JFXRadioButton eng;

    @FXML
    private JFXTextField keyString;

    @FXML
    private JFXTextField hackKey;

    @FXML
    private JFXTextArea initialText;

    @FXML
    private JFXTextArea encryptedText;

    @FXML
    void btnAnalys(MouseEvent event) {
        if(!encryptedText.getText().isEmpty()){
            if(cezar.isSelected()){
                if(rus.isSelected())
                    hackKey.setText(String.valueOf(Cryptanalysis.cryptKeyRus(encryptedText.getText())));
                if(eng.isSelected()){
                    hackKey.setText(String.valueOf(Cryptanalysis.cryptKeyEng(encryptedText.getText())));
                }
            }
            if(vigener.isSelected()){
                if(rus.isSelected()){
                    hackKey.setText(Cryptanalysis.methodICKeyRus(encryptedText.getText()));
                }else if(eng.isSelected()){
                    hackKey.setText(Cryptanalysis.methodICKeyEng(encryptedText.getText()));
                }
            }
        }
    }

    @FXML
    void btnDecrypt(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Внимание!");
        if(!encryptedText.getText().isEmpty() && !keyString.getText().isEmpty()){
            if (cezar.isSelected()) {
                Character character = keyString.getText().charAt(0);
                if(Character.isDigit(character))
                    initialText.setText(CeaserCipher.decrypt(encryptedText.getText(), Integer.valueOf(keyString.getText())));
                else {
                    // textFoeDecoder.setText("Ключ - слово!");
                    alert.setContentText("Ключ - слово!");
                    keyString.clear();
                    alert.showAndWait();
                }

            }else if(vigener.isSelected()){
                Character character = keyString.getText().charAt(0);
                if(Character.isLetter(character))
                    initialText.setText(VigenereCipher.decrypt(encryptedText.getText(),keyString.getText()));
                else {
                    alert.setContentText("Ключ - цифра!");
                    keyString.clear();
                    alert.showAndWait();
                }
            }
        }else {
            //  textFoeDecoder.setText("Не выбрано метод шифрования или не введен ключ!");
            alert.setContentText("Не введен ключ!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEncrypt(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Внимание!");
        if(!initialText.getText().isEmpty() && !keyString.getText().isEmpty()){
            if (cezar.isSelected()) {
                Character character = keyString.getText().charAt(0);
                if(Character.isDigit(character))
                    encryptedText.setText(CeaserCipher.encrypt(initialText.getText(), Integer.valueOf(keyString.getText())));
                else {
                    // textFoeDecoder.setText("Ключ - слово!");
                    alert.setContentText("Ключ - слово!");
                    keyString.clear();
                    alert.showAndWait();
                }

            }else if(vigener.isSelected()){
                Character character = keyString.getText().charAt(0);
                if(Character.isLetter(character))
                    encryptedText.setText(VigenereCipher.encrypt(initialText.getText(),keyString.getText()));
                else {
                    alert.setContentText("Ключ - цифра!");
                    keyString.clear();
                    alert.showAndWait();
                }
            }
        }else {
            //  textFoeDecoder.setText("Не выбрано метод шифрования или не введен ключ!");
            alert.setContentText("Не введен ключ!");
            alert.showAndWait();
        }
    }

    @FXML
    void openFile(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open file");
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files","*.txt"));
        File selectedFile = fc.showOpenDialog(null);


        if(selectedFile != null){
            initialText.setText(ReadSaveFile.readFile(selectedFile));
        }else{
            System.out.println("Nothing");
        }

    }

    @FXML
    void saveFile(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save File");
        fc.setInitialDirectory(new File("./"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files","*.txt"));
        File selectedFile = fc.showSaveDialog(null);

        if(selectedFile != null){
            ReadSaveFile.saveFile(selectedFile,encryptedText.getText());
        }else{
            System.out.println("Nothing");
        }
    }
}
