package com.okta.kotlin.controller

import com.okta.kotlin.service.CSVService
import com.okta.kotlin.helper.CSVHelper
import com.okta.kotlin.message.ResponseMessage
import com.okta.kotlin.model.DataSetModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap


@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/api/csv")
class CSVController {
    @Autowired
    var fileService: CSVService? = null
    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<ResponseMessage> {
        var message: String
        if (CSVHelper.hasCSVFormat(file)) {
            return try {
                fileService?.save(file)
                message = "Uploaded the file successfully: " + file.originalFilename
                ResponseEntity.status(HttpStatus.OK).body(ResponseMessage(message))
            } catch (e: Exception) {
                e.printStackTrace()
                message = "Could not upload the file: " + file.originalFilename + "!"
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseMessage(message))
            }
        }
        message = "Please upload a csv file!"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage(message))
    }

    @GetMapping("/datasets/findByInvoiceNo")
    fun findByInvoiceNo(@RequestParam invoiceNo: Optional<String>,
                        @RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "5") size: Int,
                        @RequestParam(defaultValue = "invoiceNo") sort: String): ResponseEntity<Map<String,Any>>{
        try{

            var dataSets: List<DataSetModel>
            var paging: Pageable = PageRequest.of(page,size)
            var pageDataSets: Page<DataSetModel> = if (invoiceNo.isEmpty) {
                fileService!!.getAllDatasets(paging)
            }else{
                fileService!!.findByInvoiceNo(invoiceNo,paging)
            }
            dataSets = pageDataSets.content
            var response: HashMap<String,Any> = HashMap()
            response["dataSets"] = dataSets
            response["currentPage"] = pageDataSets.number
            response["totalItems"] = pageDataSets.totalElements
            response["totalPages"] = pageDataSets.totalPages
            return ResponseEntity(response,HttpStatus.OK)
        }catch (e: Exception){
           e.printStackTrace()
            return ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}