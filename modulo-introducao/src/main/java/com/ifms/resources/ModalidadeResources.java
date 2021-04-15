package com.ifms.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ifms.dto.ModalidadeDTO;
import com.ifms.services.ModalidadeService;


@RestController
@RequestMapping (value = "/modalidades")
public class ModalidadeResources {
	
	@Autowired
	private ModalidadeService service;
	
	@GetMapping
	public ResponseEntity<List<ModalidadeDTO>> findAll(){
		List<ModalidadeDTO> lista = service.findAll();
		
		return ResponseEntity.ok().body(lista);
		
	}
	@GetMapping(value = "/{ID}")
	public ResponseEntity<ModalidadeDTO> findById(@PathVariable Long ID){
	ModalidadeDTO dto = service.findById(ID);
	return ResponseEntity.ok().body(dto);
	} 
	@PostMapping
	public ResponseEntity<ModalidadeDTO> insert(@RequestBody ModalidadeDTO dto){
		dto = service.insert(dto);
		// Quando retornar 201 objeto criado, por padrão retorna também o endereço do recurso
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(dto.getID()).toUri();
		return ResponseEntity.created(uri).body(dto);
	} 
	
	@PutMapping(value = "/{ID}")
	public ResponseEntity<ModalidadeDTO> update(@PathVariable Long ID,@RequestBody ModalidadeDTO dto){
		dto = service.update(ID,dto);
		return ResponseEntity.ok().body(dto);
		
	}
	@DeleteMapping(value = "/{ID}")
	public ResponseEntity<ModalidadeDTO> delete(@PathVariable Long ID){
		service.delete(ID);
		return ResponseEntity.noContent().build();
		
	}
	
	} 


