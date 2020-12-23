package com.okta.kotlin.helper


import com.okta.kotlin.model.DataSetModel
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.QuoteMode
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


object CSVHelper {
    private const val TYPE = "text/csv"
    private const val TYPE2 = "application/vnd.ms-excel"

    fun hasCSVFormat(file: MultipartFile): Boolean {
        return if (TYPE != file.contentType && TYPE2 != file.contentType) {
            println(file.contentType)
            false
        } else true
    }

    fun  csvToDatasets(inputStream: InputStream ): List<DataSetModel> {
        try {
            var fileReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var csvParser = CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            var dataSetModels = ArrayList<DataSetModel>();
            var csvRecords = csvParser.records;
            for (csvRecord in csvRecords){
                var invoiceNo : String = csvRecord[0]
                var stockCode : String = csvRecord[1]
                var description : String = csvRecord[2]
                var quantity = 0
                if (csvRecord[3] != ""){
                    quantity = csvRecord[3].toInt()
                }
                var invoiceDate : LocalDateTime= LocalDateTime.parse(csvRecord[4],DateTimeFormatter.ofPattern("M/d/yyyy H:mm"))
                var unitPrice = 0.0
                if(csvRecord[5] != ""){
                    unitPrice = csvRecord[5].toDouble()
                }
                var customerID : String =csvRecord[6]
                var country : String = csvRecord[7]
                var dataSet = DataSetModel(
                    invoiceNo,
                    stockCode,
                    description,
                    quantity,
                    invoiceDate,
                    unitPrice,
                    customerID,
                    country
                )
                dataSetModels.add(dataSet)
            }

            return dataSetModels
        }catch (e: Exception) {
            println(e.stackTrace)
            throw Exception("wahlao" + e.message);
        }
        catch (e: IOException ) {
            throw RuntimeException("fail to parse CSV file: " + e.message);
        }
    }

    fun  datasetsToCSV(dataSets: MutableList<DataSetModel> ): ByteArrayInputStream {
        val format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try{
            var out = ByteArrayOutputStream();
            var csvPrinter = CSVPrinter(PrintWriter(out), format);
            for(dataset in dataSets) {
                var data = listOf(
                    dataset.invoiceNo,
                    dataset.stockCode,
                    dataset.description,
                    dataset.quantity,
                    dataset.invoiceDate,
                    dataset.unitPrice,
                    dataset.customerID,
                    dataset.country
                )
                csvPrinter.printRecord(data)
            }
            csvPrinter.flush()
            return ByteArrayInputStream((out.toByteArray()))
        }catch (e: IOException) {
            throw RuntimeException("fail to import data to CSV file: " + e.message);
        }
    }
}