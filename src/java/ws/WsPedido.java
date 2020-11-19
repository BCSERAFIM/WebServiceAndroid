package ws;

import entity.Cliente;
import entity.Pedido;
import facade.ClienteFacade;
import facade.PedidoFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;


@Path("pedido")

public class WsPedido {

    private PedidoFacade pedidoFacade = new PedidoFacade();

    @Context
    private UriInfo context;

    public WsPedido() {
    }

    @GET
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
     /*Traz uma lista de pedido sem intens, consultando pelo id do cliente*/
    public List<Pedido> getPedidos() {
        List<Pedido> lista = pedidoFacade.consultarPedido();
        return lista;
    }
  
    @GET
    @Path("/{id}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
     /*Traz uma lista de pedido sem intens, consultando pelo id do cliente*/
    public List<Pedido> getPedidoIdCliente(@PathParam("id") Integer id) {
        List<Pedido> lista = pedidoFacade.consultarPedido(id);
        return lista;
    }
    
    @GET
    @Path("itens/{id}")
    /*traz um único pedido com itens consultando pelo id do Pedido*/
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Pedido getPedidoItens(@PathParam("id") Integer id) {
        Pedido pedido = new Pedido();
         pedido=pedidoFacade.consultaPedidoItens(id);
        return pedido;
    }
    
    
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void inserirPedido(Pedido pedido) {

        pedidoFacade.inserirPedido(pedido);

    }

   
   
}
