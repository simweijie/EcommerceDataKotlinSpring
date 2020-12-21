package com.okta.kotlin.controller

import com.okta.kotlin.service.CSVService
import com.okta.kotlin.helper.CSVHelper
import com.okta.kotlin.message.ResponseMessage
import com.okta.kotlin.model.DataSetModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap


//@CrossOrigin("http://localhost:8081")
@CrossOrigin(origins = ["http://localhost:3000","http://localhost:8081","http://localhost:8080"])
//@CrossOrigin("http://localhost:3000/")
@Controller
@RequestMapping("/api/csv")
class CSVController {
    @Autowired
    var fileService: CSVService? = null
//    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<ResponseMessage> {
        var message = ""
        println("HIHI")
        if (CSVHelper.hasCSVFormat(file)) {
            return try {
                fileService?.save(file)
                message = "Uploaded the file successfully: " + file.originalFilename
                ResponseEntity.status(HttpStatus.OK).body<ResponseMessage>(ResponseMessage(message))
            } catch (e: Exception) {
                println(e.message)
                message = "Could not upload the file: " + file.originalFilename + "!"
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body<ResponseMessage>(ResponseMessage(message))
            }
        }
        message = "Please upload a csv file!"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ResponseMessage>(ResponseMessage(message))
    }

//    @get:GetMapping("/allDataSet")
//    val allDataSet: ResponseEntity<List<DataSetModel>?>
//        get() = try {
//            val dataSets: List<DataSetModel?>? = fileService?.getAllDataSets()
//            if (dataSets!!.isEmpty()) {
//                ResponseEntity<List<DataSetModel>?>(HttpStatus.NO_CONTENT)
//            } else ResponseEntity<List<DataSetModel>?>(HttpStatus.OK)
//        } catch (e: Exception) {
//            ResponseEntity<List<DataSetModel>?>(null, HttpStatus.INTERNAL_SERVER_ERROR)
//        }


//    @GetMapping("/tutorials")
//    fun  getAllTutorials() : ResponseEntity<List<DataSetModel>> {
//        println("hi")
//        try {
//            var tutorials: List<DataSetModel>? = fileService?.getAllTutorials();
//
//            if (tutorials!!.isEmpty()) {
//                return ResponseEntity(HttpStatus.NO_CONTENT);
//            }
//
//            return ResponseEntity(tutorials, HttpStatus.OK);
//        } catch (e: Exception) {
//            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @CrossOrigin(origins = ["http://localhost:3000","http://localhost:8081","http://localhost:8080"])
    @GetMapping("/tutorials/findByInvoiceNo")
    fun findByInvoiceNo(@RequestParam invoiceNo: Optional<String>,
                        @RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "3") size: Int,
                        @RequestParam(defaultValue = "invoiceNo") sort: String): ResponseEntity<Map<String,Any>>{
        try{

            var dataSets: List<DataSetModel>
            var paging: Pageable = PageRequest.of(page,size)
            var pageTuts: Page<DataSetModel>
            if (invoiceNo.isEmpty) {
                println("heymama")
                pageTuts = fileService!!.getAllTutorials(paging)
            }else{
                pageTuts = fileService!!.findByInvoiceNo(invoiceNo,paging)
            }
            dataSets = pageTuts.content
            var response: HashMap<String,Any> = HashMap()
            response["dataSets"] = dataSets
            response["currentPage"] = pageTuts.number
            response["totalItems"] = pageTuts.totalElements
            response["totalPages"] = pageTuts.totalPages
            return ResponseEntity(response,HttpStatus.OK)
        }catch (e: Exception){
           e.printStackTrace()
            return ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // not in use
    @get:GetMapping("/download")
    val file: ResponseEntity<Resource>
        get() {
            val filename = "tutorials.csv"
            val file = InputStreamResource(fileService!!.load())
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$filename")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file)
        }
}