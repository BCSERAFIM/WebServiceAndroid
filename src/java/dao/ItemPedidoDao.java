package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.ItemPedido;
import entity.Produto;
import facade.ProdutoFacade;

public class ItemPedidoDao {


    private final String sqlListarItens = "SELECT ID_PRODUTO,QTDADE FROM ITEM_DO_PEDIDO WHERE ID_PEDIDO= ?";

    public List<ItemPedido> consultarItemDoPedido(int id) throws ClassNotFoundException {

      PreparedStatement ps = null;
      Connection  con = null;
        
        
        List<ItemPedido> listaitemencontrado = new ArrayList<ItemPedido>();
        Produto produto=new Produto();
        try {

           con = conexao.ConnectionFactory.getConnection();

            ps = con.prepareStatement(sqlListarItens);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();
           

            while (resultado.next()) {
                
              ProdutoFacade produtoFacade = new ProdutoFacade();
              produto=produtoFacade.consultaProdutoId(resultado.getInt("id_produto"));
                
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setProduto(produto);
                itemPedido.setQuantidade(resultado.getInt("qtdade"));
                listaitemencontrado.add(itemPedido);
          
            }
            
            return listaitemencontrado;

        } catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o Pedido esperado");
        } finally {
            try {
                ps.close();
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
