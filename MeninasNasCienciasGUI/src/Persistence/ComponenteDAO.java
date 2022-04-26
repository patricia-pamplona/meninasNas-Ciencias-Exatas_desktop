package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Domain.Componente;
import Domain.Equipe;

public class ComponenteDAO {
		
		private Conexao minhaConexao;
		private final String selectGeral = "select * from projeto_pesquisa.Componente";
		String selectPorIdFoguete = "select * from projeto_pesquisa.Componente where id = ?";
		private final String SelectPorId = "select * from projeto_pesquisa.Componente where Id = ?";
		private final String SelectPorNome = "select * from projeto_pesquisa.Componente where nome = ?";
		private final String insert = "INSERT INTO PROJETO_PESQUISA.Componente (nome) values (?)";
		private final String Excluir = "delete from projeto_pesquisa.Componente where id = ?";
		private final String Alterar = "Update projeto_pesquisa.Componente set Nome = ? where id = ?";
				
		public ComponenteDAO() {
			minhaConexao = new Conexao ("jdbc:postgresql://localhost:5432/meninas_ciencias_exatas", "postgres", "abc123");	
		}
	
		public void Alterar (Componente comp) {
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Alterar);
				instrucao.setString(1, comp.getNome() );
				instrucao.setInt(2, comp.getId());
				instrucao.execute();
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.Alterar: " + e.getMessage());
				e.printStackTrace();
			}
		}

		public ArrayList<Componente> listar () {
			ArrayList<Componente> resultado = new ArrayList();
			
			try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet resultSet = instrucao.executeQuery(selectGeral);
			while(resultSet.next()) {
				Componente comp = new Componente(resultSet.getInt("id"), resultSet.getString("nome"));
				resultado.add(comp);
				
			}
			minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.Listar: " + e.getMessage());
			}
			return resultado;
		}

		public void Adicionar (Componente comp) {
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(insert);
				instrucao.setString(1, comp.getNome());
				instrucao.execute();
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.Adicionar: " + e.getMessage());
			}
		}

		public void Excluir (Componente comp) {
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Excluir);
				instrucao.setInt(1, comp.getId());
				instrucao.execute();
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.Excluir: " + e.getMessage());
				e.printStackTrace();
			}
			
		}

		public Componente consultarPorId (int id){
			Componente resultado = null;
			
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorId);
				instrucao.setInt(1, id);
				ResultSet resultSet = instrucao.executeQuery();
				if(resultSet.next()) {
					resultado = new Componente(resultSet.getInt("id"), resultSet.getString("nome"));
				}
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.consultarPorId: " + e.getMessage());
			}
				return resultado;
		}

		public Componente consultarPorNome (String nome) {
			Componente resultado = null;
			
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorNome);
				instrucao.setString(1, nome);
				ResultSet resultSet = instrucao.executeQuery();
				if(resultSet.next()) {
					resultado = new Componente(resultSet.getInt("id"), resultSet.getString("nome"));
				}
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.consultarPorNome: " + e.getMessage());
			}
			return resultado;
		}

		public List<Componente> listarPorIdFoguete(int id_foguete) {
			ArrayList<Componente> resultado = new ArrayList();
			
			try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(selectPorIdFoguete);
			instrucao.setInt(1, id_foguete);
			ResultSet resultSet = instrucao.executeQuery();
			while(resultSet.next()) {
				Componente comp = new Componente(resultSet.getInt("id"), resultSet.getString("nome"));
				resultado.add(comp);
				
			}
			minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ComponenteDAO.Listar: " + e.getMessage());
			}
			return resultado;
		}
}
