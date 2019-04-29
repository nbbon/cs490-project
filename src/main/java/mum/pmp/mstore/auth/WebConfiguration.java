package mum.pmp.mstore.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
        .addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");
    }
    
//	@Bean
//	public ViewResolver getXmlJasperViewResolver() {
//		XmlViewResolver resolver = new XmlViewResolver();
//		resolver.setLocation(new ClassPathResource("jasper-views.xml"));
//		resolver.setOrder(0);
//		return resolver;
//	}
	
//	@Bean
//	public JasperReportsViewResolver getJasperReportsViewResolver() {
//	  JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//	  resolver.setPrefix("classpath:/jasperreports/");
//	  resolver.setSuffix(".jasper");
//	  resolver.setReportDataKey("datasource");
//	  resolver.setViewNames("rpt_*");
//	  resolver.setViewClass(JasperReportsMultiFormatView.class);
//	  resolver.setOrder(0);
//	  return resolver;
//	}  

//	@Bean
//	public InternalResourceViewResolver getInternalResourceViewResolver() {
//	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	  resolver.setPrefix("/WEB-INF/jsp/");
//	  resolver.setSuffix(".jsp");
//	  resolver.setOrder(1);
//	  return resolver;
//	}
	 
}
