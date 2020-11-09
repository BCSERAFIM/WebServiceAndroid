package dao;


import entity.Cliente;
import entity.ItemPedido;
import entity.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class PedidoDao {

    private final String stmtInserirPedido = "INSERT INTO PEDIDO (DATA,ID_CLIENTE) VALUES (?,?)";
    private final String stmtInserirItem_do_pedido = "INSERT INTO ORCAMENTO.ITEM_DO_PEDIDO "
            + " (id_pedido,id_produto,qtdade)"
            + " values (?,?,?)";

    private final String stmtListarPedidoPorClienteMB = "SELECT ID,DATA,ID_CLIENTE FROM PEDIDO WHERE ID_CLIENTE = ? ";
    private final String stmtExcluirPedido = "DELETE FROM PEDIDO WHERE ID = ? ";

    public void inserir(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserirPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            stmt.setTimestamp(1, (Timestamp) date);
            stmt.setInt(2, pedido.getCliente().getId());
            stmt.executeUpdate();
            PreparedStatement stmt2;

            for (int i = 0; i < pedido.getItemPedido().size(); i++) {
                stmt2 = con.prepareStatement(stmtInserirItem_do_pedido);
                int idPedidoGravado = lerIdPedido(stmt);
                stmt2.setInt(1, idPedidoGravado);

                stmt2.setInt(2, pedido.getItemPedido().get(i).getProduto().getId());
                stmt2.setInt(3, pedido.getItemPedido().get(i).getQuantidade());
                stmt2.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir Pedido no banco de dados. Origem=" + ex.getMessage());
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

    private int lerIdPedido(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    public List<Pedido> listarPedidoPorClienteMB(Cliente cliente) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;

        List<Pedido> listaPedido = new ArrayList<>();

        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListarPedidoPorClienteMB);
            stmt.setLong(1, cliente.getId());
            ResultSet resultado = stmt.executeQuery();
            

            while (resultado.next()) {
                Pedido pedidoDoClienteMBEncontrado = new Pedido();
                pedidoDoClienteMBEncontrado.setCliente(cliente);
                pedidoDoClienteMBEncontrado.setData(resultado.getDate("data"));
                pedidoDoClienteMBEncontrado.setId(resultado.getInt("id"));
                listaPedido.add(pedidoDoClienteMBEncontrado);

            }
            
            
            

            return listaPedido;

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

    public List<ItemPedido> itensPedidoCliente(int idPedido) throws ClassNotFoundException {
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
        List<ItemPedido> listaItens;
        listaItens = itemPedidoDao.consultarItemDoPedido(idPedido);
        return listaItens;
    }

}
