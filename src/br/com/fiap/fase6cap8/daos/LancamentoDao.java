package br.com.fiap.fase6cap8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fase6cap8.models.Lancamento;
import br.com.fiap.fase6cap8.utils.DbConnection;

public class LancamentoDao implements ICrudDao<Lancamento> {
	
	private Connection getConnection() throws SQLException {
		return DbConnection.getConnection();
	}

	@Override
	public List<Lancamento> getAll() {
		
		List<Lancamento> contas = new ArrayList<Lancamento>();
		try(Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT cd_lancamento, vl_lancamento, dt_lancamento, ds_lancamento, "
						+ "cd_tipo, cd_usuario, cd_conta, cd_tipo_investimento FROM T_LANCAMENTO ")) {
			while(rs.next()) {
				Lancamento lancamento = criarLancamento(rs);
				contas.add(lancamento);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao extrair os lançamentos. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return contas;
	}

	@Override
	public Lancamento getById(Long id) {
		Lancamento lancamento = null;
		try(PreparedStatement stmt = getConnection().prepareStatement(
				"SELECT cd_lancamento, vl_lancamento, dt_lancamento, ds_lancamento, cd_tipo, cd_usuario, cd_conta, "
				+ "cd_tipo_investimento FROM T_LANCAMENTO WHERE cd_lancamento = ?"
				)) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					lancamento = criarLancamento(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao extrair o lançamento com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return lancamento;
	}

	@Override
	public void save(Lancamento t) {
		String sql = "";
		if(t.getId() != null && t.getId() > 0) {
			sql = "UPDATE T_LANCAMENTO SET vl_lancamento = ?, dt_lancamento = ?, ds_lancamento = ?, cd_tipo = ?, "
//					+ "cd_usuario = ?, cd_conta = ? WHERE cd_lancamento = ?";
					+ "cd_conta = ? WHERE cd_lancamento = ?";
		} else if ((t.getId() != null && t.getId() > 0) && (t.getTipoLancamento().getId() == 3)) {
			sql = "UPDATE T_LANCAMENTO SET vl_lancamento = ?, dt_lancamento = ?, ds_lancamento = ?, cd_tipo = ?, "
//					+ "cd_usuario = ?, cd_conta = ?, cd_tipo_investimento = ? WHERE cd_lancamento = ?";
					+ "cd_conta = ?, cd_tipo_investimento = ? WHERE cd_lancamento = ?";
		} else if (t.getTipoLancamento().getId() == 3) {
			sql = "INSERT INTO T_LANCAMENTO (vl_lancamento, dt_lancamento, ds_lancamento, cd_tipo, "
//					+ " cd_usuario, cd_conta, cd_tipo_investimento, cd_lancamento) VALUES (?, ?, ?, ?, ?, ?, ?, SEQ_LANCAMENTO.NEXTVAL)";
					+ "cd_conta, cd_tipo_investimento, cd_lancamento) VALUES (?, ?, ?, ?, ?, ?, SEQ_LANCAMENTO.NEXTVAL)";
		} else {
			sql = "INSERT INTO T_LANCAMENTO (vl_lancamento, dt_lancamento, ds_lancamento, cd_tipo, "
//					+ " cd_usuario, cd_conta, cd_lancamento) VALUES (?, ?, ?, ?, ?, ?, SEQ_LANCAMENTO.NEXTVAL)";
					+ "cd_conta, cd_lancamento) VALUES (?, ?, ?, ?, ?, SEQ_LANCAMENTO.NEXTVAL)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setDouble(1, t.getVlLancamento());
			java.sql.Date dataLancamento = new java.sql.Date(t.getDtLancamento().getTime());
			stmt.setDate(2, dataLancamento);
			stmt.setString(3, t.getDsLancamento());
			stmt.setLong(4, t.getTipoLancamento().getId());
//			stmt.setLong(4, t.getUsuario().getId());
			stmt.setLong(5, t.getConta().getId());
			if(t.getTipoLancamento().getId() == 3) {
				stmt.setLong(6, t.getTipoIvestimento().getId());
			}
			if(t.getId() != null && t.getId() > 0) {
				stmt.setLong(7, t.getId());
			} 
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar ou atualizar um lançamento. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}

	@Override
	public void delete(Long id) {
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM T_LANCAMENTO WHERE cd_lancamento = ?")) {
			stmt.setLong(0, id);
			stmt.executeQuery();
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao remover a conta com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}
	
	private static Lancamento criarLancamento(ResultSet rs) throws SQLException {
//		UsuarioDao usuarioDao = new UsuarioDao();
		ContaDao contaDao = new ContaDao();
		TipoLancamentoDao tipoLancamentoDao = new TipoLancamentoDao();
		TipoInvestimentoDao tipoInvestimentoDao = new TipoInvestimentoDao();
		Lancamento lancamento = new Lancamento();
		lancamento.setId(rs.getLong("cd_lancamento"));
		lancamento.setVlLancamento(rs.getDouble("vl_lancamento"));
		lancamento.setDtLancamento(rs.getDate("dt_lancamento"));
		lancamento.setDsLancamento(rs.getString("ds_lancamento"));
		lancamento.setTipoLancamento(tipoLancamentoDao.getById(rs.getLong("cd_tipo")));
		lancamento.setTipoIvestimento(tipoInvestimentoDao.getById(rs.getLong("cd_tipo_investimento")));
//		lancamento.setUsuario(usuarioDao.getById(rs.getLong("cd_usuario")));
		lancamento.setConta(contaDao.getById(rs.getLong("cd_conta")));
		return lancamento;
	}
	
}
