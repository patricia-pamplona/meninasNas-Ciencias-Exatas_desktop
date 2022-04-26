package Domain;

import java.util.ArrayList;
import java.util.List;

import Persistence.EquipeDAO;
import Persistence.FornecedorDAO;

public class Fornecedor implements InterfaceEntidade { 	 	
	public static List<Fornecedor> fornecedores = new ArrayList();
	final private int id;
	private FornecedorDAO forDAO;
	private String nome;
	private String categoria;
	private String email;
	private String telefone;
	private String ruaNumero;
	private String cep;
	private String cidade;
	private String estado;
	
	public Fornecedor() {
		this.id = Fornecedor.fornecedores.size() + 1;
		this.nome = "";
		this.categoria = "";
		this.email = "";
		this.telefone = "";
		this.ruaNumero = "";
		this.cep = "";
		this.cidade = "";
		this.estado = "";
		forDAO = new FornecedorDAO();
		
	}
	public Fornecedor (int id, String nome, String categoria, String email, String telefone, String ruaNumero, String cep, String cidade, String estado) {
		this.id = id;
		//this.id = Fornecedor.fornecedores.size() + 1;
		this.nome = nome;
		this.categoria = categoria;
		this.email = email;
		this.telefone = telefone;
		this.ruaNumero = ruaNumero;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		forDAO = new FornecedorDAO();
	}
	public String getRuaNumero() {
		return ruaNumero;
	}
	public void setRuaNumero(String ruaNumero) {
		this.ruaNumero = ruaNumero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void Adicionar() {
		forDAO.Adicionar(this);
		//Fornecedor.fornecedores.add(this);
	}	
	
	public void Excluir() {
		forDAO.Excluir(this);
		/*Fornecedor.fornecedores.remove(this);
		System.out.println("Fornecedor excluído!");
		*/
	}
	

	public void Alterar() {
		forDAO.Alterar(this);
		/*Fornecedor.fornecedores.remove(this);
		Fornecedor.fornecedores.add(this);
		*/
	}
	
	public static void Listar() {
		FornecedorDAO forDAO = new FornecedorDAO();
		
		Fornecedor.fornecedores = forDAO.listar();
		
		if(Fornecedor.fornecedores.size() == 0) {
			System.out.println("Nenhum fornecedor cadastrado!");
		}else {
			for (Fornecedor fo : Fornecedor.fornecedores) {
				System.out.println("ID: " + fo.getId());
				System.out.println("NOME: " + fo.getNome());
				System.out.println("CATEGORIA: " + fo.getCategoria());
				System.out.println("EMAIL: " + fo.getEmail());
				System.out.println("TELEFONE: " + fo.getTelefone());
				System.out.println("CEP: " + fo.getCep());
				System.out.println("CIDADE: " + fo.getCidade());
				System.out.println("ESTADOE: " + fo.getEstado());
				System.out.println("RUA/NUMERO: " + fo.getRuaNumero());
				
				System.out.println("===============================" );
			}
		}
	}
	
	public static Fornecedor ConsultarPorNome(String nome) {
		
		FornecedorDAO forDAO = new FornecedorDAO();
		Fornecedor f = forDAO.consultarPorNome(nome);
		if(f != null) {
			System.out.println("ID: " + f.getId());
			System.out.println("NOME: " + f.getNome());
			System.out.println("CATEGORIA: " + f.getCategoria());
			System.out.println("EMAIL: " + f.getEmail());
			System.out.println("TELEFONE: " + f.getTelefone());
			System.out.println("CEP: " + f.getCep());
			System.out.println("CIDADE: " + f.getCidade());
			System.out.println("ESTADOE: " + f.getEstado());
			System.out.println("RUA/NUMERO: " + f.getRuaNumero());
			System.out.println("===============================" );
		}
			return f;
			
		/*for (Fornecedor fo : Fornecedor.fornecedores) {
			if(fo.getNome().equals(nome)) {
				System.out.println("ID: " + fo.getId());
				System.out.println("NOME: " + fo.getNome());
				System.out.println("CATEGORIA: " + fo.getCategoria());
				System.out.println("EMAIL: " + fo.getEmail());
				System.out.println("TELEFONE: " + fo.getTelefone());
				System.out.println("CEP: " + fo.getCep());
				System.out.println("CIDADE: " + fo.getCidade());
				System.out.println("ESTADOE: " + fo.getEstado());
				System.out.println("RUA/NUMERO: " + fo.getRuaNumero());
				System.out.println("===============================" );
				return fo;
			}	
		}
		
		System.out.println("Fornecedor não encontrado!");
		return null;
		*/
	
	}
	
	
}