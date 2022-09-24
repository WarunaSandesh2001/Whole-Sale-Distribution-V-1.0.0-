package util;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static Object validateForNormalTextFields(LinkedHashMap<TextField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-text-fill: red");
                    //textFieldKey.setStyle("-fx-border-color: darkred");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: green");
            //textFieldKey.setStyle("-fx-border-color: darkred");
        }
        btn.setDisable(false);
        return true;
    }

    public static Object validateForNormalTextFields2(LinkedHashMap<TextField, Pattern> map, Button btn1, Button btn2) {
        btn1.setDisable(true);
        btn2.setDisable(true);
        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-text-fill: red");
                    //textFieldKey.setStyle("-fx-border-color: darkred");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: green");
            //textFieldKey.setStyle("-fx-border-color: darkred");
        }
        btn1.setDisable(false);
        btn2.setDisable(false);
        return true;
    }
}
