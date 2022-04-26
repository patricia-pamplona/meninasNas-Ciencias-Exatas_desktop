package Domain;

import java.util.ArrayList;
import java.util.List;

import Persistence.EquipeDAO;
import Persistence.ParticipanteDAO;

public class Participante extends Pessoa implements InterfaceEntidade{
		public static List<Participante> participantes = new ArrayList();
		private ParticipanteDAO paDAO;
		final private int id;
		private int idEquipe;
		private String funcao;
		
		public Participante() {
			this.id = Participante.participantes.size() + 1;
			this.idEquipe = 0;
			this.nome = "";
			this.idade = "";
			this.funcao = "";
			paDAO = new ParticipanteDAO();
		}
		
		public Participante(int id, int idEquipe, String nome, String idade, String funcao) {
			this.id = id;
			//this.id = Participante.participantes.size() + 1;
			this.idEquipe = idEquipe;
			this.nome = nome;
			this.idade = idade;
			this.funcao = funcao;
			paDAO = new ParticipanteDAO();
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

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getIdade() {
			return idade;
		}

		public void setIdade(String idade) {
			this.idade = idade;
		}

		public String getFuncao() {
			return funcao;
		}

		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}
		
		public void Adicionar() {
			paDAO.Adicionar(this);
			/*Participante.participantes.add(this);
			*/
		}
		
		public void Excluir() {
			paDAO.Excluir(this);
			/*Participante.participantes.remove(this);
			*/
			System.out.println("Participante excluído!");
		}
		
		public void Alterar() {
			paDAO.Alterar(this);
			/*Participante.participantes.remove(this);
			Participante.participantes.add(this);
			*/
		}
		
		public static void Listar() {
			ParticipanteDAO paDAO = new ParticipanteDAO();
			
			Participante.participantes = paDAO.listar();
			
			if(Participante.participantes.size() == 0) {
				System.out.println("Nenhum participante cadastrado!");
			}else {
				for (Participante p : Participante.participantes) {
					System.out.println("ID: " + p.getId());
					System.out.println("IDADE: " + p.getIdade());
					System.out.println("NOME: " + p.getNome());
					System.out.println("ID EQUIPE: " + p.getIdEquipe());
					System.out.println("FUNÇÃO: " + p.getFuncao());
					System.out.println("===============================" );
				}
			}
		}
		
		public static Participante ConsultarPorNome(String nome) {
			ParticipanteDAO paDAO = new ParticipanteDAO();
			Participante pa =  paDAO.consultarPorNome(nome);
			if(pa != null) {
				System.out.println("ID: " + pa.getId());
				System.out.println("DATA_NASCIMENTO: " + pa.getIdade());
				System.out.println("NOME: " + pa.getNome());
				System.out.println("ID EQUIPE: " + pa.getIdEquipe());
				System.out.println("FUNÇÃO: " + pa.getFuncao());
				System.out.println("===============================" );
			}
			return pa;
			
			/*for (Participante p : Participante.participantes) {
				if(p.getNome().equals(nome)) {
					System.out.println("ID: " + p.getId());
					System.out.println("IDADE: " + p.getIdade());
					System.out.println("NOME: " + p.getNome());
					System.out.println("ID EQUIPE: " + p.getIdEquipe());
					System.out.println("FUNÇÃO: " + p.getFuncao());
					System.out.println("===============================" );
					return p;
				}	
			}
			
			System.out.println("Participante não encontrado!");
			return null;
			*/
		}
		
		
	}
	
		

