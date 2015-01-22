package br.edu.ifpb.caju.controller;

import java.util.List;

import br.edu.ifpb.caju.model.Voto;

public interface SistemaVotoInterface {
	
	public void cadastraVoto();

	public void removeVoto(Voto voto);

	public void editaVoto(Voto voto);

	public List<Voto> getAllVoto();

}
