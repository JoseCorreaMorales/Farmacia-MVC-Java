package ventas.view;

import dialogos.Dialogos;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ventas.Ventas;
import ventas.model.Producto;

import java.util.ArrayList;

public class productosControlador {
    //variables globales
    public Stage escenario;
    @FXML
    private ComboBox<String> nombre;
    @FXML
    private TextField cantidad;

    private ArrayList<Float> precios = new ArrayList<>();

    //funcion para inicializar la ventana
    @FXML
    private void initialize(){
        try {
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"}, "Productos", "", "Nombre");
            while (bd.Resultado.next()) {
                nombre.getItems().add(bd.Resultado.getString("Nombre"));
                precios.add(bd.Resultado.getFloat("Precio"));
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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
    //funcion para boton guardar
    @FXML
    private void Guardar(){
        if (!Validar()) return;
        Producto nuevo = new Producto();
        nuevo.no.setValue((Ventas.productos.size() + 1) + "");
        nuevo.nombre.setValue(nombre.getValue());
        nuevo.precio.setValue(precios.get(nombre.getSelectionModel().getSelectedIndex()).toString());
        nuevo.cantidad.setValue(cantidad.getText());
        nuevo.subtotal.setValue((precios.get(nombre.getSelectionModel().getSelectedIndex()) *
                Float.parseFloat(cantidad.getText())) + "");
         Ventas.productos.add(nuevo);
    escenario.close();
    }
    //funcion para validar
    private boolean Validar(){
        if (nombre.getValue() == null){
        new Dialogos(escenario).Error("Validacion", "Falta el Nombre");
        return false;
        }
        if (cantidad.getText().equals("")) {
            new Dialogos(escenario).Error("Validacion", "Falta la Cantidad");
            return false;
        }
        return true;
    }
}