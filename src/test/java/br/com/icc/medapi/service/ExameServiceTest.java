package br.com.icc.medapi.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.icc.medapi.model.CategoriaExame;
import br.com.icc.medapi.model.Exame;
import br.com.icc.medapi.model.dto.ExameDTO;
import br.com.icc.medapi.repository.CategoriaExameRepository;
import br.com.icc.medapi.repository.ExameRepository;

@ExtendWith(MockitoExtension.class)
public class ExameServiceTest {

	
	@InjectMocks
	ExameService service;
	
	@Mock
	ExameRepository exameRepo;
	
	@Mock
	CategoriaExameRepository categoriaExameRepo;
	
	
	@Mock
	ModelMapper mapper;
	
	Page<Exame> mockResult;
	
	Page<ExameDTO> mockEmpty;
	
	List<Exame> mockListaCategoria;
	
	@BeforeEach
	void init() {
		
		List<Exame> lista = List.of(new Exame(), new Exame());
		this.mockEmpty = Page.empty();
		this.mockResult = new PageImpl<>(lista);
		this.mockListaCategoria = lista;
	}
	
	@Test
	@DisplayName("deveRetornarListaExameslistarTodos")
	public void deveRetornarListaExameslistarTodos() throws Exception {
		Pageable req = PageRequest.of(0, 2);
		Mockito.when(exameRepo.findAll(req))
		.thenReturn(mockResult);
		
		Page<ExameDTO> result = service.listarTodos(0,2);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2,result.getTotalElements());
	}
	
	@Test
	@DisplayName("deveRetornarListaExameslistarPorCategoria")
	public void deveRetornarListaExameslistarPorCategoria() throws Exception {
		CategoriaExame categoria = new CategoriaExame();
		categoria.setId(1L);
		Mockito.when(categoriaExameRepo.findById(1L))
		.thenReturn(categoria);
		Mockito.when(exameRepo.findByCategoriaExame(categoria))
		.thenReturn(mockListaCategoria);
		
		Page<ExameDTO> result = service.listarPorCategoria(1L,0,10);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2,result.getTotalElements());
	}
	
}
