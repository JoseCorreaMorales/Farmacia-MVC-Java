package clientes.view;


import clientes.Clientes;
import dialogos.Dialogos;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregarControlador {
    //variables globales
    public Stage escenario;
    @FXML
    private TextField nombre;
    @FXML
    private TextField economia;
    @FXML
    private TextField enfermedades;
    @FXML
    private TextField alergias;
    @FXML
    private TextField edad;
    @FXML
    private TextField sintomas;
    @FXML
    private ComboBox<String> embarazo;

    //Funcion para guardar

    String[] embarazos = {"Si", "No"};

    //funcion para inicializar
    @FXML
    private void initialize(){
        embarazo.getItems().addAll(embarazos);
        edad.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Float.parseFloat(newValue);
            } catch (Exception e) {
                if (newValue.equals("")) oldValue = "";
                edad.setText(oldValue);
            }
        });
    }
    //funcion del boton cancelar
    @FXML
    private void Cancelar(){
        escenario.close();
    }

    //funcion del boton guardar
    @FXML
    private void Guardar(){
        if (!Validar()) return;
        SQLite bd = new SQLite();
        bd.Abrir();
        bd.Insertar("Clientes",
                new String[]{"Nombre", "Economia", "Enfermedades", "Alergias", "Edad", "Sintomas", "Embarazo"},
                new String[]{nombre.getText(), economia.getText(), enfermedades.getText(), alergias.getText(), edad.getText(), sintomas.getText(), embarazo.getValue()});
        bd.Cerrar();
        Clientes.Mostrar();
        new Dialogos(escenario).Mensaje("Clientes", "Cliente Agregado");
        escenario.close();
    }

    //funcion para validar
    private boolean Validar(){
        if (nombre.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta el Nombre");
            return false;
        }
        if (enfermedades.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta las Enfermedades");
            return false;
        }
        if (alergias.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta las Alergias");
            return false;
        }
        if (sintomas.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta los Sintomas");
            return false;
        }
        if (embarazo.getValue() == null){
            new Dialogos(escenario).Error("Validacion", "Falta el Embarazo");
            return false;
        }

        return true;
    }

}
