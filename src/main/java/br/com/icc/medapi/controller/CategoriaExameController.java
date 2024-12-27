package br.com.icc.medapi.controller;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.icc.medapi.controller.utils.ControllerUtils;
import br.com.icc.medapi.model.dto.CategoriaExameDTO;
import br.com.icc.medapi.service.CategoriaExameService;

@RestController
@RequestMapping("/categorias-exame")
public class CategoriaExameController {

  private CategoriaExameService service;

  public CategoriaExameController(CategoriaExameService service) {
    this.service = service;

  }

  @GetMapping
  public ResponseEntity<Map<String, Object>> listarTodos(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<CategoriaExameDTO> resultado = service.listarTodos(page, size);

    return new ResponseEntity<>(ControllerUtils.getRetornoSimplificado(resultado), HttpStatus.OK);

  }



}
