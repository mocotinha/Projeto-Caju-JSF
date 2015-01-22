package br.edu.ifpb.caju.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Processo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String idProcesso;
	private String matRequerente;
	private String nomeRequerente;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDoc;
	private String assunto;
	private String periodo;
	@ManyToOne
	private Reuniao reuniao;
	@OneToOne
	private Voto voto;
	@ManyToOne
	private Membro relator;
	
	public Processo(){}
	
	
	
	public Membro getRelator() {
		return relator;
	}



	public void setRelator(Membro relator) {
		this.relator = relator;
	}



	public Voto getVoto() {
		return voto;
	}



	public void setVoto(Voto voto) {
		this.voto = voto;
	}



	public Reuniao getReuniao() {
		return reuniao;
	}



	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}



	public String getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getMatRequerente() {
		return matRequerente;
	}

	public void setMatRequerente(String matRequerente) {
		this.matRequerente = matRequerente;
	}

	public String getNomeRequerente() {
		return nomeRequerente;
	}

	public void setNomeRequerente(String nomeRequerente) {
		this.nomeRequerente = nomeRequerente;
	}

	public Date getDataDoc() {
		return dataDoc;
	}

	public void setDataDoc(Date dataDoc) {
		this.dataDoc = dataDoc;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Processo [id=" + id + ", idProcesso=" + idProcesso
				+ ", matRequerente=" + matRequerente + ", nomeRequerente="
				+ nomeRequerente + ", dataDoc=" + dataDoc + ", assunto="
				+ assunto + ", periodo=" + periodo + ", reuniao=" + reuniao
				+ ", voto=" + voto + ", relator=" + relator + "]";
	}
	
	
	
	
	
}
