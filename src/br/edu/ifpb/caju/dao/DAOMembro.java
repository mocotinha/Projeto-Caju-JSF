package br.edu.ifpb.caju.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.caju.model.Colegiado;
import br.edu.ifpb.caju.model.Membro;

public class DAOMembro extends DAO<Membro>{
	
	public Membro findByNome(String text) {
		Query q = getManager().createQuery("select m from Membro m where m.nome like :atributo" );
		q.setParameter("atributo", text);
		return (Membro) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Membro> findAllAtivos() {
		Query q = getManager().createQuery("select m from Membro m where m.ativo = :atributo" );
		q.setParameter("atributo", true);
		return q.getResultList();
	}
	
	public Membro findMembroById(int id) {
		Query q = getManager().createQuery("select m from Membro m where m.id = :id" );
		q.setParameter("id", id);
		return (Membro) q.getSingleResult();
	}
	
	public void desativaMembros() {
		Query q = getManager().createQuery("UPDATE Membro m SET m.ativo = :false" );
		q.setParameter("false", false);
		q.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Membro> getAllPorColegiado(Colegiado colegiado){
		System.out.println(colegiado);
		Query q = getManager().createQuery("SELECT m FROM Colegiado c JOIN FETCH c.membros m WHERE c = :colegiado");
		q.setParameter("colegiado", colegiado);
		List<Membro> aux =  q.getResultList();
		for (Membro membro : aux) {
			System.out.println(membro);
		}
		return aux;
	}

}
