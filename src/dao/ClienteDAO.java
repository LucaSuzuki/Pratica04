package dao;

import factory.ConnectionFactory;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome,cpf,data_nascimento,telefone,endereco,bairro,cidade,estado,cep) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, cliente.getEstado());
            stmt.setString(9, cliente.getCep());
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirPorId(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));

                clientes.add(cliente);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
    }
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, data_nascimento=?, telefone=?, endereco=?, bairro=?, cidade=?, estado=?, cep=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, cliente.getEstado());
            stmt.setString(9, cliente.getCep());
            stmt.setInt(10, cliente.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
