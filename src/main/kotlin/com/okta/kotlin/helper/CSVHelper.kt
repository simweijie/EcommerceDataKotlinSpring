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
    var TYPE = "text/csv"
    var TYPE2 = "application/vnd.ms-excel"
    var HEADERs = arrayOf("Id", "Title", "Description", "Published")
    fun hasCSVFormat(file: MultipartFile): Boolean {
        return if (TYPE != file.contentType && TYPE2 != file.contentType) {
            println(file.contentType)
            false
        } else true
    }

//    fun csvToTutorials(`is`: InputStream?): List<DataSetModel> {
//        try {
//            val tutorials: MutableList<DataSetModel> = ArrayList<DataSetModel>()
//            val csvRecords: Iterable<CSVRecord> = csvParser.getRecords()
//            BufferedReader(InputStreamReader(`is`, "UTF-8")).use { fileReader ->
//                CSVParser(
//                    fileReader,
//                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
//                ).use { csvParser ->
//                    for (csvRecord in csvRecords) {
//                        val tutorial = DataSetModel(
//                            csvRecord.get("InvoiceNo"),
//                            csvRecord.get("StockCode"),
//                            csvRecord.get("Description"),
//                            csvRecord.get("Quantity"),
//                            csvRecord.get("InvoiceDate"),
//                            csvRecord.get("UnitPrice"),
//                            csvRecord.get("CustomerID"),
//                            csvRecord.get("Country"),
//                        )
//                        tutorials.add(tutorial)
//                    }
//
//                }
//            }
//            return tutorials
//
//        } catch (e: IOException) {
//            throw RuntimeException("fail to parse CSV file: " + e.message)
//        }
//    }

//    @Throws(RuntimeException::class)
    fun  csvToTutorials(inputStream: InputStream ): List<DataSetModel> {
        try {
            var fileReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var csvParser = CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            var dataSetModels = ArrayList<DataSetModel>();
            var csvRecords = csvParser.records;
//            println("EEEE: " + csvRecords.size)
//            println(csvRecords[0])
//            var count: Int = 0
            for (csvRecord in csvRecords){
//                count++
//                println(count)
//                println(csvRecord[4])
//                println("WHERE MY RECORD0: " + csvRecord[0])
//                println("WHERE MY RECORD1: " + csvRecord[1])
//                println("WHERE MY RECORD2: " + csvRecord[2])
//                println("WHERE MY RECORD3: " + csvRecord[3].toInt())
//                println("WHERE MY RECORD4: " + LocalDateTime.parse(csvRecord[4], DateTimeFormatter.ofPattern("M/d/yyyy H:mm")))
////                println("WHERE MY RECORD4: " + LocalDateTime.parse(csvRecord[4]))
//                println("WHERE MY RECORD5: " + csvRecord[5].toDouble())
//                println("WHERE MY RECORD6: " + csvRecord[6].toLong())
//                println("WHERE MY RECORD7: " + csvRecord[7])

//            var dataSet : DataSetModel =  DataSetModel(
//                536365,
//                "85123A",
//                "WHITE HANGING HEART T-LIGHT HOLDER",
//                6,
//                LocalDateTime.parse("12/1/2010 8:26", DateTimeFormatter.ofPattern("M/d/yyyy H:mm")),
//                2.55,
//                17850,
//                "United Kingdom"
//            )
//            dataSet.invoiceNo=csvRecord[0].toInt()
//                if (csvRecord[2] == null){
//                    println(csvRecord[2])
//                    println("ok")
//                }
//                if (csvRecord[2] == ""){
//                    println("empty string")
//                }
                var invoiceNo : String = csvRecord[0]
                var stockCode : String = csvRecord[1]
                var description : String = csvRecord[2]
                var quantity : Int = 0
                if (csvRecord[3] != ""){
                    quantity = csvRecord[3].toInt()
                }
                var invoiceDate : LocalDateTime= LocalDateTime.parse(csvRecord[4],DateTimeFormatter.ofPattern("M/d/yyyy H:mm"))
                var unitPrice : Double = 0.0
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
//                println(dataSet)
                dataSetModels.add(dataSet)
            }
//            println("dataSet models: "+ dataSetModels.size)

            return dataSetModels
        }catch (e: Exception) {
            println(e.stackTrace)
            throw Exception("wahlao" + e.message);
        }
        catch (e: IOException ) {
            throw RuntimeException("fail to parse CSV file: " + e.message);
        }




//                for (CSVRecord csvRecord : csvRecords) {
//                Tutorial tutorial = new Tutorial(
//                    Long.parseLong(csvRecord.get("Id")),
//                    csvRecord.get("Title"),
//                    csvRecord.get("Description"),
//                    Boolean.parseBoolean(csvRecord.get("Published"))
//                );
//
//                tutorials.add(tutorial);
//            }
//
//                return tutorials;
//            }
    }

    fun  tutorialsToCSV(dataSets: MutableList<DataSetModel> ): ByteArrayInputStream {
        val format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try{
            var out = ByteArrayOutputStream();
            var csvPrinter = CSVPrinter(PrintWriter(out), format);
            for(dataset in dataSets) {
                var data = Arrays.asList(
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

//                for (Tutorial tutorial : tutorials) {
//                List<String> data = Arrays.asList(
//                        String.valueOf(tutorial.getId()),
//                tutorial.getTitle(),
//                tutorial.getDescription(),
//                String.valueOf(tutorial.isPublished())
//                );
//
//                csvPrinter.printRecord(data);
//            }
//
//                csvPrinter.flush();
//                return new ByteArrayInputStream(out.toByteArray());
//            }
        }
//
//    fun tutorialsToCSV(dataSets: List<DataSetModel?>): ByteArrayInputStream {
//        val format: CSVFormat = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL)
//        try {
//            ByteArrayOutputStream().use { out ->
//                CSVPrinter(PrintWriter(out), format).use { csvPrinter ->
//                    for (dataSet in dataSets) {
//                        val data: MutableList<String?> = Arrays.asList(
////                            dataSet.invoiceNo,
//                            dataSet?.stockCode,
//                        )
//                        csvPrinter.printRecord(data)
//                    }
//                    csvPrinter.flush()
//                    return ByteArrayInputStream(out.toByteArray())
//                }
//            }
//        } catch (e: IOException) {
//            throw RuntimeException("fail to import data to CSV file: " + e.message)
//        }
//    }
}