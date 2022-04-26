package Domain;

import java.util.ArrayList;
import java.util.List;

public class Organizador extends Participante  implements InterfaceEntidade {
	public static List<Organizador> organizadores = new ArrayList();
	private String email;
	private String telefone;
		
	public Organizador() {
		super();
		this.email = "";
		this.telefone = "";
	}
	
	public Organizador(int id, String email, String telefone, int idEquipe, String nome, String idade, String funcao) {
		super(id, idEquipe, nome, idade, funcao);
		this.email = email;
		this.telefone = telefone;
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
	
	@Override
	public void Adicionar() {
		// TODO Auto-generated method stub
		super.Adicionar();
		Organizador.organizadores.add(this);
	}
	
	@Override
	public void Excluir() {
		// TODO Auto-generated method stub
		super.Excluir();
		Organizador.organizadores.remove(this);
	}
	
	@Override
	public void Alterar() {
		// TODO Auto-generated method stub
		super.Alterar();
		Organizador.organizadores.remove(this);
		Organizador.organizadores.add(this);
	}
	
	public static void Listar() {
		if(Organizador.organizadores.size() == 0) {
			System.out.println("Nenhum participante cadastrado!");
		}else {
			for (Organizador o : Organizador.organizadores) {
				System.out.println("ID: " + o.getId());
				System.out.println("IDADE: " + o.getIdade());
				System.out.println("NOME: " + o.getNome());
				System.out.println("ID EQUIPE: " + o.getIdEquipe());
				System.out.println("FUNÇÃO: " + o.getFuncao());
				System.out.println("Email: " + o.getEmail());
				System.out.println("Telefone: " + o.getTelefone());

				System.out.println("===============================" );
			}
		}
	}
	
	public static Organizador ConsultarPorNome(String nome) {
		for (Organizador o : Organizador.organizadores) {
			if(o.getNome().equals(nome)) {
				System.out.println("ID: " + o.getId());
				System.out.println("IDADE: " + o.getIdade());
				System.out.println("NOME: " + o.getNome());
				System.out.println("ID EQUIPE: " + o.getIdEquipe());
				System.out.println("FUNÇÃO: " + o.getFuncao());
				System.out.println("TELEFONE: " + o.getTelefone());
				System.out.println("EMAIL: " + o.getEmail());
				System.out.println("===============================" );
				return o;
			}	
		}
		
		System.out.println("Organizador não encontrado!");
		return null;
	}
	
	
	
	
	
}
