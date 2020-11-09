package dao;

import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao {

    private final String procurausuario = "SELECT LOGIN,SENHA FROM USUARIO WHERE LOGIN=? AND SENHA=?";

    public Usuario consultarUsuario(Integer login, String senha) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(procurausuario);
            stmt.setInt(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            rs.next();

            Usuario encontrado = new Usuario();
            encontrado.setLogin(rs.getInt("LOGIN"));
            encontrado.setSenha(rs.getString("SENHA"));

            return encontrado;

        } catch (SQLException ex) {
            return null;

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
