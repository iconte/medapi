package br.com.icc.medapi.controller.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;

public class ControllerUtils {

  public static <T> Map<String, Object> getRetornoSimplificado(Page<T> page) {

    Optional<Page<T>> opt = Optional.ofNullable(page);

    Map<String, Object> response = new HashMap<>();
    if (opt.isPresent()) {
      response.put("data", page.getContent());
      response.put("currentPage", page.getNumber());
      response.put("totalItems", page.getTotalElements());
      response.put("totalPages", page.getTotalPages());
      response.put("size", page.getSize());
    }
    return response;
  }
}
