package com.sapient.costumer.controller;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonView;
import com.sapient.customer.CustomerApplication;
import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.beans.Transaction;
import com.sapient.customer.pojo.TransactionMiniView;
import com.sapient.customer.service.CustomerDBService;

@SpringBootTest(classes=CustomerApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

	@MockBean
	CustomerDBService service;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void displayMaxTransactionsApiCheck() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/customer/creditcards/lastmonth/max_merchant");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse r = result.getResponse();
		// System.out.println(r);
		Assert.assertEquals(HttpStatus.OK.value(), r.getStatus());
	}
	
	@Test
	public void displayMaxTransactionsApiMock() throws Exception {
		List<CreditCard> list = new ArrayList<CreditCard>();
		LocalDate transactionDate = LocalDate.of(2020,10, 5);
        CreditCard tempCard = new CreditCard("4012888888881881", "624", new Date(),true);
        Map<String,String> map = new TreeMap<String, String>();
        list.add(tempCard);
        map.put("credit_card", "4012888888881881");
        map.put("merchant", "rrcjfmo4442");
        map.put("month", transactionDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US));
        map.put("amount", "8000");
        
        Mockito.when(service.getAllCards(Mockito.anyString())).thenReturn(list);
        Mockito.when(service.getLastMonthMaxTransactionWithMerchant(Mockito.anyString())).thenReturn(map);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/customer/creditcards/lastmonth/max_merchant");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse r = result.getResponse();
		Assert.assertTrue(new JSONArray(r.getContentAsString()).getJSONObject(0).get("amount").equals("8000"));
	}
	
	
}
