package facade;


import entity.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import dao.ClienteDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos Eduardo
 */
@Stateless
public class ClienteFacade {

    ClienteDAO clienteDAO = new ClienteDAO();
    List<Cliente> lista;

    public List<Cliente> listaCliente() {

        lista = new ArrayList<>();
        try {
            lista = clienteDAO.listarCliente();
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return lista;

    }
    
   

    public void cadastraCliente(Cliente cliente) {

        cliente.setCpf(cliente.getCpf().replace(".", ""));
        

        cliente.setNome(cliente.getNome().toUpperCase());
        cliente.setSobreNome(cliente.getSobreNome().toUpperCase());

        try {
            clienteDAO.inserir(cliente);
            FacesMessage msg = new FacesMessage("Cliente inserido com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void atualizarCliente(Cliente selecionado) {
        selecionado.setNome(selecionado.getNome().toUpperCase());
        selecionado.setSobreNome(selecionado.getSobreNome().toUpperCase());
       
        try {
            clienteDAO.atualizarCliente(selecionado);

            FacesMessage msg = new FacesMessage("Cliente atualizado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void deletarCliente(Cliente selecionado) {

        try {
            clienteDAO.deletarCliente(selecionado.getId());

            FacesMessage msg = new FacesMessage("Cliente deletado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void consultaCpf(String cpf) {
        Cliente cliente = new Cliente();
        try {
            cliente = clienteDAO.consultarClientePeloCpfCnpj(cpf);
            FacesMessage msg = new FacesMessage("Cliente encontrado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

}
