package br.edu.ifpb.caju.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.edu.ifpb.caju.controller.SistemaColegiadoBean;
import br.edu.ifpb.caju.controller.SistemaReuniao;
import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOReuniao;
import br.edu.ifpb.caju.model.Colegiado;
import br.edu.ifpb.caju.model.Membro;
import br.edu.ifpb.caju.model.Reuniao;

@ManagedBean(name = "reuniaoBean")
@SessionScoped
public class SistemaReuniaoBean {
	private DAOReuniao dao = new DAOReuniao();
	private Reuniao reuniao = new Reuniao();	
	private DataModel<Reuniao> reunioes;
	private List<Membro> membrosSelecionados;
	private List<Membro> membros;
	private Membro membro;
	private Date novaData;
	SistemaReuniao sisReuniao;
	
	public String finalizaReuniao(){
		return "finalizaReuniao";
	}
	
	public Date getNovaData() {
		return novaData;
	}



	public void setNovaData(Date novaData) {
		this.novaData = novaData;
	}



	public void atualizaData(){
		DAO.open();
		DAO.begin();
		System.out.println(reuniao);
		reuniao = dao.find(reuniao.getId());
		reuniao.setDataAgenda(novaData);
		dao.merge(reuniao);
		reunioes = getReunioes();
		DAO.close();
	}

	
	
	public DAOReuniao getDao() {
		return dao;
	}

	public void setDao(DAOReuniao dao) {
		this.dao = dao;
	}

	//construtor
	public SistemaReuniaoBean() {
		sisReuniao = new SistemaReuniao();
		this.membros = getListaDeMembrosAtivos();
	}
		
	public Membro getMembro() {
		return membro;
	}


	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public void addMembroSelecionado(){
		this.membrosSelecionados.add(this.membro);
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}	
	
	public List<Membro> getMembrosSelecionados() {
		return membrosSelecionados;
	}

	public void setMembrosSelecionados(List<Membro> membrosSelecionados) {
		this.membrosSelecionados = membrosSelecionados;
	}

	public List<Membro> getListaDeMembrosAtivos(){
		
		//pegar a lista do colegiado
		SistemaColegiadoBean colegiado = new SistemaColegiadoBean();
		List<Colegiado> col = colegiado.getAllColegiado();
				
		Colegiado c = new Colegiado();
		//pegar o colegiado ativo
		for(Colegiado coleg: col){
			if(coleg.isAtivo()){
				c = coleg;				
			}
			break;
		}
		
		//Criar um array de membros
		List<Membro> membros = new ArrayList<Membro>();
		for(Membro m : c.getMembros()){
			membros.add(m);			
		}		
		return membros;
	}
	
	
	
	public DataModel<Reuniao> getReunioes() {
		SistemaReuniao sisReuniao = new SistemaReuniao();
		
		try {
			List<Reuniao> lista = sisReuniao.getAllReunioes();
			reunioes = new ListDataModel<Reuniao>(lista);
		} catch (Exception e) {
			
		}
		return reunioes;
	}
	
	public void setReunioes(DataModel<Reuniao> r){
		this.reunioes = r;
	}

	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
	
	public String cadastraReuniao(){
		String retorno = "erro";
		
		FacesContext context = FacesContext.getCurrentInstance();		
		SistemaReuniao sisReuniao = new SistemaReuniao();		
		
		try{
			sisReuniao.cadastraReuniao(reuniao);
			FacesMessage faceMessage = new FacesMessage("Reuniao salva com Sucesso");
			context.addMessage(null,faceMessage);
			retorno = "sucesso";
			reuniao = null;
		}catch(Exception e){
			
		}
		
		return retorno;		
	}

	
	public void excluirReuniao(){
		DAO.open();		
		DAO.begin();
		dao.remove(dao.merge(reuniao));
		DAO.commit();
		DAO.close();
		
	
	}	

	public String atualizarReuniao(){
		String retorno = "erro";
		
//		FacesContext context = FacesContext.getCurrentInstance();		
		SistemaReuniao sisReuniao = new SistemaReuniao();		
		
		try{
			sisReuniao.editaReuniao(reuniao);
//			FacesMessage faceMessage = new FacesMessage("Reuniao atualizada com Sucesso");
//			context.addMessage(null,faceMessage);
			retorno = "lista_reuniao";			
		}catch(Exception e){
			
		}
		
		return retorno;		
	}	
	
	public String selecionarReuniao(){
		this.reuniao = reunioes.getRowData();
		return new String(reuniao.getId()+"");
	}
	
	public void novo(){
		this.reuniao = new Reuniao();
	}
}
