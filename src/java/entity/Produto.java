
package entity;

import java.io.Serializable;


public class Produto implements Serializable {

    private Integer id;
    private String descricao;

    
    
    public Produto() {
    }

    public Produto( String descricao) {
        this.descricao = descricao;
    }
       
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

}
