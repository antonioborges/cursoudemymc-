package br.com.codigolivre.cursoudemymc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codigolivre.cursoudemymc.domain.Categoria;
import br.com.codigolivre.cursoudemymc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoriaId = categoriaRepository.findById(id);
		return categoriaId.orElse(null);

	}

}
