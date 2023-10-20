package br.com.fiap.fase6cap8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fase6cap8.models.TipoInvestimento;
import br.com.fiap.fase6cap8.utils.DbConnection;

public class TipoInvestimentoDao implements ICrudDao<TipoInvestimento> {
	
	private Connection getConnection() throws SQLException {
		return DbConnection.getConnection();
	}

	@Override
	public List<TipoInvestimento> getAll() {		
		List<TipoInvestimento> tipoInvestimentos = new ArrayList<TipoInvestimento>();
		try(Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT cd_tipo_investimento, ds_tipo_investimento, vl_rendimento_mensal, prazo_minimo_investimento "
						+ "FROM T_TIPO_INVESTIMENTO")) {
			while(rs.next()) {
				TipoInvestimento tipoInvestimento = criarTipoInvestimento(rs);
				tipoInvestimentos.add(tipoInvestimento);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao extrair as carteiras de investimentos. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return tipoInvestimentos;
	}

	@Override
	public TipoInvestimento getById(Long id) {
		TipoInvestimento tipoInvestimento = null;
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT cd_tipo_investimento, ds_tipo_investimento, vl_rendimento_mensal, prazo_minimo_investimento "
				+ "FROM T_TIPO_INVESTIMENTO WHERE cd_tipo_investimento = ?")) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					tipoInvestimento = criarTipoInvestimento(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao extrair a carteira de investimento com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
		return tipoInvestimento;
	}

	@Override
	public void save(TipoInvestimento t) {
		
		String sql = "";
		if(t.getId() != null && t.getId() > 0) {
			sql = "UPDATE T_TIPO_INVESTIMENTO SET ds_tipo_investimento = ?, vl_rendimento_mensal = ?, prazo_minimo_investimento = ? WHERE cd_tipo_investimento = ?";
		} else {
			sql = "INSERT INTO T_TIPO_INVESTIMENTO (ds_tipo_investimento, vl_rendimento_mensal, prazo_minimo_investimento, cd_tipo_investimento) VALUES (?, ?, ?, SEQ_TIPO_INVESTIMENTO.NEXTVAL)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, t.getDsTipoInvestimento());
			stmt.setDouble(2, t.getVlRendimentoMensal());
			stmt.setInt(3, t.getPrazoMinimoInvestimento());
			if(t.getId() != null && t.getId() > 0) {
				stmt.setLong(4, t.getId());
			} 
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar ou atualizar a carteira de investimento. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}

	@Override
	public void delete(Long id) {
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM T_TIPO_INVESTIMENTO WHERE cd_conta = ?")) {
			stmt.setLong(0, id);
			stmt.executeQuery();
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao remover a conta com ID [ " + id + " ]. Erro [ " + e.getMessage() + " ].");
			System.out.println("Trace do erro [ " + e.getStackTrace().toString() + " ].");
		}
	}
	
	private static TipoInvestimento criarTipoInvestimento(ResultSet rs) throws SQLException {
		TipoInvestimento tipoInvestimento = new TipoInvestimento();
		tipoInvestimento.setId(rs.getLong("cd_tipo_investimento"));
		tipoInvestimento.setDsTipoInvestimento(rs.getString("ds_tipo_investimento"));
		tipoInvestimento.setPrazoMinimoInvestimento(rs.getInt("prazo_minimo_investimento"));
		tipoInvestimento.setVlRendimentoMensal(rs.getDouble("vl_rendimento_mensal"));
		return tipoInvestimento;
	}
	
}
