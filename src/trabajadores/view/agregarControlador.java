package trabajadores.view;

import dialogos.Dialogos;
import farmacia.util.Fechas;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import trabajadores.Trabajadores;


public class agregarControlador {
    //variables globales
    public Stage escenario;
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<String> genero;
    @FXML
    private TextField edad;
    @FXML
    private ComboBox<String> estudios;
    @FXML
    private TextField capacidad;
    @FXML
    private TextField domicilio;
    @FXML
    private TextField telefono;
    @FXML
    private ComboBox<String> responsable;
    @FXML
    private ComboBox<String> honesto;
    @FXML
    private ComboBox<String> comportamiento;

    String[] generos ={"Masculino", "Femenino"};

    String[] estudio ={"Si", "No"};

    String[] responsables ={"Si", "No"};

    String[] honestidad ={"Buena", "Regular", "Mala"};

    String[] comportamientos ={"Bueno", "Regular", "Malo"};

    //funcion para inicializar
    @FXML
    private void initialize() {
        genero.getItems().addAll(generos);
        estudios.getItems().addAll(estudio);
        responsable.getItems().addAll(responsables);
        honesto.getItems().addAll(honestidad);
        comportamiento.getItems().addAll(comportamientos);
        edad.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Float.parseFloat(newValue);
            } catch (Exception e) {
                if (newValue.equals("")) oldValue = "";
                edad.setText(oldValue);
            }
        });
        telefono.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Float.parseFloat(newValue);
            } catch (Exception e) {
                if (newValue.equals("")) oldValue = "";
                telefono.setText(oldValue);
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
        bd.Insertar("Trabajadores",
                new String[]{"Nombre", "Genero", "Edad", "Estudios", "Capacidad", "Domicilio", "Telefono", "Responsable", "Honesto", "Comportamiento"},
                new String[]{nombre.getText(), genero.getValue(), edad.getText(), estudios.getValue(),  capacidad.getText(), domicilio.getText(), telefono.getText(), responsable.getValue(), honesto.getValue(), comportamiento.getValue()});
        bd.Cerrar();
        Trabajadores.Mostrar();
        new Dialogos(escenario).Mensaje("Trabajadores", "Trabajador Agregado");
        escenario.close();
    }

    //funcion para validar
    private boolean Validar(){
        if (nombre.getText().equals("")){
        new Dialogos(escenario).Error("Validacion", "Falta el Nombre");
        return false;
        }
        if (edad.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta la Edad");
            return false;
        }
        if (estudios.getValue() == null){
            new Dialogos(escenario).Error("Validacion", "Falta los Estudios");
            return false;
        }
        if (domicilio.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta el Domicilio");
            return false;
        }
        if (telefono.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta el Telefono");
            return false;
        }
        return true;
    }
}
