package de.hhu.propra1.marketing.baellchenbude;

import de.hhu.propra1.marketing.defaultservices.DerbyCustomerRepository;
import de.hhu.propra1.marketing.defaultservices.GMailSender;
import de.hhu.propra1.marketing.defaultservices.WttrInForecast;

import java.util.Collection;

public class MarketingApp {
    private static final int SIX_HOURS = 3_600_000 * 6;
    private final WttrInForecast temperature;
    private final DerbyCustomerRepository customerRepository;
    private final GMailSender mailSender;

    public MarketingApp() {
        temperature = new WttrInForecast();
        customerRepository = new DerbyCustomerRepository();
        mailSender = new GMailSender();
    }

    public static void main(String[] args) throws Exception {
        MarketingApp app = new MarketingApp();
        app.marketingLoop();
    }

    private void marketingLoop() throws InterruptedException {
        while (true) {
            doMarketing();
            Thread.sleep(SIX_HOURS);
        }
    }

    public void doMarketing() {
        System.out.println("Executing marketing strategy");
        int celsius = temperature.getCelsiusTemperature();
        System.out.println("Temperature is: " + celsius + " ºC.");
        if (celsius > 30) {
            System.out.println("Sending Mails");
            Collection<String> customers = customerRepository.getMailAddresses();
            for (String customer : customers) {
                System.out.println("Sending Mail to " + customer);
                mailSender.sendMail(customer);
            }
        }
    }

}
