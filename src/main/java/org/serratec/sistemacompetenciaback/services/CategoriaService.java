package org.serratec.sistemacompetenciaback.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sistemacompetenciaback.dto.CategoriaDTO;
import org.serratec.sistemacompetenciaback.entity.Categoria;
import org.serratec.sistemacompetenciaback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public ResponseEntity<Categoria> listarPorId(Long id) {
		Optional<Categoria> opCategoria = categoriaRepository.findById(id);
		if(opCategoria.isPresent()) {
			return ResponseEntity.ok(opCategoria.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public Categoria inserir(Categoria categoria) {
		Categoria categorias = categoriaRepository.save(categoria);
		return categorias;
	}
	
	@Transactional
	public ResponseEntity<Categoria> atualizar(Long id, Categoria categoria) {
		Optional<Categoria> categorias = categoriaRepository.findById(id);
		if(categorias.isPresent()) {
			if(null != categoria.getNome()) {
				categorias.get().setNome(categoria.getNome());
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.ok(categoria = categoriaRepository.save(categorias.get()));
	}
	
	@Transactional
	public ResponseEntity<Void> deletar(Long id) {
		if(!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	public ResponseEntity<Categoria> save(CategoriaDTO categoriaDTO) {
		Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}
}