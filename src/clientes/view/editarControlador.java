package clientes.view;

import clientes.Clientes;
import clientes.model.Cliente;
import dialogos.Dialogos;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editarControlador {
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

    public Cliente seleccionado;

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
        bd.Modificar("Clientes",
                new String[]{"Nombre", "Economia", "Enfermedades", "Alergias", "Edad", "Sintomas", "Embarazo"},
                new String[]{nombre.getText(), economia.getText(), enfermedades.getText(), alergias.getText(), edad.getText(), sintomas.getText(), embarazo.getValue()},
                "Clave=" + seleccionado.clave.get());
        bd.Cerrar();
        Clientes.Mostrar();
        new Dialogos(escenario).Mensaje("Clientes", "Cliente Editado");
        escenario.close();
    }

    //funcion para validar
    private boolean Validar(){
        if (enfermedades.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta las Enfermedades");
            return false;
        }
        if (nombre.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta el Nombre");
            return false;
        }
        return true;
    }
    //funcion para llenar el formulario
    public void Llenar(){
        nombre.setText(seleccionado.nombre.get());
        economia.setText(seleccionado.economia.get());
        enfermedades.setText(seleccionado.enfermedades.get());
        nombre.setText(seleccionado.nombre.get());
        alergias.setText(seleccionado.alergias.get());
        edad.setText(seleccionado.edad.get());
        sintomas.setText(seleccionado.sintomas.get());
        embarazo.setValue(seleccionado.embarazo.get());
    }
}
