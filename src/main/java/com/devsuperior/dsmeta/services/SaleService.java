package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sale not Found"));
		return new SaleMinDTO(entity);
	}

	private LocalDate handleMaxDate(String maxDate) {
		if (maxDate == null || maxDate.isBlank()) {
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		return LocalDate.parse(maxDate);
	}

	private LocalDate handleMinDate(String minDate, LocalDate maxDate) {
		if (minDate == null || minDate.isBlank()) {
			return  maxDate.minusYears(1L);
		}
		return LocalDate.parse(minDate);
	}
}
