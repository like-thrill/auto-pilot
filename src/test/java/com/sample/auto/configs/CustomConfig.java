package com.sample.auto.configs;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;

@Getter
@Configuration
@ComponentScan(basePackages = "com.sample.auto")
@PropertySource("file:./src/test/resources/properties/custom.properties")
public class CustomConfig implements ApplicationContextAware {
    @Value("${app.selenium.timeout}")
    private long seleniumTimeOut;

    @Value("${app.selenium.docker.url}")
    private String seleniumGridUrl;

    @Value("${app.selenium.log.level}")
    private String seleniumLog;

    @Value("${app.selenium.browser.headless}")
    private boolean isBrowserHeadless;

    @Value("${app.selenium.download.dir}")
    private String downloadsDir;

    private static ApplicationContext context;

    public static CustomConfig getBean(Class<CustomConfig> appConfigClass) {
        return context.getBean(appConfigClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomConfig.context = applicationContext;
    }
}
