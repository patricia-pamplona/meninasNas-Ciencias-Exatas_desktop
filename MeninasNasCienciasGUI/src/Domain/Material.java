package Domain;

import java.util.List;
import java.util.ArrayList;
public class Material implements InterfaceEntidade{
	public static List<Material> materiais = new ArrayList();
	private int id;
	private String nome;
	
	public Material() {
		this.id = Material.materiais.size() + 1;
		this.nome = "";
	}
	
	public Material(String nome) {
		this.id = Material.materiais.size() + 1;
		this.nome = nome;
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
	
	@Override
	public void Adicionar() {
		Material.materiais.add(this);
	}
	
	@Override
	public void Excluir() {
		Material.materiais.remove(this);
	}
	
	@Override
	public void Alterar() {
		Material.materiais.remove(this);
		Material.materiais.add(this);
	}
	
	public static void Listar() {
		if(Material.materiais.size() == 0) {
			System.out.println("Nenhum Material cadastrado!");
		}else {

			System.out.println("LISTA DE MATERIAIS ");
			for (Material m : Material.materiais) {
				
				System.out.println("ID: " + m.getId());
				System.out.println("NOME: " + m.getNome());
				System.out.println("===============================" );
			}
		}
	}
	public static Material ConsultarPorId(int id) {
		for (Material m : Material.materiais) {
			if(m.getId() == id) {
				System.out.println("ID: " + m.getId());
				System.out.println("NOME: " + m.getNome());
				
				System.out.println("===============================" );
				return m;
			}
		}
		System.out.println("Material não encontrado!");
		return null;
	}

	public static Material ConsultarPorNome(String nome) {
		for (Material m : Material.materiais) {
			if(m.getNome().equals(nome)) {
				System.out.println("ID: " + m.getId());
				System.out.println("NOME: " + m.getNome());
				
				System.out.println("===============================" );
				return m;
			}
		}
		System.out.println("Material não encontrado!");
		return null;
	}

	
}
