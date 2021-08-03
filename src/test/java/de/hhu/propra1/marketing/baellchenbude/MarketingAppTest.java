package de.hhu.propra1.marketing.baellchenbude;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MarketingAppTest {

    @Test
    public void temperatureIsHot_noCustomersInRepository() {
        // Arrange
        MarketingApp marketingApp = new MarketingApp();
        //...
        // Act
        marketingApp.doMarketing();
        // Assert
        assertThat(true).isTrue();
    }
}