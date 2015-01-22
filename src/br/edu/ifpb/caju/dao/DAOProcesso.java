package br.edu.ifpb.caju.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.caju.model.Processo;

public class DAOProcesso extends DAO<Processo>{

	@SuppressWarnings("unchecked")
	public List<Processo> findByAtributes(String texto) {
		Query q = getManager().createQuery("select p from Processo p where p.nomeRequerente like :nomeRequerente OR "+
	"p.idProcesso like :idProcesso OR p.matRequerente like :matRequerente OR p.periodo like :periodo" );
		
		q.setParameter("idProcesso", texto+"%");
		
		
		q.setParameter("matRequerente", texto+"%");
			
		
		q.setParameter("nomeRequerente", texto+"%");
		
		q.setParameter("periodo", texto+"%");
		
		return  q.getResultList();
		
	}

}
