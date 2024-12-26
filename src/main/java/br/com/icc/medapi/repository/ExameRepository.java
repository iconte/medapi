package br.com.icc.medapi.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.icc.medapi.model.CategoriaExame;
import br.com.icc.medapi.model.Exame;

public interface ExameRepository extends PagingAndSortingRepository<Exame, Long>{
	
	public List<Exame> findByCategoriaExame(CategoriaExame categoria);
	

}
