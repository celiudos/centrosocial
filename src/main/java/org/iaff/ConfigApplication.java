package org.iaff;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.iaff.csiaff.controller.converter.CidadeConverter;
import org.iaff.csiaff.controller.converter.EstadoConverter;
import org.iaff.csiaff.controller.converter.GrupoConverter;
import org.iaff.csiaff.thymeleaf.IaffDialect;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableCaching
public class ConfigApplication extends WebMvcConfigurerAdapter {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		
		registry.addConverter( cidadeConverter() );
		registry.addConverter( estadoConverter() );
		registry.addConverter( grupoConverter() );

		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		registry.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);

		NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
		registry.addFormatterForFieldType(Integer.class, integerFormatter);

		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		dateTimeFormatter.registerFormatters(registry);
		
		super.addFormatters(registry);
	}

	@Bean
	public IaffDialect iaffDialect() {
	    return new IaffDialect();
	}
	
	// Início conversores
    @Bean
    public CidadeConverter cidadeConverter(){
    	return new CidadeConverter();
    }
    
    @Bean
    public EstadoConverter estadoConverter(){
    	return new EstadoConverter();
    }
    
    @Bean
    public GrupoConverter grupoConverter(){
    	return new GrupoConverter();
    }
	// Fim conversores    
}
