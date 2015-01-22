package br.edu.ifpb.caju.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Presidente extends Membro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	
	public Presidente(){}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		
		this.senha = senha;
	}
	
	
}
