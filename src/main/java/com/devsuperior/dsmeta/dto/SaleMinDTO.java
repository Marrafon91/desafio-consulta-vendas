package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public record SaleMinDTO(
        Long id,
        Double amount,
        LocalDate date) {

	public SaleMinDTO(Sale entity) {
		this (
				entity.getId(),
				entity.getAmount(),
				entity.getDate()
		);
	}
}
