package com.ifms.dto;

import java.io.Serializable;

import com.ifms.entities.Modalidade;

public class ModalidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long ID;
	private String descricao;
	
	public ModalidadeDTO() {}

	public ModalidadeDTO(Long id, String descricao) {
		ID = ID;
		this.descricao = descricao;
	}
	public ModalidadeDTO(Modalidade entity) {
		ID = entity.getID();
		this.descricao = descricao;
	
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
