package com.okta.kotlin.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GenerationType

import javax.persistence.GeneratedValue




@Entity
     class DataSetModel(
    invoiceNo: String,
    stockCode: String,
    description: String,
    quantity: Int,
    invoiceDate: LocalDateTime,
    unitPrice: Double,
    customerID: String,
    country: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? =null
//    @Id
    var invoiceNo: String = invoiceNo
//    @Id
    var stockCode: String = stockCode
    var description: String = description
    var quantity: Int = quantity
    var invoiceDate: LocalDateTime = invoiceDate
    var unitPrice: Double = unitPrice
    var customerID: String = customerID
    var country: String = country
}