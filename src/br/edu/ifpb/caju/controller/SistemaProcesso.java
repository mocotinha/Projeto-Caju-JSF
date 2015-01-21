package br.edu.ifpb.caju.controller;

import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOMembro;
import br.edu.ifpb.caju.dao.DAOProcesso;
import br.edu.ifpb.caju.model.Membro;
import br.edu.ifpb.caju.model.Processo;


@ManagedBean (name ="sistemaProcesso")
@ApplicationScoped
public class SistemaProcesso implements SistemaProcessoInterface{

	private DAOProcesso dao;
	private DAOMembro daoM;
	private Processo processo;
	private List<Processo> processos;
	private String busca;
	
	private Membro membro = new Membro();
	private List<Membro> membros;

	
	public SistemaProcesso() {
		this.dao = new DAOProcesso();
		this.daoM = new DAOMembro();
		processo = new Processo();
		busca = "";
        getAllProcessos();
        getAllMembros();
	}
	
	public List<Membro> getAllMembros(){
		DAO.open();
		DAO.begin();
		this.membros = this.daoM.findAllAtivos();
		DAO.commit();
		DAO.close();
		return this.membros;
		
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public DAOProcesso getDao() {
		return dao;
	}

	public void setDao(DAOProcesso dao) {
		this.dao = dao;
	}

	public Processo getProcesso() {
		
		return processo;
	}

	public void setProcesso(Processo processo) {

		this.processo = processo;
	}
	
	@Override
	public void cadastraProcesso() {
		Membro membroAux;
		DAO.open();
		DAO.begin();
		membroAux = daoM.findMembroById(membro.getId());
		membro.addProcesso(processo);
		dao.merge(processo);
		daoM.merge(membroAux);
		DAO.commit();
		DAO.close();
		membro = new Membro();
		getAllProcessos();
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Processo: Nº " + this.processo.getIdProcesso() + ". Cadastrado/Atualizado com Sucesso!"));
		this.processo = new Processo();
		
		
	}


	@Override
	public void removeProcesso() {
		DAO.open();
		DAO.begin();
		String valor = new String(processo.getIdProcesso()+"");
		this.dao.remove(dao.merge(processo));
		DAO.commit();
		DAO.close();
		getAllProcessos();
		
		FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Processo: Nº " + valor + ". Removido com Sucesso!"));
		this.processo = new Processo();
		
	}

	@Override
	public void editaProcesso() {
		DAO.open();
		DAO.begin();
		this.dao.merge(processo);
		DAO.commit();
		DAO.close();
		
	}

	@Override
	public List<Processo> getAllProcessos() {
		List<Processo> processos;
		DAO.open();
		DAO.begin();
		processos = this.dao.findAll();
		DAO.commit();
		DAO.close();
		this.processos = processos;
		return this.processos;
	}
	
	 


	public void buscaProcessos() {
		@SuppressWarnings("unused")
		List<Processo> processos;
		DAO.open();
		DAO.begin();
		
		//TODO: Precisa pegar o DAO de Amandas
		//processos = this.dao.findByAtributes(busca);
		DAO.close();

		//this.processos = processos;
	}
	
	
	
	public void getProcessoPorProcesso(){
		DAO.open();
		DAO.begin();
		processo = this.dao.find(processo);
		DAO.commit();
		DAO.close();
	}

	@Override
	public List<Processo> getProcessosByAtributes(HashMap<String, String> dados) {
		// TODO Auto-generated method stub
		return null;
	}

}
