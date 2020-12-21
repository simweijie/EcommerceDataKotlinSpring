package com.okta.kotlin.service


import com.okta.kotlin.model.DataSetModel
import com.okta.kotlin.repository.DataSetRepository
import com.okta.kotlin.helper.CSVHelper
import com.okta.kotlin.helper.CSVHelper.tutorialsToCSV
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*


@Service
class CSVService {
    @Autowired
    lateinit var repository: DataSetRepository
    fun save(file: MultipartFile) {
        try {
            val tutorials: List<DataSetModel> = CSVHelper.csvToTutorials(file.inputStream)
//            repository.saveAll(tutorials) // ToFix: saveAll does not throw exception, just quietly dies
            var count : Int = 0
            for(tutorial in tutorials){
                count++
                println("saving$count")
                repository.save(tutorial)
            }
            println("insertion complete")
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        } catch (e: IllegalArgumentException){
            println("dead dog")
            e.printStackTrace()
            throw RuntimeException("fail to store csv data: " + e.message)
        }
    }

    fun load(): ByteArrayInputStream {
        val tutorials: MutableList<DataSetModel> = repository!!.findAll() as MutableList<DataSetModel>
        return tutorialsToCSV(tutorials)
    }

//    fun getAllTutorials(): MutableList<DataSetModel> {
//        return repository.findAll();
//    }

    fun getAllTutorials(paging: Pageable): Page<DataSetModel> {
        return repository.findAll(paging);
    }

    fun findByInvoiceNo(invoiceNo: Optional<String>, paging: Pageable): Page<DataSetModel> {
        return repository.findByInvoiceNo(invoiceNo, paging);
    }

}