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
import br.com.icc.medapi.model.dto.CategoriaExameDTO;
import br.com.icc.medapi.repository.CategoriaExameRepository;

@ExtendWith(MockitoExtension.class)
public class CategoriaExameServiceTest {

	
	@InjectMocks
	CategoriaExameService service;
	
	@Mock
	CategoriaExameRepository categoriaExameRepo;
	
	
	@Mock
	ModelMapper mapper;
	
	Page<CategoriaExame> mockResult;
	
	Page<CategoriaExameDTO> mockEmpty;
	
	List<CategoriaExame> mockListaCategoria;
	
	@BeforeEach
	void init() {
		
		List<CategoriaExame> lista = List.of(new CategoriaExame(), new CategoriaExame());
		this.mockEmpty = Page.empty();
		this.mockResult = new PageImpl<>(lista);
		this.mockListaCategoria = lista;
	}
	
	@Test
	@DisplayName("deveRetornarListaExameslistarTodos")
	public void deveRetornarListaExameslistarTodos() throws Exception {
		Pageable req = PageRequest.of(0, 2);
		Mockito.when(categoriaExameRepo.findAll(req))
		.thenReturn(mockResult);
		
		Page<CategoriaExameDTO> result = service.listarTodos(0,2);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2,result.getTotalElements());
	}
	
	
	
}
