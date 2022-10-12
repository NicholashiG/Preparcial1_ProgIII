package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    /*
     * Ejercicio #1:
     * 1. Ingresar los datos de estudiantes: código, nombre y tres notas y que almacene estos datos en un archivo de texto, registrar en un log el registro de la operación.
     * 2. Buscar y mostrar los datos de un estudiante en particular almacenado en el archivo.
     *
     * Ejercicio #2:
     * 1. Ingresar los datos de los programas: código, nombre y modalidad, adscritos a la universidad del Quindío y almacene estos datos en un archivo serializado xml.
     * Nota: La modalidad Distancia y Presencial se deben cargar desde un archivo de propiedades.
     * 2. Buscar y mostrar los datos de un programa en particular.
     */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
