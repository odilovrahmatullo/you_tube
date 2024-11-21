package you_tube.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import you_tube.enums.AppLanguage;

@Component
public class AppLanguageConverter implements Converter<String, AppLanguage> {

    @Override
    public AppLanguage convert(String source) {
        if (source == null || source.isEmpty()) {
            return AppLanguage.uz;
        }

        switch (source.toLowerCase()) {
            case "uz":
                return AppLanguage.uz;
            case "ru":
                return AppLanguage.ru;
            case "en":
                return AppLanguage.en;
            default:
                return AppLanguage.uz;
        }
    }
}
