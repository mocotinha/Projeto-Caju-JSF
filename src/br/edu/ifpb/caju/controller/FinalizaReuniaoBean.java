package br.edu.ifpb.caju.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.caju.model.Reuniao;


@ManagedBean(name = "finalizaReuniaoBean")
@SessionScoped
public class FinalizaReuniaoBean {
	private Reuniao reuniao;

	
	
	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
	

}
