package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.date.HandleDate;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements HandleDate {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sale not Found"));
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> saleReportDTO (String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate max = handleMaxDate(maxDate);
		LocalDate min = handleMinDate(minDate,max);
		String sellerName = (name == null) ? "" : name;

		return repository.report(min, max, sellerName, pageable);
	}

	public List<SaleSummaryDTO> summaryDTO (String minDate, String maxDate) {
		LocalDate max = handleMaxDate(maxDate);
		LocalDate min = handleMinDate(minDate, max);

		return repository.summary(min, max);
	}
}
