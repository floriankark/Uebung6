package de.hhu.propra1.marketing.baellchenbude;

import de.hhu.propra1.marketing.defaultservices.*;
import de.hhu.propra1.marketing.wetterservice.IWeatherForecast;
import de.hhu.propra1.marketing.wetterservice.WttrInForecast;

import java.util.Collection;

public class MarketingApp {
    private static final int SIX_HOURS = 3_600_000 * 6;
    private final IWeatherForecast temperature;
    private final DerbyCustomerRepository customerRepository;
    private final IMailSender mailSender;

    public MarketingApp(IWeatherForecast wetter, DerbyCustomerRepository repo, IMailSender mailSender) {
        this.temperature = wetter;
        this.customerRepository = repo;
        this.mailSender = mailSender;
    }

    public static void main(String[] args) throws Exception {
        IWeatherForecast wetter = new WttrInForecast();
        DerbyCustomerRepository repo = new DerbyCustomerRepository();
        IMailSender mailSender = new GMailSender();
        MarketingApp app = new MarketingApp(wetter, repo, mailSender);
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
            if(customers.isEmpty()){
                System.out.println("Ist leer");
            }
            for (String customer : customers) {
                System.out.println("Sending Mail to " + customer);
                mailSender.sendMail(customer);
            }
        }
    }

}
