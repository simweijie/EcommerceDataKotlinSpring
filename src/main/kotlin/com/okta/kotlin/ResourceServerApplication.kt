package com.okta.kotlin

import com.okta.kotlin.model.DataSetModel
import com.okta.kotlin.repository.DataSetRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootApplication
class ResourceServerApplication  {

//	@Bean
//	fun run(repository: DataSetRepository) = ApplicationRunner {
////		repository.save(
////            DataSetModel(
////			invoiceNo = 536365,
////			stockCode = "85123A",
////			description = "WHITE HANGING HEART T-LIGHT HOLDER",
////			quantity = 6,
//////			invoiceDate = Timestamp.valueOf("12/1/2010 8:26"),
////			invoiceDate = LocalDateTime.parse("12/1/2010 8:26", DateTimeFormatter.ofPattern("M/d/yyyy H:mm")),
////			unitPrice = 2.55,
////			customerID = 17850,
////			country = "United Kingdom"
////		)
//        )
//	}

	//	@Bean
//	fun run(repository: CoffeeShopRepository) = ApplicationRunner {
//		repository.save(CoffeeShopModel(
//			name = "Oblique",
//			address = "3039 SE Stark St, Portland, OR 97214",
//			phone = "555-111-4444",
//			priceOfCoffee = 1.50,
//			powerAccessible = true,
//			internetReliability = 5
//		))
//		repository.save(CoffeeShopModel(
//			name = "Epoch Coffee",
//			address = "221 W N Loop Blvd, Austin, TX 78751",
//			phone = "555-111-2424",
//			priceOfCoffee = 2.50,
//			powerAccessible = true,
//			internetReliability = 3
//		))
//	}

}

fun main(args: Array<String>) {
	runApplication<ResourceServerApplication>(*args)
}