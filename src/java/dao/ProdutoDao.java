package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entity.Produto;


public class ProdutoDao {

    private final String stmtInserirProduto = "INSERT INTO PRODUTO(DESCRICAO) VALUES(?) ";
    private final String stmtConsultarProduto = "SELECT ID,DESCRICAO FROM PRODUTO WHERE id = ?";
    private final String stmtListarProdutos = "SELECT ID,DESCRICAO FROM PRODUTO";
    private final String stmtExcluirProduto = "DELETE FROM PRODUTO WHERE ID = ?";
    private final String stmtAtualizarProduto = "UPDATE PRODUTO SET DESCRICAO =? WHERE ID=?";

    public void inserir(Produto produto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserirProduto, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, produto.getDescricao());
            if (produto.getDescricao().length() < 3) {
                throw new RuntimeException("Descrição muito pequena");
            }
            stmt.executeUpdate();
            int idProdutoGravado = lerIdProduto(stmt);
            produto.setId(idProdutoGravado);

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir Produto no banco de dados. Origem=" + ex.getMessage());
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

    private int lerIdProduto(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    public Produto consultarProduto(Integer id) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produtoEncontrado;
        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultarProduto);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            rs.next();

            produtoEncontrado = new Produto();
            produtoEncontrado.setId(id);
            produtoEncontrado.setDescricao(rs.getString("descricao"));
            return produtoEncontrado;

        } catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o produto esperado");
            
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

    public List<Produto> listarProduto() throws ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaProduto = new ArrayList<Produto>();

        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListarProdutos);

            rs = stmt.executeQuery();
            while (rs.next()) {

                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));

                listaProduto.add(produto);

            }
            listaProduto = ordenarProduto(listaProduto);
            return listaProduto;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar Produto no banco de dados. Origem=" + ex.getMessage());
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

    public void deletarProduto(Integer id) throws ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtExcluirProduto);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Não encontrei Produto para excluir");
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

    private List<Produto> ordenarProduto(List<Produto> listaProduto) {
        Collections.sort(listaProduto, new java.util.Comparator<Produto>() {
            @Override
            public int compare(Produto arg0, Produto arg1) {
                String nomeProduto1 = arg0.getDescricao();
                String nomeProduto2 = arg1.getDescricao();
                int retorno = nomeProduto1.compareToIgnoreCase(nomeProduto2);
                return retorno;
            }
        }
        );

        return listaProduto;
    }

    public void atualizar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizarProduto);
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getId());
            if (produto.getDescricao().length() < 3) {
                throw new RuntimeException("Descrição muito pequena");
            }
            stmt.executeUpdate();
           
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar Produto");
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
