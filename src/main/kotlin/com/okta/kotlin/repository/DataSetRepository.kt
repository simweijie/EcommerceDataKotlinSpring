package com.okta.kotlin.repository

import com.okta.kotlin.model.DataSetModel
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface DataSetRepository : JpaRepository<DataSetModel, Long>{
    fun findByInvoiceNo(invoiceNo: Optional<String>, pageable: Pageable): Page<DataSetModel>

//    fun findByStockCode(stockCode: String, pageable: Pageable): Page<DataSetModel>
//    fun findByDescContaining(description: String, pageable: Pageable): Page<DataSetModel>
//    fun findByInvoiceDate(invoiceDate: LocalDateTime, pageable: Pageable): Page<DataSetModel>
//    fun findByCustomerID(customerID: String, pageable: Pageable): Page<DataSetModel>
//    fun findByCountry(country: String, pageable: Pageable): Page<DataSetModel>

//    fun findByTitleContaining(title: String, sort: Sort): List<DataSetModel>
}

