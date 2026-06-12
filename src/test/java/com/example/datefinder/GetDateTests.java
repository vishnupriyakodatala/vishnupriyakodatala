package com.example.datefinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GetDateTests {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testGetDate() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.TEXT_PLAIN);
        MvcResult result = mockMvc.perform(mockRequest).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        LocalDate now = LocalDate.now();
        
        LocalDate futureDate = now.plusDays(100);
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String strDate = futureDate.format(format1);

        assertEquals(content, strDate);
    }
}
