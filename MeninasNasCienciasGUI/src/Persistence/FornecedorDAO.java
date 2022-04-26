package Persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Domain.Equipe;
import Domain.Fornecedor;

public class FornecedorDAO {

	private Conexao minhaConexao;
	private final String selectGeral = "select * from projeto_pesquisa.Fornecedor";
	private final String SelectPorId = "select * from projeto_pesquisa.Fornecedor where Id = ?";
	private final String SelectPorNome = "select * from projeto_pesquisa.Fornecedor where nome = ?";
	private final String insert = "INSERT INTO PROJETO_PESQUISA.Fornecedor (Nome, categoria, email, telefone, ruaNumero, cep, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String Excluir = "delete from projeto_pesquisa.Fornecedor where id = ?";
	private final String Alterar = "Update projeto_pesquisa.Fornecedor set Nome = ?, categoria = ?, email = ? , telefone = ?, ruaNumero = ?, cep = ?, cidade = ?, estado = ? where id = ?";
			
	public FornecedorDAO() {
		minhaConexao = new Conexao ("jdbc:postgresql://localhost:5432/meninas_ciencias_exatas", "postgres", "abc123");	
	}
	
	public void Adicionar (Fornecedor f) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(insert);
			instrucao.setString(1, f.getNome());
			instrucao.setString(2, f.getCategoria());
			instrucao.setString(3, f.getEmail());
			instrucao.setString(4, f.getTelefone());
			instrucao.setString(5, f.getRuaNumero());
			instrucao.setString(6, f.getCep());
			instrucao.setString(7, f.getCidade());
			instrucao.setString(8, f.getEstado());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FornecedorDAO.Adicionar: " + e.getMessage());
		}
	}

	public void Alterar (Fornecedor f) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Alterar);
			instrucao.setString(1, f.getNome());
			instrucao.setString(2, f.getCategoria());
			instrucao.setString(3, f.getEmail());
			instrucao.setString(4, f.getTelefone());
			instrucao.setString(5, f.getRuaNumero());
			instrucao.setString(6, f.getCep());
			instrucao.setString(7, f.getCidade());
			instrucao.setString(8, f.getEstado());
			instrucao.setInt(9, f.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FornecedorDAO.Alterar: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void Excluir (Fornecedor f) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(Excluir);
			instrucao.setInt(1, f.getId());
			instrucao.execute();
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FornecedorDAO.Excluir: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	public ArrayList<Fornecedor> listar () {
		ArrayList<Fornecedor> resultado = new ArrayList();
		
		try {
		minhaConexao.conectar();
		Statement instrucao = minhaConexao.getConexao().createStatement();
		ResultSet resultSet = instrucao.executeQuery(selectGeral);
		while(resultSet.next()) {
			Fornecedor f = new Fornecedor(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("categoria"),  resultSet.getString("email"), resultSet.getString("telefone"), resultSet.getString("rua_numero"), resultSet.getString("cep"), resultSet.getString("cidade"), resultSet.getString("estado"));
			resultado.add(f);
		}
		minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FornecedorDAO.Listar: " + e.getMessage());
		}
		return resultado;
	}

	public Fornecedor consultarPorNome (String nome) {
		Fornecedor resultado = null;
		
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(SelectPorNome);
			instrucao.setString(1, nome);
			ResultSet resultSet = instrucao.executeQuery();
			if(resultSet.next()) {
				resultado = new Fornecedor(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("categoria"),  resultSet.getString("email"), resultSet.getString("telefone"), resultSet.getString("rua_numero"), resultSet.getString("cep"), resultSet.getString("cidade"), resultSet.getString("estado"));
			}
			minhaConexao.desconectar();
		}catch (Exception e) {
			System.out.println("Erro FornecedorDAO.consultarPorNome: " + e.getMessage());
		}
		return resultado;
	}
}
