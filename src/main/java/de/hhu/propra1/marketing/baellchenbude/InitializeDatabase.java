package de.hhu.propra1.marketing.baellchenbude;


import de.hhu.propra1.marketing.defaultservices.DerbyCustomerRepository;

public class InitializeDatabase {
	public static void main(String[] args) {
		DerbyCustomerRepository customerRepository = new DerbyCustomerRepository();
		customerRepository.initDatabase();
		for (String mail : args) {
			customerRepository.addMailAddress(mail);
		}
	}
}
