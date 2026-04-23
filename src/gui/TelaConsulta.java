package gui;

import dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modelo.Cliente;

import java.util.List;

public class TelaConsulta {

    @FXML
    private TextField buscaField;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn<Cliente, String> colNome;

    @FXML
    private TableColumn<Cliente, String> colCpf;

    @FXML
    private TableColumn<Cliente, Void> colAcoes;

    private ObservableList<Cliente> listaOriginal;

    @FXML
    private void buscar() {

        String filtro = buscaField.getText().toLowerCase();

        ObservableList<Cliente> filtrada = FXCollections.observableArrayList();

        for (Cliente c : listaOriginal) {
            if (c.getNome().toLowerCase().contains(filtro) ||
                    c.getCpf().contains(filtro)) {

                filtrada.add(c);
            }
        }

        tabelaClientes.setItems(filtrada);
    }

    @FXML
    private void irParaCadastro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/telaCadastro.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){


        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));


        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.listar();

        listaOriginal = FXCollections.observableArrayList(lista);
        tabelaClientes.setItems(listaOriginal);

        ObservableList<Cliente> dados = FXCollections.observableArrayList(lista);


        tabelaClientes.setItems(dados);

        colAcoes.setCellFactory(col -> new TableCell<>() {

            private final Button btnAlterar = new Button("Alterar");
            private final Button btnExcluir = new Button("Excluir");
            private final HBox box = new HBox(5, btnAlterar, btnExcluir);

            {
                btnAlterar.setOnAction(e -> {
                    try {
                        Cliente cliente = getTableView().getItems().get(getIndex());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaAlterar.fxml"));
                        Parent root = loader.load();


                        TelaAlterar controller = loader.getController();


                        controller.setCliente(cliente);

                        Stage stage = (Stage) getTableView().getScene().getWindow();
                        stage.setScene(new Scene(root));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                btnExcluir.setOnAction(e -> {
                    Cliente cliente = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(cliente);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });

    }
}

