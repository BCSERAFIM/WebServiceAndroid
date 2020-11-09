package dao;

import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private final String inserirUsuario = "INSERT INTO USUARIO(LOGIN,SENHA) VALUES(?,?) ";
    private final String atualizarUsuario = "UPDATE USUARIO SET SENHA=? WHERE LOGIN=? ";
    private final String excluirUsuario = "DELETE FROM USUARIO WHERE LOGIN = ? ";
    private final String listarUsuario = "SELECT LOGIN,SENHA FROM USUARIO";
    private final String consultaUsuario = "SELECT LOGIN,SENHA FROM USUARIO WHERE LOGIN=?";

    public void inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(inserirUsuario, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Usuário já existe");
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

    private int lerIdUsuario(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    public void atualizarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(atualizarUsuario);
            stmt.setString(1, usuario.getSenha());
            stmt.setInt(2, usuario.getLogin());
            stmt.executeUpdate();
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

    public void deletarUsuario(Integer login) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(excluirUsuario);
            stmt.setInt(1, login);
            stmt.executeUpdate();

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

    public List<Usuario> listarUsuario() throws ClassNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {

            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarUsuario);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getInt("login"));
                usuario.setSenha(rs.getString("senha"));
                listaUsuario.add(usuario);
            }
            return listaUsuario;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar Usuario no banco de dados. Origem=" + ex.getMessage());
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

    public Usuario consultarUsuario(Integer login) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultaUsuario);
            stmt.setInt(1, login);
            rs = stmt.executeQuery();
            rs.next();
            Usuario usuario = new Usuario();
            usuario.setLogin(rs.getInt("login"));
            usuario.setSenha(rs.getString("senha"));
            return usuario;

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
