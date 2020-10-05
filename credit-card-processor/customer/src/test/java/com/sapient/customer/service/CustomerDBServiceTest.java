package com.sapient.customer.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import com.sapient.customer.CustomerApplication;
import com.sapient.customer.beans.Transaction;
import com.sapient.customer.pojo.TransactionMiniView;
import com.sapient.customer.repo.ITransactionRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerDBServiceTest {
	
	@Test
	void getTransactionByCcNumberTest() throws Exception {	
//		Sort sort = Sort.by(Sort.Direction.DESC, "transactionAmount");
//		List<Transaction> ListOfTransactions = new ArrayList<Transaction>() ;
//	g	ListOfTransactions.add(new Transaction("901", "112233", 234.6, "debit", "online", LocalDate.of(2020, 9, 19),
//				LocalTime.of(13, 34, 42), "hyd", "192.168.32.109", "success", "501", "for food"));
//		ListOfTransactions.add(new Transaction("902", "112233", 134.3, "debit", "online", LocalDate.of(2020, 10, 12),
//				LocalTime.of(1, 35, 42), "hyd", "192.168.32.109", "success", "502", "paying bill"));
//		when(repository.findByCcNumber("112233",sort)).thenReturn(ListOfTransactions);
//		List<TransactionMiniView> expectedList = service.getTransactionByCcNumber("112233",2);
//		List<TransactionMiniView> actualList = new ArrayList<TransactionMiniView>();
//		actualList.add(new TransactionMiniView("Sep",234.6,"for food"));
//		assertEquals(expectedList, actualList);
		
	}
}
