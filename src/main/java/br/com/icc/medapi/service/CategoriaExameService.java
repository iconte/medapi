package br.com.icc.medapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.icc.medapi.model.CategoriaExame;
import br.com.icc.medapi.model.dto.CategoriaExameDTO;
import br.com.icc.medapi.repository.CategoriaExameRepository;

@Service
public class CategoriaExameService {

	private final CategoriaExameRepository repo;
	private ModelMapper mapper;

	public CategoriaExameService(CategoriaExameRepository repository, ModelMapper mapper) {
		this.repo = repository;
		this.mapper = mapper;
	}

	public Page<CategoriaExameDTO> listarTodos(int number, int size) {
		PageRequest req = PageRequest.of(number, size);
		Page<CategoriaExame> page = repo.findAll(req);
		return converterPageExameParaDTO(page);

	}

	public Page<CategoriaExameDTO> converterPageExameParaDTO(Page<CategoriaExame> pageExame) {
		List<CategoriaExameDTO> exameDTOS = pageExame.getContent().stream()
				.map(exame -> mapper.map(pageExame, CategoriaExameDTO.class)).collect(Collectors.toList());

		return new PageImpl<>(exameDTOS, pageExame.getPageable(), pageExame.getTotalElements());
	}

}
