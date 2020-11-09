
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
            FacesMessage msg = new FacesMessage("Produto inserido com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
    }
    
    public void atualizarProduto(Produto selecionado) {
        selecionado.setDescricao(selecionado.getDescricao().toUpperCase());
        try {
            produtoDao.atualizar(selecionado);
            FacesMessage msg = new FacesMessage("Produto atualizado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
        
    }
    
    public void deletarProduto(Produto selecionado) {
        
        try {
            
            produtoDao.deletarProduto(selecionado.getId());
            FacesMessage msg = new FacesMessage("Produto deletado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
    }
    
    public Produto consultaProdutoId(int id) {
        Produto produto = new Produto();
        try {
            
            produto = produtoDao.consultarProduto(id);
            if (produto == null) {
                FacesMessage msg = new FacesMessage("Produto Não encontrado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
            } else {
                FacesMessage msg = new FacesMessage("Produto encontrado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
            }
            
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return produto;
        
    }
    
}
