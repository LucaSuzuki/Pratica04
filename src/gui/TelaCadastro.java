package gui;


import dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cliente;

import java.time.LocalDate;


public class TelaCadastro {
    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private DatePicker dataNascimentoField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField bairroField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField estadoField;

    @FXML
    private TextField cepField;

    @FXML
    private void cadastrar() {
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setNome(nomeField.getText());
        cliente.setCpf(cpfField.getText());
        cliente.setDataNascimento(dataNascimentoField.getValue());
        cliente.setTelefone(telefoneField.getText());
        cliente.setEndereco(enderecoField.getText());
        cliente.setBairro(bairroField.getText());
        cliente.setCidade(cidadeField.getText());
        cliente.setEstado(estadoField.getText());
        cliente.setCep(cepField.getText());
        dao.adiciona(cliente);
        limparCampos();
        showMessage();
    }



    private void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Cadastro realizado com sucesso!");

        alert.showAndWait();
    }

    private void limparCampos() {
        nomeField.clear();
        cpfField.clear();
        telefoneField.clear();
        enderecoField.clear();
        bairroField.clear();
        cidadeField.clear();
        estadoField.clear();
        cepField.clear();

        dataNascimentoField.setValue(null);}

    @FXML
    private void alterar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaAlterar.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void consultar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaConsulta.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excluir(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaConsulta.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
