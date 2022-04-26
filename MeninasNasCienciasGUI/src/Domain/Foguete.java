package Domain;

import java.util.ArrayList;
import java.util.List;

import Persistence.ComponenteDAO;
import Persistence.EquipeDAO;
import Persistence.FogueteDAO;

public class Foguete implements InterfaceEntidade {
	public static List<Foguete> foguetes = new ArrayList();
	private List<Componente> componentes;
	private int id;
	private int idEquipe;
	private String base;
	FogueteDAO fogDAO;
	
	public Foguete(){
		this.id = 0;
		this.componentes = new ArrayList();
		this.idEquipe = 0;
		this.base = "";
		fogDAO = new FogueteDAO();
	}
	
	public Foguete (int id, int idEquipe, String base) {
		this.id = id;
		
		//this.id = Foguete.foguetes.size() + 1;
		this.componentes = new ArrayList();
		this.idEquipe = idEquipe;
		this.base = base;
		fogDAO = new FogueteDAO();
	}
	
	public int getId() {
		return id;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	public void Adicionar() {
		this.id = fogDAO.Adicionar(this);
		//Foguete.foguetes.add(this);
	}
	
	public void Excluir() {
		fogDAO.Excluir(this);
		//Foguete.foguetes.remove(this);
	}
	
	public void Alterar() {
		fogDAO.Alterar(this);
		/*Foguete.foguetes.remove(this);
		Foguete.foguetes.add(this);
		*/
	}
	
	public static void Listar() {
		FogueteDAO fogDAO = new FogueteDAO();
		ComponenteDAO compDAO = new ComponenteDAO();
		Foguete.foguetes = fogDAO.listar();
		
		if(Foguete.foguetes.size() == 0) {
			System.out.println("Nenhum Foguete cadastrado!");
		}else {
			
			for (Foguete f : Foguete.foguetes) {
				
				System.out.println("ID: " + f.getId());
				Equipe e = Equipe.ConsultarPorId(f.getIdEquipe());
				System.out.println("ID Equipe - Escola: " + f.getIdEquipe() + " - " + e.getEscola());
				System.out.println("Base: " + f.getBase());
				
				System.out.println("Componentes: ");
				f.componentes = compDAO.listarPorIdFoguete(f.getId()); 
				for (Componente c : f.componentes) {
					System.out.println("> ID: " + c.getId() + " Nome: " + c.getNome());
				}

				System.out.println("===============================" );
			}
		}
	}
	
	public static Foguete ConsultarPorId(int id) {
		FogueteDAO fogDAO = new FogueteDAO();
		Foguete fog =  fogDAO.consultarPorId(id);
		
		if(fog != null) {
			System.out.println("ID: " + fog.getId());
			System.out.println("ID EQUIPE: " + fog.getIdEquipe());
			System.out.println("Base: " + fog.getBase());
			for (Componente c : fog.componentes) {
				System.out.println("ID: " + c.getId());
				System.out.println("Nome: " + c.getNome());
			}
			System.out.println("===============================" );
		}
			
		return fog;
		
		/*for (Foguete f : Foguete.foguetes) {
			if(f.getId() == id) {
				System.out.println("ID: " + f.getId());
				System.out.println("ID EQUIPE: " + f.getIdEquipe());
				System.out.println("Base: " + f.getBase());
				for (Componente c : f.componentes) {
					System.out.println("ID: " + c.getId());
					System.out.println("Nome: " + c.getNome());
				}

				System.out.println("===============================" );
				return f;
			}
		}
		System.out.println("Foguete não encontrado!");
		return null;
		*/
	}

	public void AdicionarComponentes (Componente c){
		ComponenteDAO compDAO = new ComponenteDAO();
		componentes = compDAO.listarPorIdFoguete(this.id);
		for (Componente componente : componentes) {
			if(componente.getId() == c.getId()) {
				return;
			}
		}
		fogDAO.AdicionarComponente(this.id, c.getId());
		//this.componentes.add(c);
	}
	
	public void ExcluirComponentes (Componente c) {
		this.componentes.remove(c);
	}
	
	
}
