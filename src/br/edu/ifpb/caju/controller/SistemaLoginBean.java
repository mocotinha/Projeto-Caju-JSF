package br.edu.ifpb.caju.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOPresidente;
import br.edu.ifpb.caju.model.Presidente;
import br.edu.ifpb.caju.util.JavaMail;


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
	
	public void recuperarSenha(){
		JavaMail jm = new JavaMail();
		String emailR = jm.enviaEmailEsqueceuSenha();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "A senha foi enviada para o email: " + emailR));
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
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			isLogged = true;
			session.setAttribute("login", presidente);
			session.setAttribute("logado", isLogged);
			return "index";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Login e/ou senha invalidos!"));
		return null;
	}
	
	public String efetuarLogout() {
		isLogged = false;
	    FacesContext fc = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	    session.invalidate();
	    return "login";
	}
}
