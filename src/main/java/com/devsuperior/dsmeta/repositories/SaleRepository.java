package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(
            obj.id, obj.date, obj.amount, obj.seller.name)
            FROM Sale obj
            WHERE obj.date BETWEEN :min AND :max
            AND LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))
            ORDER BY obj.date DESC
            """)
    Page<SaleReportDTO> report(LocalDate min, LocalDate max, String name, Pageable pageable);


    @Query("""
            SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount))
            FROM Sale obj
            WHERE obj.date BETWEEN :min AND :max
            GROUP BY obj.seller.name
            ORDER BY obj.seller.name
            """)
    Page<SaleSummaryDTO> summary(LocalDate min, LocalDate max, Pageable pageable);
}
