package br.edu.ifpb.caju.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOPresidente;
import br.edu.ifpb.caju.model.Presidente;


@ManagedBean(name="sistemaLoginBean")
@SessionScoped
public class SistemaLoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Presidente presidente;
	private DAOPresidente dao;
	private String email;
	private String senha;
	private boolean isLogged = false;
	
	public SistemaLoginBean(){
		this.dao = new DAOPresidente();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Presidente getPresidente() {
		return presidente;
	}

	public void setPresidente(Presidente presidente) {
		this.presidente = presidente;
	}
	
	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public String autenticaLogin(){
		DAO.open();
		DAO.begin();
		presidente = this.dao.findByLogin(email);
		DAO.commit();
		DAO.close();
		if(presidente != null && presidente.getSenha().equals(senha)){
			isLogged = true;
			return "index";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Login e/ou senha invalidos!"));
		return null;
	}
}
