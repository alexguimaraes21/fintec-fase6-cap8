package br.com.fiap.fase6cap8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fase6cap8.models.TipoLancamento;
import br.com.fiap.fase6cap8.utils.DbConnection;

public class TipoLancamentoDao implements ICrudDao<TipoLancamento> {
	
	private Connection getConnection() throws SQLException {
		return DbConnection.getConnection();
	}

	@Override
	public List<TipoLancamento> getAll() {
		
		List<TipoLancamento> contas = new ArrayList<TipoLancamento>();
		try(Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT cd_tipo, ds_tipo FROM T_TIPO_LANCAMENTO")) {
			while(rs.next()) {
				TipoLancamento tipoLancamento = criarTipoLancamento(rs);
				contas.add(tipoLancamento);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao extrair as contas. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return contas;
	}

	@Override
	public TipoLancamento getById(Long id) {
		TipoLancamento tipoLancamento = null;
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT cd_tipo, ds_tipo FROM T_TIPO_LANCAMENTO WHERE cd_tipo = ?")) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					tipoLancamento = criarTipoLancamento(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao extrair a conta com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return tipoLancamento;
	}

	@Override
	public void save(TipoLancamento t) {
		
		String sql = "";
		if(t.getId() != null && t.getId() > 0) {
			sql = "UPDATE T_TIPO_LANCAMENTO SET ds_tipo = ? WHERE cd_conta = ?";
		} else {
			sql = "INSERT INTO T_TIPO_LANCAMENTO (ds_tipo, cd_tipo) VALUES (?, SEQ_TIPO_LANCAMENTO.NEXTVAL)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, t.getDsTipo());
			if(t.getId() != null && t.getId() > 0) {
				stmt.setLong(2, t.getId());
			} 
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar ou atualizar a conta. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}

	@Override
	public void delete(Long id) {
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM T_TIPO_LANCAMENTO WHERE cd_conta = ?")) {
			stmt.setLong(0, id);
			stmt.executeQuery();
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao remover a conta com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}
	
	private static TipoLancamento criarTipoLancamento(ResultSet rs) throws SQLException {
		TipoLancamento tipoLancamento = new TipoLancamento();
		tipoLancamento.setId(rs.getLong("cd_tipo"));
		tipoLancamento.setDsTipo(rs.getString("ds_tipo"));
		return tipoLancamento;
	}
	
}
