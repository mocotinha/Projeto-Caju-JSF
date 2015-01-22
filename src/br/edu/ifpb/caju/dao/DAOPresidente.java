package br.edu.ifpb.caju.dao;

import java.io.Serializable;

import javax.persistence.Query;

import br.edu.ifpb.caju.model.Presidente;


public class DAOPresidente extends DAO<Presidente> implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	public Presidente findByLogin(String email) {
		Query q = getManager().createQuery("select m from Presidente m where m.login like :login and m.ativo = :true" );
		q.setParameter("login", email);
		q.setParameter("true", true);
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
