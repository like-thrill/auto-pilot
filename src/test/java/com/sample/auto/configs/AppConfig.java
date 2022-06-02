package com.sample.auto.configs;

import com.github.javafaker.Faker;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.testng.Reporter;

import java.util.Objects;

@Data
@Configuration
@ComponentScan(basePackages = "com.sample.auto")
@PropertySources({
        @PropertySource("file:./src/test/resources/properties/application.properties"),
        @PropertySource("file:./src/test/resources/properties/custom.properties")})
public class AppConfig implements ApplicationContextAware {

    @Value("${app.url}")
    private String url;

    @Value("${app.user.name}")
    private String userName;

    @Value("${app.user.password}")
    private String userPassword;

    @Value("${app.selenium.timeout}")
    private long seleniumTimeOut;

    private static ApplicationContext context;

    @Bean
    public Faker faker() {
        return new Faker();
    }

    /**
     * Get current OS name.
     *
     * @return
     */
    public String getCurrentOS() {
        return System.getProperty("os.name");
    }

    /**
     * Get current working directory
     *
     * @return
     */
    public String getCurrentWorkingDir() {
        return System.getProperty("usr.dir");
    }

    /**
     * Get platform name for browser.
     *
     * @return
     */
    public String getPlatFormName() {
        var platform = System.getProperty("browser");
        if (Objects.isNull(platform))
            platform = Reporter
                    .getCurrentTestResult()
                    .getTestContext()
                    .getCurrentXmlTest()
                    .getParameter("browser");

        return platform;
    }

    public static AppConfig getBean(Class<AppConfig> appConfigClass) {
        return context.getBean(appConfigClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppConfig.context = applicationContext;
    }
}
