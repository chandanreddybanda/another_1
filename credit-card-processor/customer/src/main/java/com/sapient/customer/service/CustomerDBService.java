package com.sapient.customer.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.beans.Transaction;
import com.sapient.customer.pojo.TransactionMiniView;
import com.sapient.customer.repo.CustomerRepository;
import com.sapient.customer.repo.ITransactionRepository;

@Service
public class CustomerDBService {

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private ITransactionRepository trepo;

	public Optional<Customer> getCustomerById(String id) {
		return this.repository.findById(id);
	}

	public Customer getCustomerByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	public Customer insertCustomer(Customer customer) {
		return this.repository.insert(customer);
	}

	public Customer updateCustomer(Customer customer) {
		return this.repository.save(customer);
	}

	public void deleteCustomerById(String id) {
		this.repository.deleteById(id);
	}

	public List<Customer> getAllCustomers() {
		return this.repository.findAll();
	}

	public boolean isCustomerExists(String email) {
		return this.repository.existsByEmail(email);
	}

	public List<CreditCard> getAllCards(String email) {
		List<CreditCard> cards = new ArrayList<>();

		cards = getCustomerByEmail(email).getCreditCards();

		return cards;
	}

	// transaction part written by chandan story number 63

	public Transaction getTransactionById(String transactionNumber) {
		Optional<Transaction> optionalObj = this.trepo.findById(transactionNumber);
		return optionalObj.get(); // scope for change
	}

	public List<TransactionMiniView> getTransactionByCcNumber(String ccNumber, int number) {
		Sort sort = Sort.by(Sort.Direction.DESC, "transactionAmount");
		List<Transaction> allTransactions = this.trepo.findByCcNumber(ccNumber, sort);
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction t : allTransactions) {
			if (t.getTransactionDate().getMonth().compareTo(LocalDate.now().getMonth()) < 0) {
				result.add(t);
			}
		}
		List<TransactionMiniView> transactions = new ArrayList<TransactionMiniView>();
		result = result.subList(0, Math.min(result.size(), number));
		for (Transaction t : result) {
			TransactionMiniView transaction = new TransactionMiniView(
					t.getTransactionDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US),
					t.getTransactionAmount(), t.getTransactionDescription());
			transactions.add(transaction);
		}
		return transactions;
	}

	public HashMap<String, String> getLastMonthMaxTransactionWithMerchant(String ccNumber) {
		Sort sort = Sort.by(Sort.Direction.DESC, "transactionAmount");
		List<Transaction> allTransactions = this.trepo.findByCcNumber(ccNumber, sort);
		Transaction result = null;
		for (Transaction t : allTransactions) {
			if (t.getTransactionDate().getMonth().compareTo(LocalDate.now().getMonth()) < 0) {
				result = t;
				break;
			}
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("credit_card", ccNumber);
		map.put("month", result.getTransactionDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US));
		map.put("amount", result.getTransactionAmount().toString());
		map.put("merchant", result.getMerchantId());
		return map;
	}

	public HashMap<String, String> getLastMonthMaxTransactionWithoutMerchant(String ccNumber) {
		HashMap<String, String> maxTransaction = getLastMonthMaxTransactionWithMerchant(ccNumber);
		maxTransaction.remove("merchant");
		return maxTransaction;
	}

}
