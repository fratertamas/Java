/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Halacska-NB4
 */
public class FXMLDocumentController implements Initializable {
    
    private final int MIN = 1;
    private final int MAX = 95;
    private int sorsolandoSzamokDB = 5;
    private int megadandoSzamokDB = 5;
    private int[] sorsoltSzamok = new int[sorsolandoSzamokDB];
    private int[] userSzamok = new int[megadandoSzamokDB];
    
    //<editor-fold defaultstate="collapsed" desc="FXML items">
    @FXML
    private Pane basePane;
    @FXML
    private Pane alertPane;
    @FXML
    private Label alertMessage;
    @FXML
    private Button btnAlertPaneOK;
    
    @FXML
    private Label lbResult;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lb4;
    @FXML
    private Label lb5;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private TextField txt5;
//</editor-fold>
    
    @FXML
    private void handleBtnAlertPaneOK(ActionEvent event) {
        basePane.setDisable(false);
        basePane.setOpacity(1.0);
        alertPane.setVisible(false);
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        addRandomNumberInArray();
        
        lb1.setText(""+sorsoltSzamok[0]);
        lb2.setText(""+sorsoltSzamok[1]);
        lb3.setText(""+sorsoltSzamok[2]);
        lb4.setText(""+sorsoltSzamok[3]);
        lb5.setText(""+sorsoltSzamok[4]);

        if (addNumbersInUserArray() && checkUserNumbersRange() && checkUserNumberCount() ) {
            
            lbResult.setText("Az ön találatainak száma: "+checkResult());
            lbResult.setVisible(true);
        }
        
    }
    
    //generált sorsolt számok felvitele a tömbbe
    private int[] addRandomNumberInArray(){
        for (int i = 0; i < sorsoltSzamok.length; i++) {
            sorsoltSzamok[i] = getRandomNumber();
        }
        return sorsoltSzamok;
    }
    
    //Rekurzívan feltöltjük a sorsolt számok tömböt, ismétlődéseket elkerülve
    private int getRandomNumber(){
        int random = (int)(Math.random()*(MAX-MIN+1))+MIN; 
        for (int i = 0; i < sorsoltSzamok.length; i++) {
            if (sorsoltSzamok[i] == random) {
                return getRandomNumber();
            }
        }
        return  random;   
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private boolean addNumbersInUserArray() {
        try{
            userSzamok[0] = Integer.parseInt(txt1.getText());
            userSzamok[1] = Integer.parseInt(txt2.getText());    
            userSzamok[2] = Integer.parseInt(txt3.getText());
            userSzamok[3] = Integer.parseInt(txt4.getText());
            userSzamok[4] = Integer.parseInt(txt5.getText());
            return true;
        }catch(Exception ex){
            alertMSG("Nem számot adott meg!");
            return false;
        }
    }

    private boolean checkUserNumbersRange() {
        for (int i = 0; i < userSzamok.length; i++) {
            if (userSzamok[i] < MIN || userSzamok[i] > MAX) {
                alertMSG("Nem jó számot adott meg!\nA számoknak "+MIN+" és "+MAX+" közé kell esnie!");
                return false;
            }
        }
        return true;
    }

    private boolean checkUserNumberCount() {
        int db;
        for (int i = 0; i < userSzamok.length; i++) {
            db = 0;
            for (int j = 0; j < userSzamok.length; j++) {
                if (userSzamok[i] == userSzamok[j]) {
                    db++;
                }
            }
            if (db > 1) {
                alertMSG("Valamelyik számot többször adta meg!");
                return false;
            }
        }
        return true;
    }

    private void alertMSG(String message) {
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        alertMessage.setText(message);
        alertPane.setVisible(true);
    }

    private int checkResult() {
        int db = 0;
        for (int i = 0; i < userSzamok.length; i++) {
            for (int j = 0; j < sorsoltSzamok.length; j++) {
                if (userSzamok[i] == sorsoltSzamok[j]) {
                    db++;
                }
            }
        }
        return db;
    }
    
    
}
