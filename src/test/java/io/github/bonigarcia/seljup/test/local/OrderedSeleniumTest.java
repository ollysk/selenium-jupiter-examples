/*
 * (C) Copyright 2019 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.seljup.test.local;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.seljup.SingleSession;

@ExtendWith(SeleniumExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SingleSession
class OrderedSeleniumTest {

    final Logger log = getLogger(lookup().lookupClass());

    RemoteWebDriver driver;

    OrderedSeleniumTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    @Order(1)
    void testStep1() {
        log.debug("Step 1: {}", driver);
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle())
                .containsMatch("JUnit 5 extension for Selenium");
    }

    @Test
    @Order(2)
    void testStep2() throws InterruptedException {
        log.debug("Step 2: {}", driver);
        WebElement about = driver.findElementByLinkText("About");

        assertThat(about.isDisplayed());
        about.click();

        Thread.sleep(3000);
    }

}