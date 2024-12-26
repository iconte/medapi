package br.com.icc.medapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import br.com.icc.medapi.exception.NegocioException;
import br.com.icc.medapi.model.CategoriaExame;
import br.com.icc.medapi.model.Exame;
import br.com.icc.medapi.model.dto.ExameDTO;
import br.com.icc.medapi.repository.CategoriaExameRepository;
import br.com.icc.medapi.repository.ExameRepository;

@Service
public class ExameService {

	private final ExameRepository repo;
	private final CategoriaExameRepository categoriaExameRepo;
	private final ModelMapper mapper;

	public ExameService(ExameRepository eRepo, CategoriaExameRepository cRepo, ModelMapper mapper) {
		this.repo = eRepo;
		this.categoriaExameRepo = cRepo;
		this.mapper = mapper;
	}

	public Page<ExameDTO> listarTodos(int number, int size) {

		Pageable req = PageRequest.of(number, size);
		Page<Exame> page = repo.findAll(req);
		return converterPageExameParaDTO(page);

	}

	public Page<ExameDTO> listarPorCategoria(Long categoriaExameId, int number, int size) {

		Optional<CategoriaExame> categoria = Optional.ofNullable(categoriaExameRepo.findById(categoriaExameId));
		if (!categoria.isPresent()) {
			throw new NegocioException(getMensagemCategoriaNaoEncontrada(categoriaExameId));
		}
		List<Exame> list = repo.findByCategoriaExame(categoria.get());
		Pageable req = PageRequest.of(number, size);
		Page<Exame> page = new PageImpl<>(list, req, list.size());
		return converterPageExameParaDTO(page);

	}

	private Page<ExameDTO> converterPageExameParaDTO(Page<Exame> pageExame) {
		Optional<Page<Exame>> opPageExame = Optional.ofNullable(pageExame);
		boolean pageEmpty = !opPageExame.isPresent() || !opPageExame.get().hasContent();
		if (pageEmpty) {
			return Page.empty();
		}
		List<ExameDTO> exameDTOS = pageExame.getContent().stream().map(exame -> mapper.map(exame, ExameDTO.class))
				.collect(Collectors.toList());
		return new PageImpl<>(exameDTOS, pageExame.getPageable(), pageExame.getTotalElements());

	}
	
	private String getMensagemCategoriaNaoEncontrada(Long id) {
		return String.format("Categoria %d não encontrada.", id);
	}

}
