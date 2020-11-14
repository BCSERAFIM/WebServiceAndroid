package facade;

import entity.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import dao.ClienteDAO;
import java.io.Serializable;

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
public class ClienteFacade implements Serializable{

    ClienteDAO clienteDAO = new ClienteDAO();
    List<Cliente> lista;

    public List<Cliente> listaCliente() {

        lista = new ArrayList<>();
        try {
            lista = clienteDAO.listarCliente();
        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }
        return lista;

    }

    public void cadastraCliente(Cliente cliente) {

        cliente.setCpf(cliente.getCpf().replace(".", ""));

        cliente.setNome(cliente.getNome().toUpperCase());
        cliente.setSobreNome(cliente.getSobreNome().toUpperCase());

        try {
            clienteDAO.inserir(cliente);

        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }
    }

    public void atualizarCliente(Cliente selecionado) {
        selecionado.setNome(selecionado.getNome().toUpperCase());
        selecionado.setSobreNome(selecionado.getSobreNome().toUpperCase());

        try {
            clienteDAO.atualizarCliente(selecionado);

        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }

    }

    public void deletarCliente(Integer id) {

        try {
            clienteDAO.deletarCliente(id);

        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }

    }

    public Cliente consultaCpf(Integer id) {
        Cliente cliente = new Cliente();
        try {
            cliente = clienteDAO.consultarClientePeloCpfCnpj(id);
            return cliente;
        } catch (Exception e) {
            String mensagem = e.getMessage();
            return null;
        }

    }

}
