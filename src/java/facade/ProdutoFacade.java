
package facade;


import entity.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import dao.ProdutoDao;


@Stateless
public class ProdutoFacade {
    
    ProdutoDao produtoDao = new ProdutoDao();
    List<ProdutoDao> lista;
    
    public List<Produto> listaProduto() {
        
        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> lista = new ArrayList<>();
        try {
            lista = produtoDao.listarProduto();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public void cadastrarProduto(Produto produto) {
        produto.setDescricao(produto.getDescricao().toUpperCase());
        
        try {
            produtoDao.inserir(produto);
           
        } catch (Exception e) {
           String mensagem = e.getMessage();
            System.out.println(mensagem);
            
        }
    }
    
    public void atualizarProduto(Produto selecionado) {
        selecionado.setDescricao(selecionado.getDescricao().toUpperCase());
        try {
            produtoDao.atualizar(selecionado);
           
            
        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
            
        }
        
    }
    
    public void deletarProduto(int id) {
        
        try {
            
            produtoDao.deletarProduto(id);
            
        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
            
        }
    }
    
    public Produto consultaProdutoId(int id) {
        Produto produto = new Produto();
        try {
            
            produto = produtoDao.consultarProduto(id);
           
            
        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }
        return produto;
        
    }
    
}
