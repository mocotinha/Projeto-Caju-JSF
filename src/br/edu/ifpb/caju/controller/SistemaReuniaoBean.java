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

import org.primefaces.component.datatable.DataTable;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOReuniao;
import br.edu.ifpb.caju.model.Ata;
import br.edu.ifpb.caju.model.Colegiado;
import br.edu.ifpb.caju.model.Membro;
import br.edu.ifpb.caju.model.Processo;
import br.edu.ifpb.caju.model.Reuniao;

@ManagedBean(name = "reuniaoBean")
@SessionScoped
public class SistemaReuniaoBean {
	private DAOReuniao dao = new DAOReuniao();
	private Reuniao reuniao = new Reuniao();	
	private DataModel<Reuniao> reunioes;
	private List<String> membrosSelecionados;
	private List<Membro> membros;
	private Membro membro;
	private List<String> processosSelecionados;
	private List<Processo> processos;
	private Processo processo;
	private Date novaData;
	SistemaReuniao sisReuniao;
	private Ata ata;
	private DataTable reunioesTable;
	private Reuniao selectedReuniao;
	
	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public String finalizaReuniao(){
		selectedReuniao = (Reuniao) reunioesTable.getRowData();
		//System.out.println(selectedReuniao);
		return "finaliza_reuniao";
	}
	
	public Date getNovaData() {
		return novaData;
	}



	public List<String> getProcessosSelecionados() {
		return processosSelecionados;
	}

	public void setProcessosSelecionados(List<String> processosSelecionados) {
		this.processosSelecionados = processosSelecionados;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
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
	
	public void atualizaReuniao(){
		DAO.open();
		DAO.begin();
		System.out.println(membrosSelecionados);
		reuniao = dao.find(selectedReuniao.getId());
		reuniao.setdataReuniao(novaData);

		List<Membro> mb = getMembroNome();
		
		List<Processo> pces = getProcessoIdProcesso();
		//adiconar membros
		for(Membro m: mb){
			reuniao.addMembro(m);			
		}
			
		for(Processo p: pces){
			reuniao.addProcesso(p);
		}
		dao.merge(reuniao);
		reunioes = getReunioes();
		DAO.close();
	}

	
	public List<Membro> getMembroNome(){
		
		List<Membro> mm = new ArrayList<Membro>();
		
		for(Membro m :  membros){
			for(String ms : membrosSelecionados){
				if(ms.equals(m.getNome())){
					mm.add(m);
				}
			}		
		}
			
		return mm;
	}
	
public List<Processo> getProcessoIdProcesso(){
		
		List<Processo> pr = new ArrayList<Processo>();
		
		
		for(Processo pp :  processos ){
			for(String ms : processosSelecionados){
							
				if(ms == pp.getIdProcesso()){
					pr.add(pp);
				}
			}		
		}
			
		return pr;
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
		this.processos = getListaProcessoAtivos();
	}
		
	public List<Processo> getListaProcessoAtivos(){
		SistemaProcesso sisproc = new SistemaProcesso();
		List<Processo> proc = sisproc.getAllProcessos();
		List<Processo> proc2 = new ArrayList<Processo>();
		
		for(Processo p : proc){
			if(p.getVoto() == null){
				proc2.add(p);
			}
		}
		return proc2; 
	}
	
	public Membro getMembro() {
		return membro;
	}


	public void setMembro(Membro membro) {
		this.membro = membro;
	}

//	public void addMembroSelecionado(){
	//	this.membrosSelecionados.add(this.membro);
//	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}	
	
	public List<String> getMembrosSelecionados() {
		return membrosSelecionados;
	}

	public void setMembrosSelecionados(List<String> membrosSelecionados) {
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
					if(m.isAtivo()){
						membros.add(m);	
					}
					break;
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
			reuniao = new Reuniao();
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

	public DataTable getReunioesTable() {
		return reunioesTable;
	}

	public void setReunioesTable(DataTable reunioesTable) {
		this.reunioesTable = reunioesTable;
	}
}
