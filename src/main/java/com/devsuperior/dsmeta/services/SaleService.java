package com.devsuperior.dsmeta.services;

import java.util.Optional;

import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sale not Found"));

		return new SaleMinDTO(entity);
	}
}
