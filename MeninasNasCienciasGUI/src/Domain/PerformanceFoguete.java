package Domain;

import java.util.ArrayList;
import java.util.List;

public class PerformanceFoguete {
	public static List<PerformanceFoguete> performances = new ArrayList();
	final private int id;
	private int idFoguete;
	private int velocidade;
	private int distancia;
	private int altura;
	
	public PerformanceFoguete() {
		this.id = PerformanceFoguete.performances.size() + 1;
		this.idFoguete = 0;
		this.velocidade = 0;
		this.distancia = 0;
		this.altura = 0;
	}
	
	public PerformanceFoguete(int id, int idFoguete, int velocidade, int distancia, int altura) {
		this.id = PerformanceFoguete.performances.size() + 1;
		this.idFoguete = idFoguete;
		this.velocidade = velocidade;
		this.distancia = distancia;
		this.altura = altura;
	}

	public int getId() {
		return id;
	}

	public int getIdFoguete() {
		return idFoguete;
	}

	public void setIdFoguete(int idFoguete) {
		this.idFoguete = idFoguete;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public void Adicionar() {
		PerformanceFoguete.performances.add(this);
	}
	
	public void Excluir() {
		PerformanceFoguete.performances.remove(this);
		System.out.println("Performance excluída!");
	}
	
	public void Alterar() {
		PerformanceFoguete.performances.remove(this);
		PerformanceFoguete.performances.add(this);
	}
	
	public static void Listar() {
		if(PerformanceFoguete.performances.size() == 0) {
			System.out.println("Nenhuma performance cadastrada!");
		}else {
			for (PerformanceFoguete perf : PerformanceFoguete.performances) {
				System.out.println("ID: " + perf.getId());
				System.out.println("ID FOGUETE: " + perf.getIdFoguete());
				System.out.println("ALTURA: " + perf.getAltura());
				System.out.println("DISTÂNCIA: " + perf.getDistancia());
				System.out.println("VELOCIDADE: " + perf.getVelocidade());
				System.out.println("===============================" );	
			}
		}
	}
	
	public static PerformanceFoguete ConsultarPorId (int id) {
		for (PerformanceFoguete perf : PerformanceFoguete.performances) {
			if(perf.getId() == (id)) {
				System.out.println("ID FOGUETE: " + perf.getIdFoguete());
				System.out.println("ID: " + perf.getId());
				System.out.println("ALTURA: " + perf.getAltura());
				System.out.println("DISTÂNCIA: " + perf.getDistancia());
				System.out.println("VELOCIDADE: " + perf.getVelocidade());	
				System.out.println("===============================" );
				return perf;
			}
		}
		System.out.println("ID Foguete não encontrado!");
		return null;
	}
	
}
