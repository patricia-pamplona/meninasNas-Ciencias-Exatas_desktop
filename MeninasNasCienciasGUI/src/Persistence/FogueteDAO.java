package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Domain.Equipe;
import Domain.Foguete;

public class FogueteDAO {

	private Conexao minhaConexao;
	private final String selectGeral = "select * from projeto_pesquisa.foguete";
	private final String SelectPorId = "select * from projeto_pesquisa.foguete where Id = ?";
	private final String insert = "INSERT INTO PROJETO_PESQUISA.foguete (id_equipe, Base) values (?, ?)";
	private final String Excluir = "delete from projeto_pesquisa.foguete where id = ?";
	private final String ExcluirComponentes = "delete from projeto_pesquisa.foguete_componente where id_foguete = ?";
	private final String Alterar = "Update projeto_pesquisa.foguete set Base = ?, id_equipe = ? where id = ?";
	private final String insertFogCom = "insert into projeto_pesquisa.foguete_componente (Id_foguete, Id_componente) values (? ,? )";
	private final String ExcluirFogComp = "delete from projeto_pesquisa.foguete where id = ?";
	
	public FogueteDAO() {
		minhaConexao = new Conexao ("jdbc:postgresql://localhost:5432/meninas_ciencias_exatas", "postgres", "abc123");	
	}
	
	public ArrayList<Foguete> listar () {
		ArrayList<Foguete> resultado = new ArrayList();
		
		try {
		minhaConexao.conectar();
		Statement instrucao = minhaConexao.getConexao().createStatement();
		ResultSet resultSet = instrucao.executeQuery(selectGeral);
		while(resultSet.next()) {
			Foguete fog = new Foguete(resultSet.getInt("id"), resultSet.getInt("id_equipe"), resultSet.getString("base"));
			resultado.add(fog);
		}
		minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.Listar: " + e.getMessage());
		}
		return resultado;
	}
	
	public int Adicionar (Foguete fog) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			instrucao.setInt(1, fog.getIdEquipe());
			instrucao.setString(2, fog.getBase());
			instrucao.execute();
			ResultSet keyset = instrucao.getGeneratedKeys();
			if(keyset.next()) {
				return keyset.getInt("id");
			}
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.Adicionar: " + e.getMessage());
		}
		return 0;
	}

	public void Alterar (Foguete fog) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Alterar);
			instrucao.setString(1, fog.getBase());
			instrucao.setInt(2, fog.getIdEquipe());
			instrucao.setInt(3, fog.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.Alterar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Excluir (Foguete fog) {
		try {
			minhaConexao.conectar();
			//excluindo foguete_componente do foguete
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ExcluirComponentes);
			instrucao.setInt(1, fog.getId());
			instrucao.execute();
			//excluindo foguete
			instrucao = minhaConexao.getConexao().prepareStatement(Excluir);
			instrucao.setInt(1, fog.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.Excluir: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public Foguete consultarPorId (int id){
		Foguete resultado = null;
		
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorId);
			instrucao.setInt(1, id);
			ResultSet resultSet = instrucao.executeQuery();
			if(resultSet.next()) {
				resultado = new Foguete(resultSet.getInt("id"), resultSet.getInt("id_equipe"), resultSet.getString("Base"));
			}
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.consultarPorId: " + e.getMessage());
		}
			return resultado;
	}

	public void AdicionarComponente (int id_foguete, int id_componente) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(insertFogCom);
			instrucao.setInt(1, id_foguete);
			instrucao.setInt(2, id_componente);
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.AdicionarComponente: " + e.getMessage());
		}
	}

	public void ExcluirComponente (int id_foguete, int id_componente) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(ExcluirFogComp);
			instrucao.setInt(1, id_foguete);
			instrucao.setInt(2, id_componente);
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FogueteDAO.ExcluirComponente: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
