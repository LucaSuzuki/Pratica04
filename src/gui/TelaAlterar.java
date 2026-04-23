package gui;

import dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cliente;

public class TelaAlterar {
    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private DatePicker dataNascimentoField;
    @FXML private TextField telefoneField;
    @FXML private TextField enderecoField;
    @FXML private TextField bairroField;
    @FXML private TextField cidadeField;
    @FXML private TextField estadoField;
    @FXML private TextField cepField;

    private Cliente clienteAtual;

    public void setCliente(Cliente cliente) {
        this.clienteAtual = cliente;

        nomeField.setText(cliente.getNome());
        cpfField.setText(cliente.getCpf());
        telefoneField.setText(cliente.getTelefone());
        enderecoField.setText(cliente.getEndereco());
        bairroField.setText(cliente.getBairro());
        cidadeField.setText(cliente.getCidade());
        estadoField.setText(cliente.getEstado());
        cepField.setText(cliente.getCep());
        dataNascimentoField.setValue(cliente.getDataNascimento());
    }

    @FXML
    private void salvar() {

        ClienteDAO dao = new ClienteDAO();

        clienteAtual.setNome(nomeField.getText());
        clienteAtual.setCpf(cpfField.getText());
        clienteAtual.setTelefone(telefoneField.getText());
        clienteAtual.setEndereco(enderecoField.getText());
        clienteAtual.setBairro(bairroField.getText());
        clienteAtual.setCidade(cidadeField.getText());
        clienteAtual.setEstado(estadoField.getText());
        clienteAtual.setCep(cepField.getText());
        clienteAtual.setDataNascimento(dataNascimentoField.getValue());

        dao.atualizar(clienteAtual);

        System.out.println("Cliente atualizado!");
    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/telaConsulta.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



