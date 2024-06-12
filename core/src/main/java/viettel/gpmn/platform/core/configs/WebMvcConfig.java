package viettel.gpmn.platform.core.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import viettel.gpmn.platform.core.enums.EnumConverterFactory;

import java.util.List;

@Configuration
@EnableSpringDataWebSupport
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    @NonNull
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverterFactory(new EnumConverterFactory<>());
        return f;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

}
