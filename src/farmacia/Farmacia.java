package farmacia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Farmacia extends Application{
    //variables globales
    public static Stage escenario;
    public static BorderPane raiz;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //inicializar el escenario
        escenario = primaryStage;
        escenario.setTitle("Farmacia");
        //cargar la raiz
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Farmacia.class.getResource("view/raiz.fxml"));
            raiz = cargador.load();
            Scene escena = new Scene(raiz);
            escenario.setScene(escena);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        escenario.show();
    }
    public static void main(String [] arg){
        launch(arg);
    }
}
