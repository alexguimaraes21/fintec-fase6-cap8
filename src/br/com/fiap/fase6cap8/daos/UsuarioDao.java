package br.com.fiap.fase6cap8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fase6cap8.models.Usuario;
import br.com.fiap.fase6cap8.utils.DbConnection;

public class UsuarioDao implements ICrudDao<Usuario> {
	
	private Connection getConnection() throws SQLException {
		return DbConnection.getConnection();
	}

	@Override
	public List<Usuario> getAll() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try(Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT cd_usuario, vl_cpf_usuario, nm_usuario, ds_senha, ck_usuario_ativo FROM T_USUARIO")) {
			while(rs.next()) {
				Usuario usuario = criarUsuario(rs);
				usuarios.add(usuario);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao extrair os usuários. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return usuarios;
	}

	@Override
	public Usuario getById(Long id) {
		Usuario usuario = null;
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT cd_usuario, vl_cpf_usuario, nm_usuario, ds_senha, ck_usuario_ativo FROM T_USUARIO"
				+ " WHERE cd_usuario = ?")) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					usuario = criarUsuario(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao extrair o usuário com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return usuario;
	}

	@Override
	public void save(Usuario t) {
		
		String sql = "";
		if(t.getId() != null && t.getId() > 0) {
			sql = "UPDATE T_USUARIO SET vl_cpf_usuario = ?, nm_usuario = ?, ds_senha = ?, ck_usuario_ativo = ? WHERE cd_usuario = ?";
		} else {
			sql = "INSERT INTO T_USUARIO (vl_cpf_usuario, nm_usuario, ds_senha, ck_usuario_ativo, cd_usuario) VALUES (?, ?, ?, ?, SEQ_USUARIO.NEXTVAL)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, t.getVlCpf());
			stmt.setString(2, t.getNmUsuario());
			stmt.setString(3, t.getDsSenha());
			stmt.setInt(4, t.isCkUsuarioAtivo() ? 1 : 0);
			if(t.getId() != null && t.getId() > 0) {
				stmt.setLong(5, t.getId());
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar ou atualizar o usuario. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}

	@Override
	public void delete(Long id) {
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM T_USUARIO WHERE cd_usuario = ?")) {
			stmt.setLong(0, id);
			stmt.executeQuery();
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao remover o usuário com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		
	}
	
	private static Usuario criarUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getLong("cd_usuario"));
		usuario.setVlCpf(rs.getString("vl_cpf_usuario"));
		usuario.setNmUsuario(rs.getString("nm_usuario"));
		usuario.setDsSenha(rs.getString("ds_senha"));
		usuario.setCkUsuarioAtivo(rs.getInt("ck_usuario_ativo") == 1 ? true : false);
		return usuario;
	}
	
}
