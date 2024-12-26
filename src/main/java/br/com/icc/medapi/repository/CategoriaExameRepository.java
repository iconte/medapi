package br.com.icc.medapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.icc.medapi.model.CategoriaExame;


public interface CategoriaExameRepository extends PagingAndSortingRepository<CategoriaExame, Long>{

		public CategoriaExame findById(Long id);
	
}
