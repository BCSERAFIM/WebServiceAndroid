
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entity.Cliente;


public class ClienteDAO {

    private final String stmtInserirCliente = "INSERT INTO ORCAMENTO.CLIENTE(CPF,NOME,SOBRENOME) VALUES(?,?,?)";
    private final String stmtConsultarCliente = "SELECT ID,CPF,NOME,SOBRENOME FROM ORCAMENTO.CLIENTE WHERE ID = ? ";
    private final String stmtConsultarClientePeloid = "SELECT ID,CPF,NOME,SOBRENOME FROM ORCAMENTO.CLIENTE WHERE ID = ? ";

    private final String stmtListarClientes = "SELECT ID,CPF,NOME,SOBRENOME FROM orcamento.cliente";
    private final String stmtExcluirCliente = "DELETE FROM ORCAMENTO.CLIENTE WHERE ID = ? ";
    private final String stmtAtualizarCliente = "UPDATE CLIENTE SET NOME=?,SOBRENOME=? WHERE ID=?";

    public void inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserirCliente, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobreNome());
            stmt.executeUpdate();
            int idClienteGravado = lerIdAutor(stmt);
            cliente.setId(idClienteGravado);

        } catch (SQLException ex) {
            throw new RuntimeException("Cliente já existe no banco de dados ou erro de conexão");
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    private int lerIdAutor(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    public Cliente consultarCliente(int id) throws ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteEncontrado;
        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultarCliente);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            rs.next();

            clienteEncontrado = new Cliente();
            clienteEncontrado.setId(rs.getInt("id"));
            clienteEncontrado.setCpf(rs.getString("cpf"));
            clienteEncontrado.setNome(rs.getString("nome"));
            clienteEncontrado.setSobreNome(rs.getString("sobrenome"));

            return clienteEncontrado;

        } catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o cliente esperado" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public Cliente consultarClientePeloCpfCnpj(Integer id) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteEncontrado;
        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultarClientePeloid);
            stmt.setInt(1, id);
            

            rs = stmt.executeQuery();
            rs.next();

            clienteEncontrado = new Cliente();
            clienteEncontrado.setId(rs.getInt("id"));
            clienteEncontrado.setCpf(rs.getString("cpf"));
            clienteEncontrado.setNome(rs.getString("nome"));
            clienteEncontrado.setSobreNome(rs.getString("sobrenome"));
            return clienteEncontrado;

        } catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o cliente esperado");
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public List<Cliente> listarCliente() throws ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> listaCliente = new ArrayList<Cliente>();

        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListarClientes);

            rs = stmt.executeQuery();
            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobreNome(rs.getString("sobrenome"));
                listaCliente.add(cliente);

            }
            listaCliente = ordenarCliente(listaCliente);
            return listaCliente;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar Clientes no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    public void deletarCliente(int id) throws ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExcluirCliente);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Você tentou deletar um Cliente que possui pedidos");
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    private List<Cliente> ordenarCliente(List<Cliente> listaCliente) {
        Collections.sort(listaCliente, new java.util.Comparator<Cliente>() {
            @Override
            public int compare(Cliente arg0, Cliente arg1) {
                String nomeAutor1 = arg0.getNome();
                String nomeAutor2 = arg1.getNome();
                int retorno = nomeAutor1.compareToIgnoreCase(nomeAutor2);
                return retorno;
            }
        }
        );

        return listaCliente;
    }

    public void atualizarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizarCliente);

           
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobreNome());
            stmt.setInt(3, cliente.getId());
                       
            stmt.executeUpdate();
            if (stmt.executeUpdate() == 0) {
                throw new RuntimeException("Não encontrei Cliente para Atualizar");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível alterar esse cliente");
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

}
