package br.edu.ifpb.caju.dao;

import javax.persistence.Query;

import br.edu.ifpb.caju.model.Presidente;

public class DAOPresidente extends DAO<Presidente>{
	
	public Presidente findByLogin(String email) {
		Query q = getManager().createQuery("select m from Presidente m where m.login like :login" );
		q.setParameter("login", email);
		try {
			Presidente aux = (Presidente) q.getSingleResult();
			return aux;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void updateAtivo() {
		Query q = getManager().createQuery("UPDATE Presidente p SET p.ativo = :ativo");
		q.setParameter("ativo", false);
		q.executeUpdate();
	}
	
	public Presidente findAtivo(){
		Query q = getManager().createQuery("Select p from Presidente p where p.ativo = :ativo");
		q.setParameter("ativo", true);
		return (Presidente) q.getSingleResult();
	}
	

}
