package you_tube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AppLanguageConverter appLanguageConverter() {
        return new AppLanguageConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(appLanguageConverter());
    }
}
