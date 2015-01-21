package br.edu.ifpb.caju.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOColegiado;
import br.edu.ifpb.caju.model.Colegiado;
import br.edu.ifpb.caju.model.Membro;
import br.edu.ifpb.caju.model.Reuniao;

@ManagedBean(name = "colegiadoBean")
@ViewScoped
public class SistemaColegiadoBean implements SistemaColegiadoInterface {
	private DAOColegiado dao;
	private Colegiado colegiado;
	private List<Colegiado> colegiados = new ArrayList<Colegiado>();
	private List<Reuniao> reunioes = new ArrayList<Reuniao>();
	private List<Membro> membros = new ArrayList<Membro>();
	private Colegiado colegiadoSelecionado;
	
	@ManagedProperty("#{membroColegiadoBean}")
	private SistemaMembroColegiadoBean smcb;
	
	public SistemaMembroColegiadoBean getSmcb() {
		return smcb;
	}

	public void setSmcb(SistemaMembroColegiadoBean smcb) {
		this.smcb = smcb;
	}

	public SistemaColegiadoBean() {
		this.dao = new DAOColegiado();
		colegiado = new Colegiado();
		colegiadoSelecionado = new Colegiado();
		
	}
	
	@Override
	public void cadastraColegiado() {
		colegiado.setDataIni(new Date());
		colegiado.setAtivo(true);
		DAO.open();
		DAO.begin();
		dao.updateAtivo();
		this.dao.persist(colegiado);
		DAO.commit();
		DAO.close();
		smcb.inicializa();
	}

	@Override
	public void removeColegiado(Colegiado colegiado) {
		//Verifica apenas se o colegiado esta setado como ativo ou não e atualiza o banco
		colegiado.setAtivo(false);
		DAO.open();
		DAO.begin();
		this.dao.merge(colegiado);
		DAO.commit();
		DAO.close();

	}

	@Override
	public void editaColegiado(Colegiado colegiado) {
		DAO.open();
		DAO.begin();
		this.dao.merge(colegiado);
		DAO.commit();
		DAO.close();

	}
	
	public void handload(Colegiado colegiado){
		DAO.open();
		DAO.begin();
		this.colegiado = this.dao.findById(colegiado.getId());
		DAO.commit();
		DAO.close();
		RequestContext.getCurrentInstance().execute("editDialog.show();");

	}

	@Override
	public List<Colegiado> getAllColegiado() {
		DAO.open();
		DAO.begin();
		colegiados = this.dao.findAll();
		DAO.commit();
		DAO.close();
		return colegiados;
	}
	
	public Colegiado getColegiado() {
		return colegiado;
	}

	public void setColegiado(Colegiado colegiado) {
		this.colegiado = colegiado;
	}

	public List<Colegiado> getColegiados() {
		return colegiados;
	}
	
	public List<Reuniao> getReunioes(){
		return reunioes;
	}
	
	public List<Membro> getMembros(){
		return membros;
	}

	public void setColegiados(List<Colegiado> colegiados) {
		this.colegiados = colegiados;
	}
	
	public void setMembros(List<Membro> membros){
		this.membros = membros;
	}
	
	public void setReunioes (List<Reuniao> reunioes){
		this.reunioes = reunioes;
	}
	
	public Colegiado getColegiadoSelecionado() {
		return colegiadoSelecionado;
	}

	public void setColegiadoSelecionado(Colegiado colegiadoSelecionado) {
		this.colegiadoSelecionado = colegiadoSelecionado;
	}

}
