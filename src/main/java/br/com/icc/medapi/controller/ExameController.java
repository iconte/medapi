package br.com.icc.medapi.controller;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.icc.medapi.controller.utils.ControllerUtils;
import br.com.icc.medapi.model.dto.ExameDTO;
import br.com.icc.medapi.service.ExameService;

@RestController
@RequestMapping("/exames")
public class ExameController {

  private final ExameService service;

  public ExameController(ExameService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Map<String, Object>> listarTodos(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {

    Page<ExameDTO> resultado = service.listarTodos(page, size);

    if (resultado == null || resultado.isEmpty()) {
      return new ResponseEntity<>(ControllerUtils.getRetornoSimplificado(resultado), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(ControllerUtils.getRetornoSimplificado(resultado), HttpStatus.OK);
  }

  @GetMapping("/categoria/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Map<String, Object>> listarPorCategoria(
      @PathVariable("id") Long categoriaExameId,

      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {

    Page<ExameDTO> resultado = service.listarPorCategoria(categoriaExameId, page, size);
    return new ResponseEntity<>(ControllerUtils.getRetornoSimplificado(resultado), HttpStatus.OK);

  }

}
