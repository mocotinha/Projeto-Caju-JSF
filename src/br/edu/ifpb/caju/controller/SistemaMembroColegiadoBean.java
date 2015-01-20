package br.edu.ifpb.caju.controller;

import java.util.ArrayList;
import java.util.Map;

import br.edu.ifpb.caju.model.Colegiado;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.caju.controller.SistemaColegiadoBean;
import br.edu.ifpb.caju.dao.DAOColegiado;
import br.edu.ifpb.caju.model.Membro;

@ManagedBean(name="membroColegiadoBean")
@SessionScoped
public class SistemaMembroColegiadoBean {
	private List<Membro> membros = new ArrayList();;
	private List<Membro> selecionados = new ArrayList<Membro>();
	private Membro membro;
	private List<Membro> filtrados;
	private SistemaMembro sm;
	private Colegiado colegiado;
	private String colegiadoID = new String();

	
	
	
	public SistemaMembroColegiadoBean() {
		
		sm = new SistemaMembro();
		membros = sm.getAllMembros();
		filtrados = membros;
		colegiado = new Colegiado();
		
		DAOColegiado dc = new DAOColegiado();
		this.colegiado = dc.findAtivo();
		System.out.println(colegiado.getId());	
	}
	
	public void adicionaMembro(){
		selecionados.add(membro);
		membros.remove(membro);
		filtrados.remove(membro);

	}
	public void removeMembro(){
		Membro m = membro;
		selecionados.remove(m);
		membros.add(m);

	}
	public void salvarMembros(){
		
	}

	public List<Membro> getMembros() {
		return membros;
	}


	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}


	public List<Membro> getSelecionados() {
		return selecionados;
	}


	public void setSelecionados(List<Membro> selecionados) {
		this.selecionados = selecionados;
	}


	public Membro getMembro() {
		return membro;
	}


	public void setMembro(Membro membro) {
		this.membro = membro;
	}


	public List<Membro> getFiltrados() {
		return filtrados;
	}


	public void setFiltrados(List<Membro> filtrados) {
		this.filtrados = filtrados;
	}
	public Colegiado getColegiado() {
		return colegiado;
	}
	public void setColegiado(Colegiado colegiado) {
		this.colegiado = colegiado;
	}
	
//	public void getExternalColegiado(){
//		FacesContext fc = FacesContext.getCurrentInstance();
//		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
//		String id = params.get("colegiadoID");
//		if(id!=null){
//			DAOColegiado dc = new DAOColegiado();
//			this.colegiado = dc.findById(Integer.parseInt(id));	
//		}
//	}
	
	public String getColegiadoID() {
		return colegiadoID;
	}
	public void setColegiadoID(String colegiadoID) {
		this.colegiadoID = colegiadoID;
		
		
	}
	
	
}

