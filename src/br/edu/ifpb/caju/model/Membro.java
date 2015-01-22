package br.edu.ifpb.caju.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Membro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String perfil;
	private boolean ativo = true;
	@ManyToMany
	private List<Reuniao> reunioes = new ArrayList<Reuniao>();
	
	@ManyToMany(mappedBy="membros")
	private List<Colegiado> colegiados =  new ArrayList<Colegiado>();
	@OneToMany
	private List<Voto> votos = new ArrayList<Voto>();
	
	@OneToMany(mappedBy="relator")
	private List<Processo> processos = new ArrayList<Processo>();
	
	public Membro(){}
	
	
	
	public List<Processo> getProcessos() {
		return processos;
	}



	public void setProcessos(List<Processo> processos) {
		for (Processo processo : processos) {
			addProcesso(processo);
		}
	}



	public void addProcesso(Processo processo) {
		processo.setRelator(this);
		this.processos.add(processo);
		
	}



	public List<Voto> getVotos() {
		return votos;
	}

	public void addVoto(Voto voto){
		this.votos.add(voto);
	}

	public void setVotos(List<Voto> votos) {
		for (Voto voto : votos) {
			addVoto(voto);
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Colegiado> getColegiados() {
		return colegiados;
	}

	public void setColegiados(List<Colegiado> colegiados) {
		for (Colegiado colegiado : colegiados) {
			this.colegiados.add(colegiado);
		}
	}

	public void addColegiado(Colegiado colegiado){
		this.colegiados.add(colegiado);
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addReuniao(Reuniao reuniao){
		this.reunioes.add(reuniao);
	}
	
	public List<Reuniao> getReunioes() {
		return reunioes;
	}

	public void setReunioes(List<Reuniao> reunioes) {
		this.reunioes = reunioes;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result
				+ ((colegiados == null) ? 0 : colegiados.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result
				+ ((processos == null) ? 0 : processos.hashCode());
		result = prime * result
				+ ((reunioes == null) ? 0 : reunioes.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((votos == null) ? 0 : votos.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membro other = (Membro) obj;
		
		if (id != other.id)
			return false;
		
		return true;
	}
	
	

}
