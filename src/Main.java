import dao.ClienteDAO;
import factory.ConnectionFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("/view/TelaCadastro.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("Cadastro");
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
      launch();

    }
}