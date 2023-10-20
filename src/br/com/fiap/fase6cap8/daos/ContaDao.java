package br.com.fiap.fase6cap8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fase6cap8.models.Conta;
import br.com.fiap.fase6cap8.utils.DbConnection;

public class ContaDao implements ICrudDao<Conta> {
	
	private Connection getConnection() throws SQLException {
		return DbConnection.getConnection();
	}

	@Override
	public List<Conta> getAll() {
		
		List<Conta> contas = new ArrayList<Conta>();
		try(Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT cd_conta, vl_agencia, vl_conta, vl_banco, cd_usuario FROM T_CONTA")) {
			while(rs.next()) {
				Conta conta = criarConta(rs);
				contas.add(conta);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao extrair as contas. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return contas;
	}

	@Override
	public Conta getById(Long id) {
		Conta conta = null;
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT cd_conta, vl_agencia, vl_conta, vl_banco, cd_usuario "
				+ "FROM T_CONTA"
				+ " WHERE cd_conta = ?")) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					conta = criarConta(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao extrair a conta com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return conta;
	}

	@Override
	public void save(Conta t) {
		
		String sql = "";
		if(t.getId() != null && t.getId() > 0) {
//			sql = "UPDATE T_CONTA SET vl_agencia = ?, vl_conta = ?, vl_banco = ?, cd_usuario = ? WHERE cd_conta = ?";
			sql = "UPDATE T_CONTA SET vl_agencia = ?, vl_conta = ?, vl_banco = ? WHERE cd_conta = ?";
		} else {
//			sql = "INSERT INTO T_CONTA (vl_agencia, vl_conta, vl_banco, cd_usuario, cd_conta) VALUES (?, ?, ?, ?, SEQ_CONTA.NEXTVAL)";
			sql = "INSERT INTO T_CONTA (vl_agencia, vl_conta, vl_banco, cd_conta) VALUES (?, ?, ?, SEQ_CONTA.NEXTVAL)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, t.getVlAgencia());
			stmt.setString(2, t.getVlConta());
			stmt.setString(3, t.getVlBanco());
//			stmt.setLong(4, t.getUsuario().getId());
			if(t.getId() != null && t.getId() > 0) {
				stmt.setLong(4, t.getId());
			} 
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar ou atualizar a conta. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}

	@Override
	public void delete(Long id) {
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM T_CONTA WHERE cd_conta = ?")) {
			stmt.setLong(0, id);
			stmt.executeQuery();
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao remover a conta com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		
	}
	
	private static Conta criarConta(ResultSet rs) throws SQLException {
//		UsuarioDao usuarioDao = new UsuarioDao();
		Conta conta = new Conta();
		conta.setId(rs.getLong("cd_conta"));
		conta.setVlAgencia(rs.getString("vl_agencia"));
		conta.setVlConta(rs.getString("vl_conta"));
		conta.setVlBanco(rs.getString("vl_banco"));
//		conta.setUsuario(usuarioDao.getById(rs.getLong("cd_usuario")));
		return conta;
	}
	
}
