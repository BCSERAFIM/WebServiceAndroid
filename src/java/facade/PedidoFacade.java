/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Cliente;
import entity.ItemPedido;
import entity.Pedido;
import entity.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import dao.ClienteDAO;
import dao.ItemPedidoDao;
import dao.PedidoDao;

/**
 *
 * @author Lwtech
 */
@Stateless
public class PedidoFacade {

    private final ProdutoFacade produtoFacade = new ProdutoFacade();
    private final ClienteDAO clienteDao = new ClienteDAO();
    private final PedidoDao pedidoDao = new PedidoDao();
    private final ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
    private final Cliente cliente = new Cliente();
    private Integer quantidade;

    public List<Pedido> consultarPedido(Integer id) {
        List<Pedido> pedidos = new ArrayList<>();
        try {

            pedidos = pedidoDao.listarPedidoPorClienteMB(id);
            return pedidos;

        } catch (Exception e) {
            String mensagem = e.getMessage();
            return null;
        }

    }

    public List<ItemPedido> consultaItensPedido(Integer id) {
        List<ItemPedido> itens = new ArrayList<>();

        try {
            itens = itemPedidoDao.consultarItemDoPedido(id);
            return itens;

        } catch (Exception e) {
            String mensagem = e.getMessage();
            return null;
        }

    }
    
    public Pedido consultaPedidoItens(Integer id) {
        Pedido pedido = new Pedido();

        try {
            pedido = pedidoDao.PedidoItens(id);
            return pedido;

        } catch (Exception e) {
            String mensagem = e.getMessage();
            return null;
        }

    }

    public List<ItemPedido> inserirPedido(Pedido pedido) {
        List<ItemPedido> itens = new ArrayList<>();

        try {
            if (pedido.getItemPedido().isEmpty()) {
                FacesMessage msg = new FacesMessage("Coloque pelo menos um item");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else {

                pedidoDao.inserir(pedido);
                FacesMessage msg = new FacesMessage("Pedido inserido com sucesso");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return itens;

    }

    public List<ItemPedido> novoItem(List<ItemPedido> itemPedidos, Produto novoProduto, Integer quantidade) {

        ItemPedido novoItem = new ItemPedido();

        if (novoProduto != null && quantidade != null) {

            int contador = itemPedidos.size();
            int achei = 0;
            if (contador != 0) {
                for (int i = 0; i < contador; i++) {
                    if (itemPedidos.get(i).getProduto().getId() == novoProduto.getId()) {
                        achei = 1;
                        itemPedidos.get(i).setQuantidade(itemPedidos.get(i).getQuantidade() + quantidade);

                    }

                }
                if (achei != 1) {

                    novoItem.setQuantidade(quantidade);
                    novoItem.setProduto(novoProduto);
                    itemPedidos.add(novoItem);

                }
            } else {
                novoItem.setQuantidade(quantidade);
                novoItem.setProduto(novoProduto);
                itemPedidos.add(novoItem);
            }

            FacesMessage msg = new FacesMessage("Item no carrinho");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {

            FacesMessage msg = new FacesMessage("Item vazio");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        return itemPedidos;

    }

    public List<String> completeTextCliente(String query) throws ClassNotFoundException {
        List<String> results = new ArrayList<>();
        List<Cliente> clientes = clienteDao.listarCliente();

        for (Cliente cliente : clientes) {
            String procura = cliente.getId() + " " + cliente.getCpf() + " " + cliente.getNome() + " " + cliente.getSobreNome();
            if (procura.toLowerCase().contains(query.toLowerCase())) {
                results.add(cliente.getId() + " " + cliente.getCpf() + " " + cliente.getNome() + " " + cliente.getSobreNome());
            }
        }

        return results;
    }

    public Cliente clienteSelecionar(String nome) throws ClassNotFoundException {
        Cliente clienteSelecionado = new Cliente();
        List<Cliente> clientes = clienteDao.listarCliente();
        for (Cliente cliente : clientes) {
            String procura = cliente.getId() + " " + cliente.getCpf() + " " + cliente.getNome() + " " + cliente.getSobreNome();
            if (procura.contentEquals(nome)) {
                clienteSelecionado = cliente;
            }
        }
        return clienteSelecionado;
        //aqui coloco o cliente na session
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clienteescolhido", selecionado);

    }

    public List<String> completeTextProduto(String query) {
        List<String> results = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        produtos = produtoFacade.listaProduto();

        for (Produto produto : produtos) {
            String procura = produto.getId() + " " + produto.getDescricao();
            if (procura.toLowerCase().contains(query.toLowerCase())) {
                results.add(produto.getId() + " " + produto.getDescricao());
            }
        }

        return results;
    }

    public Produto produtoSelecionar(String nome) throws ClassNotFoundException {

        List<Produto> produtos = new ArrayList<>();
        Produto produtoEncontrado = new Produto();
        produtos = produtoFacade.listaProduto();
        for (Produto produto : produtos) {
            String procura = produto.getId() + " " + produto.getDescricao();
            if (procura.contentEquals(nome)) {
                produtoEncontrado = produto;
            }
        }

        return produtoEncontrado;
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produtoselecionado", selecionado);
    }

}
