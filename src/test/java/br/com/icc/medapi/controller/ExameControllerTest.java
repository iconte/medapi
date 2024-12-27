package br.com.icc.medapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import br.com.icc.medapi.exception.NegocioException;
import br.com.icc.medapi.model.dto.ExameDTO;
import br.com.icc.medapi.repository.CategoriaExameRepository;
import br.com.icc.medapi.repository.ExameRepository;
import br.com.icc.medapi.service.ExameService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ExameController.class)
public class ExameControllerTest {

  @MockBean
  ExameService service;

  @MockBean
  CategoriaExameRepository repository;

  @MockBean
  ExameRepository repository2;

  @Autowired
  private MockMvc mockMvc;

  private Page<ExameDTO> mockResult = null;

  private Page<ExameDTO> mockEmpty = null;

  @BeforeEach
  void init() {

    List<ExameDTO> lista = List.of(new ExameDTO(), new ExameDTO());
    this.mockEmpty = Page.empty();
    this.mockResult = new PageImpl<>(lista);

  }

  @Test
  public void mockMvcIsNotNull() throws Exception {
    Assertions.assertNotNull(mockMvc);
  }

  @Test
  public void serviceIsNotNull() throws Exception {
    Assertions.assertNotNull(service);
  }

  @Test
  @DisplayName("deveRetornarListaExames")
  public void deveRetornarListaExames() throws Exception {
    Mockito.when(service.listarTodos(0, 1))
        .thenReturn(new PageImpl<>(List.of(new ExameDTO()), PageRequest.of(0, 1), 1));

    mockMvc.perform(get("/exames").param("page", "0").param("size", "1"))
        .andExpect(status().isOk());

  }

  @Test
  @DisplayName("deveRetornar404ListaExames")
  public void deveRetornar404ListaExames() throws Exception {

    Mockito.when(service.listarTodos(0, 1)).thenReturn(this.mockEmpty);
    mockMvc.perform(get("/exames").param("page", "0").param("size", "1"))
        .andExpect(status().isNotFound());

  }

  @Test
  @DisplayName("deveRetornar404FIltroCategoriaInvalida")
  public void deveRetornar404FIltroCategoriaInvalida() throws Exception {
    Mockito.when(service.listarPorCategoria(9999L, 0, 1))
        .thenThrow(new NegocioException("categoria n encontrada"));
    mockMvc.perform(get("/exames/categoria/9999").param("page", "0").param("size", "1"))
        .andExpect(status().isNotFound());

  }

  @DisplayName("deveRetornar200ExameFiltroCategoria")
  public void deveRetornarExameFiltroCategoria() throws Exception {
    Mockito.when(service.listarPorCategoria(9998L, 0, 1)).thenReturn(this.mockResult);
    mockMvc.perform(get("/exames/categoria/9998").param("page", "0").param("size", "1"))
        .andExpect(status().isOk());

  }

}
