package com.ifms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.ModalidadeDTO;
import com.ifms.entities.Modalidade;
import com.ifms.repositories.ModalidadeRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class ModalidadeService {
	
	@Autowired
	private ModalidadeRepository repository;
	
	@Transactional(readOnly = true)
	public List<ModalidadeDTO> findAll() {
	  List<Modalidade> list = repository.findAll();
	   return list.stream().map(modalidade -> new ModalidadeDTO(modalidade)).collect(Collectors.toList());
	}
	@Transactional(readOnly = true)
	public ModalidadeDTO findById(Long iD) {
		Optional<Modalidade> obj = repository.findById(iD);
		Modalidade modalidade = obj.orElseThrow(()->new ResourceNotFoundException("Amodalidade solicitada não foi localizada"));
		return new ModalidadeDTO(modalidade);
	}
	@Transactional
	public ModalidadeDTO insert(ModalidadeDTO dto) {
		Modalidade entity = new Modalidade();
		entity.setDescricao(dto.getDescricao());
		
		entity = repository.save(entity);
		return new ModalidadeDTO(entity);
	}
	@Transactional
	public ModalidadeDTO update(Long iD, ModalidadeDTO dto) {
		try {
			Modalidade entity = repository.getOne(iD);
			entity.setDescricao(dto.getDescricao());
			
			entity = repository.save(entity);
			return new ModalidadeDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id da modalidade não foi localizado");
		}
		
	}
	public void delete(Long iD) {
		try {
			repository.deleteById(iD);
		} catch (EmptyResultDataAccessException e) {
		  throw new ResourceNotFoundException("Não foi possivel deletar,o id da modalidade não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar a modalidade, pois a mesma está em uso");
		}
	}

}
