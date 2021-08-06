package de.hhu.propra1.marketing.baellchenbude;

import de.hhu.propra1.marketing.defaultservices.*;
import de.hhu.propra1.marketing.wetterservice.IWeatherForecast;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class MarketingAppTest {

    @Test
    public void temperatureIsHot_customersInRepository() {
        // Arrange
        IWeatherForecast wetter = () -> 31;
        DerbyCustomerRepository repo = mock(DerbyCustomerRepository.class);
        IMailSender mailSender = mock(GMailSender.class);
        MarketingApp marketingApp = new MarketingApp(wetter, repo, mailSender);
        when(repo.getMailAddresses()).thenReturn(List.of("abc@gmail.de"));
        // Act
        marketingApp.doMarketing();
        // Assert
        verify(mailSender).sendMail(anyString());
    }

    @Test
    public void temperatureIsCold_customersInRepository() {
        // Arrange
        IWeatherForecast wetter = () -> 18;
        DerbyCustomerRepository repo = mock(DerbyCustomerRepository.class);
        IMailSender mailSender = mock(GMailSender.class);
        MarketingApp marketingApp = new MarketingApp(wetter, repo, mailSender);
        when(repo.getMailAddresses()).thenReturn(List.of("abc@gmail.de"));
        // Act
        marketingApp.doMarketing();
        // Assert
        verifyNoMoreInteractions(mailSender);
    }
}