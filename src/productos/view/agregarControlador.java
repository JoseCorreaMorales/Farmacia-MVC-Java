package productos.view;

import dialogos.Dialogos;
import farmacia.util.Fechas;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import productos.Productos;

public class agregarControlador {
    //variables globales
    public Stage escenario;
    @FXML
    private TextField nombre;
    @FXML
    private TextField precio;
    @FXML
    private DatePicker caducidad;
    @FXML
    private TextField cantidad;
    @FXML
    private TextField funcion;
    @FXML
    private TextField dosis;
    @FXML
    private TextField formula;
    @FXML
    private TextField precauciones;
    @FXML
    private TextField reaccion;
    //funcion para inicializar
    @FXML
    private void initialize() {
        precio.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Float.parseFloat(newValue);
            } catch (Exception e) {
                if (newValue.equals("")) oldValue = "";
                precio.setText(oldValue);
            }
        });
        cantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Float.parseFloat(newValue);
            } catch (Exception e) {
                if (newValue.equals("")) oldValue = "";
                cantidad.setText(oldValue);
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
        bd.Insertar("Productos",
                new String[]{"Nombre", "Precio", "Caducidad", "Cantidad", "Funcion", "Dosis", "Formula", "Precauciones", "Reaccion"},
                new String[]{nombre.getText(), precio.getText(), Fechas.Conversion(caducidad.getValue()), cantidad.getText(),  funcion.getText(), dosis.getText(), formula.getText(), precauciones.getText(), reaccion.getText()});
        bd.Cerrar();
        Productos.Mostrar();
        new Dialogos(escenario).Mensaje("Productos", "Producto Agregado");
        escenario.close();
    }

    //funcion para validar
    private boolean Validar(){
        if (nombre.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta el Nombre");
            return false;
        }
        if (precio.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta el Precio");
            return false;
        }

        if (caducidad.getValue() == null){
            new Dialogos(escenario).Error("Validacion", "Falta la Caducidad");
            return false;
        }
        if (cantidad.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta la Cantidad");
            return false;
        }
        if (funcion.getText().equals("")){
            new Dialogos(escenario).Error("Validacion", "Falta la Funcion");
            return false;
        }
        return true;
    }
}
