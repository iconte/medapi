package br.com.icc.medapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.icc.medapi")
public class MedapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedapiApplication.class, args);
	}

}
