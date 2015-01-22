package br.edu.ifpb.caju.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import javax.faces.context.FacesContext;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOColegiado;
import br.edu.ifpb.caju.dao.DAOMembro;
import br.edu.ifpb.caju.model.Colegiado;
import br.edu.ifpb.caju.model.Membro;

@ManagedBean(name="membroColegiadoBean")
@SessionScoped
public class SistemaMembroColegiadoBean {
	private List<Membro> membros = new ArrayList<Membro>();
	private List<Membro> selecionados;
	private Membro membro;
	private List<Membro> filtrados;
	private SistemaMembro sm;
	private Colegiado colegiado;
	private String colegiadoID = new String();
	private DAOColegiado dc = new DAOColegiado();
	private DAOMembro dm = new DAOMembro();
	private boolean membrosEmpty;
	
	public SistemaMembroColegiadoBean() {
		inicializa();
		
	}
	
	public void inicializa(){
		sm = new SistemaMembro();
		membros = sm.getAllMembros();
		filtrados = membros;
		colegiado = new Colegiado();
		if(dc.findAtivo()!=null){
			this.colegiado = dc.findAtivo();
		}else{
			colegiado = new Colegiado();
		}
		selecionados = new ArrayList<Membro>();
		for(Membro mem: colegiado.getMembros()){
			selecionados.add(mem);
			membros.remove(mem);
			filtrados.remove(mem);
		}
		this.membrosEmpty = (selecionados.size()==0);
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
		
		DAO.open();
		DAO.begin();
//		ArrayList<Membro> membrosBanco = (ArrayList<Membro>) sm.getAllMembros();
//		for(Membro m:membrosBanco){
//			m.setAtivo(false);
//			
//		}
//		dm.desativaMembros();
		for(Membro mem : selecionados){
			colegiado.addMembro(mem);
			mem.addColegiado(colegiado);
			mem.setAtivo(true);
			
			dm.merge(mem);
		}
		dc.merge(colegiado);
		DAO.commit();
		DAO.close();
		membrosEmpty = false;
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Membros atribuidos ao colegiado com sucesso.",  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

	public boolean isMembrosEmpty() {
		return membrosEmpty;
	}

	public void setMembrosEmpty(boolean membrosEmpty) {
		this.membrosEmpty = membrosEmpty;
	}

	
	
}

