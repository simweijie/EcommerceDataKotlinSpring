package com.okta.kotlin


//import org.junit.jupiter.api.

import com.okta.kotlin.controller.CSVController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.RequestPostProcessor
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@WebAppConfiguration
@ContextConfiguration(classes = [CSVController::class])
@ExtendWith(SpringExtension::class)
class MultipartPostRequestControllerUnitTest {
    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Test
    fun whenFileUploaded_thenVerifyStatus(){
        println("WHERE MY PRINT")
        var csvFile: MockMultipartFile  = MockMultipartFile("hello.csv","hello.csv","text/csv","536365,85123A,WHITE HANGING HEART T-LIGHT HOLDER,6,12/1/2010 8:26,2.55,17850,United Kingdom".toByteArray())
        var mockMvc: MockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        var result: MvcResult = mockMvc.perform(multipart("/api/csv/upload").file(csvFile)).andReturn();
        println("WHERE MY PRINT 2")
        println(result.response.contentAsString)

          //original
//        mockMvc.perform(multipart("/api/csv/upload").file(file)).andExpect(status().isOk);
    }
}