package Persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Domain.Equipe;
import Domain.Participante;

public class ParticipanteDAO {

	private Conexao minhaConexao;
	private final String selectGeral = "select * from projeto_pesquisa.participantes";
	private final String SelectPorNome = "select * from projeto_pesquisa.participantes where nome = ?";
	private final String insert = "INSERT INTO PROJETO_PESQUISA.participantes (data_nascimento, Nome, Funcao, id_equipe) values (?, ?, ?, ?)";
	private final String Excluir = "delete from projeto_pesquisa.participantes where Id = ?";
	private final String Alterar = "Update projeto_pesquisa.participantes set data_nascimento = ?, Nome = ?, Funcao = ?, id_equipe = ? where id = ?";
			
	public ParticipanteDAO() {
		minhaConexao = new Conexao ("jdbc:postgresql://localhost:5432/meninas_ciencias_exatas", "postgres", "abc123");	
	}
	
	public void Adicionar (Participante pa) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(insert);
			instrucao.setDate(1, Date.valueOf(pa.getIdade()));
			instrucao.setString(2, pa.getNome());
			instrucao.setString(3, pa.getFuncao());
			instrucao.setInt(4, pa.getIdEquipe());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro ParticipanteDAO.Adicionar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Excluir (Participante pa) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Excluir);
			instrucao.setInt(1, pa.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro ParticipanteDAO.Excluir: " + e.getMessage());
			e.printStackTrace();
		}
	}
		
	public void Alterar (Participante pa) {
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Alterar);
				instrucao.setDate(1, Date.valueOf(pa.getIdade()));
				instrucao.setString(2, pa.getNome());
				instrucao.setString(3, pa.getFuncao());
				instrucao.setInt(4, pa.getIdEquipe());
				instrucao.setInt(5, pa.getId());
				instrucao.execute();
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ParticipanteDAO.Alterar: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		public ArrayList<Participante> listar () {
			ArrayList<Participante> resultado = new ArrayList();
			
			try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet resultSet = instrucao.executeQuery(selectGeral);
			while(resultSet.next()) {
				Participante pa = new Participante(resultSet.getInt("Id"), resultSet.getInt("Id_Equipe"), resultSet.getString("Nome"), resultSet.getString("data_nascimento"), resultSet.getString("Funcao"));
				resultado.add(pa);
			}
			minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro ParticipanteDAO.Listar: " + e.getMessage());
			}
			return resultado;
		}
		
		public Participante consultarPorNome (String nome) {
			Participante resultado = null;
			
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorNome);
				instrucao.setString(1, nome);
				ResultSet resultSet = instrucao.executeQuery();
				if(resultSet.next()) {
					resultado = new Participante(resultSet.getInt("Id"), resultSet.getInt("Id_Equipe"), resultSet.getString("Nome"), resultSet.getString("data_nascimento"), resultSet.getString("Funcao"));
				}
				minhaConexao.desconectar();
			}catch (Exception e) {
				System.out.println("Erro EquipeDAO.consultarPorNome: " + e.getMessage());
			}
			return resultado;
		}
}
