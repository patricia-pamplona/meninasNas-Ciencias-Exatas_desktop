package Domain;

import java.util.ArrayList;
import java.util.List;

import Persistence.ComponenteDAO;
import Persistence.EquipeDAO;

public class Componente implements InterfaceEntidade{
	
	public static List<Componente> componentes = new ArrayList();
	final private int id;
	private String nome;
	private ComponenteDAO compDAO;
	
	public Componente() {
		this.id = Componente.componentes.size() + 1;
		this.nome = "";
		compDAO = new ComponenteDAO();
	}
	public Componente (int id, String nome) {
		this.id = id;
		//this.id = Componente.componentes.size() + 1;
		this.nome = nome;
		compDAO = new ComponenteDAO();
		
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
	
	public static void Listar() {
		ComponenteDAO compDAO = new ComponenteDAO();
		
		Componente.componentes = compDAO.listar();
		
		if(Componente.componentes.size() == 0) {
			System.out.println("Nenhum componente cadastrado!");
		}else {
			for (Componente c : Componente.componentes) {
				System.out.println("ID: " + c.getId() + " Nome: " + c.getNome());
				
			}
		}
	}
	
	public static void ListarPorIdFoguete(int id_foguete) {
		ComponenteDAO compDAO = new ComponenteDAO();
		
		Componente.componentes = compDAO.listarPorIdFoguete(id_foguete);
		
		if(Componente.componentes.size() == 0) {
			System.out.println("Nenhum componente cadastrado!");
		}else {
			for (Componente c : Componente.componentes) {
				System.out.println("ID: " + c.getId() + " Nome: " + c.getNome());
				
			}
		}
	}
	
	public static Componente ConsultarPorId(int idComponente) {
		ComponenteDAO compDAO = new ComponenteDAO();
		Componente comp =  compDAO.consultarPorId(idComponente);
		return comp;
		
		/*for (Componente c : Componente.componentes) {
			if(c.getId() == idComponente) {
				return c;
			}
		}
		System.out.println("Componente inválido");
		return null;
		*/
	}
	
	public static Componente ConsultarPorNome(String nome) {
		ComponenteDAO compDAO = new ComponenteDAO();
		Componente comp = compDAO.consultarPorNome(nome);
		if(comp != null) {
			System.out.println("ID: " + comp.getId() + " NOME: " + comp.getNome());
		}
		return comp;
		/*for (Componente c : Componente.componentes) {
			if(c.getNome().equals(nome)) {
				System.out.println("ID: " + c.getId() + " NOME: " + c.getNome());	
			return c;
			}
		}
		System.out.println("Componente não encontrado!");
		return null;
		*/
	}
	
	@Override
	public void Adicionar() {
		compDAO.Adicionar(this);
		//Componente.componentes.add(this);
		
	}
	
	@Override
	public void Excluir() {
		compDAO.Excluir(this);
		/*Componente.componentes.remove(this);
		 */
		System.out.println("Componente excluído!");
	}
	
	@Override
	public void Alterar() {
		compDAO.Alterar(this);
		/* Componente.componentes.remove(this);
		Componente.componentes.add(this);
		*/
	}
	
	
}
