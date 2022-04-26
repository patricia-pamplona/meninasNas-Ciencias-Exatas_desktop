package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Domain.Equipe;

public class EquipeDAO {
	
	private Conexao minhaConexao;
	private final String selectGeral = "select * from projeto_pesquisa.equipe";
	private final String SelectPorId = "select * from projeto_pesquisa.equipe where Id = ?";
	private final String SelectPorCidade = "select * from projeto_pesquisa.equipe where cidade = ?";
	private final String insert = "INSERT INTO PROJETO_PESQUISA.EQUIPE (Cidade, Escola, Nivel_Escolar) values (?, ?, ?)";
	private final String Excluir = "delete from projeto_pesquisa.equipe where id = ?";
	private final String Alterar = "Update projeto_pesquisa.equipe set Cidade = ?, Escola = ?, Nivel_Escolar = ? where id = ?";
			
	public EquipeDAO() {
		minhaConexao = new Conexao ("jdbc:postgresql://localhost:5432/meninas_ciencias_exatas", "postgres", "abc123");	
	}
	
	public void Alterar (Equipe eq) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Alterar);
			instrucao.setString(1, eq.getCidade() );
			instrucao.setString(2, eq.getEscola());
			instrucao.setString(3, eq.getNivelEscolar());
			instrucao.setInt(4, eq.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro EquipeDAO.Alterar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Excluir (Equipe eq) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Excluir);
			instrucao.setInt(1, eq.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro EquipeDAO.Excluir: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void Adicionar (Equipe eq) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(insert);
			instrucao.setString(1, eq.getCidade());
			instrucao.setString(2, eq.getEscola());
			instrucao.setString(3, eq.getNivelEscolar());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro EquipeDAO.Adicionar: " + e.getMessage());
		}
	}
	
	public Equipe consultarPorId (int id){
		Equipe resultado = null;
		
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorId);
			instrucao.setInt(1, id);
			ResultSet resultSet = instrucao.executeQuery();
			if(resultSet.next()) {
				resultado = new Equipe(resultSet.getInt("id"), resultSet.getString("cidade"), resultSet.getString("escola"),  resultSet.getString("nivel_escolar"));
			}
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro EquipeDAO.consultarPorId: " + e.getMessage());
		}
			return resultado;
	}
	
	public Equipe consultarPorCidade (String cidade) {
		Equipe resultado = null;
		
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorCidade);
			instrucao.setString(1, cidade);
			ResultSet resultSet = instrucao.executeQuery();
			if(resultSet.next()) {
				resultado = new Equipe(resultSet.getInt("id"), resultSet.getString("cidade"), resultSet.getString("escola"),  resultSet.getString("nivel_escolar"));
			}
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro EquipeDAO.consultarPorCidade: " + e.getMessage());
		}
		return resultado;
	}
	
	public ArrayList<Equipe> listar () {
		ArrayList<Equipe> resultado = new ArrayList();
		
		try {
		minhaConexao.conectar();
		Statement instrucao = minhaConexao.getConexao().createStatement();
		ResultSet resultSet = instrucao.executeQuery(selectGeral);
		while(resultSet.next()) {
			Equipe e = new Equipe(resultSet.getInt("id"), resultSet.getString("cidade"), resultSet.getString("escola"),  resultSet.getString("nivel_escolar"));
			resultado.add(e);
			//System.out.println("idEquipe: " + resultSet.getInt("Id") + "cidade: " + resultSet.getString("cidade"));
			
		}
		minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro EquipeDAO.Listar: " + e.getMessage());
		}
		return resultado;
	}

}
