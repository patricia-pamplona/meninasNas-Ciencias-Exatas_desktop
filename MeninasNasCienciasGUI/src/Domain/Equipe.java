package Domain;

import java.util.ArrayList;
import java.util.List;

import Persistence.EquipeDAO;

public class Equipe implements InterfaceEntidade{
	public static List<Equipe> equipes = new ArrayList();
	final private int id;
	private EquipeDAO eqDAO;
	private String cidade;
	private String escola;
	private String nivelEscolar;
	
	public Equipe() {
		this.id = Equipe.equipes.size() + 1;
		this.cidade= "";
		this.nivelEscolar= "";
		this.escola = "";
		eqDAO = new EquipeDAO();
		
	}
	
	public Equipe(int id, String cidade, String escola, String nivelEscolar) {
		this.id = id;
		//this.id = Equipe.equipes.size() + 1;
		this.cidade= cidade;
		this.nivelEscolar= nivelEscolar;
		this.escola = escola;
		eqDAO = new EquipeDAO();
	}

	public int getId() {
		return id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getNivelEscolar() {
		return nivelEscolar;
	}

	public void setNivelEscolar(String nivelEscolar) {
		this.nivelEscolar = nivelEscolar;
	}
	
	public void Adicionar(){
		eqDAO.Adicionar(this);
		//Equipe.equipes.add(this);
	}
	
	public void Excluir() {
		eqDAO.Excluir(this);
		/*Equipe.equipes.remove(this);
		 */
		System.out.println("Equipe excluída!");
		
	}
	
	public void Alterar() {
		eqDAO.Alterar(this);
		/*Equipe.equipes.remove(this);
		Equipe.equipes.add(this);
		*/
	}
	
	public static Equipe ConsultarPorCidade(String cidade) {
		EquipeDAO eqDAO = new EquipeDAO();
		Equipe eq = eqDAO.consultarPorCidade(cidade);
		if(eq != null) {
			System.out.println("ID: " + eq.getId());
			System.out.println("Escola: " + eq.getEscola());
			System.out.println("Cidade: " + eq.getCidade());
			System.out.println("Nível escolar: " + eq.getNivelEscolar());
			System.out.println("===============================" );
		}
		return eq;
		/*for (Equipe e : Equipe.equipes) {
			if (e.getCidade().equals(cidade)) {
				System.out.println("ID: " + e.getId());
				System.out.println("Escola: " + e.getEscola());
				System.out.println("Cidade: " + e.getCidade());
				System.out.println("Nível escolar: " + e.getNivelEscolar());
				System.out.println("===============================" );
				return e;
			} 
		}*/
		
	}
	
	public static void Listar() {
		EquipeDAO eqDAO = new EquipeDAO();
		
		Equipe.equipes = eqDAO.listar();
		if(Equipe.equipes.size() == 0) {
			System.out.println("Nenhuma Equipe cadastrado!");
		}else {
			System.out.println("LISTA DE EQUIPES:");
			for (Equipe e : Equipe.equipes) {
				System.out.println("ID: " + e.getId());
				System.out.println("Escola: " + e.getEscola());
				System.out.println("Cidade: " + e.getCidade());
				System.out.println("Nível escolar: " + e.getNivelEscolar());

				System.out.println("===============================" );
			}
		}
	}
	
	public static Equipe ConsultarPorId(int id) {
		EquipeDAO eqDAO = new EquipeDAO();
		Equipe eq =  eqDAO.consultarPorId(id);
		return eq;
		/*for (Equipe e : Equipe.equipes) {
			if(e.getId()==(id)) {
				
				return e;
			}	
		}
		return null;
	*/
		
	}
}
