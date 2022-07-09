package com.fss.aeps;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fss.aeps.jpa.ImpsConfig;

@Component
public class ApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		logger.info("ApplicationInitializer initiated.");
		try {
			final ConfigurableEnvironment environment = applicationContext.getEnvironment();
			final MutablePropertySources propertySources = environment.getPropertySources();
			final Resource resource = new ClassPathResource("/application.properties");
			final Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			final DataSource dataSource = getDataSource(properties);
			propertySources.addFirst(getApplicationProperties(dataSource));
		} catch (Exception e) {
			throw new RuntimeException("ApplicationContextInitializer Failed", e);
		}
	}

	private static final PropertySource<?> getApplicationProperties(final DataSource dataSource) throws IOException {
		final JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		final List<ImpsConfig> configs = jdbc.query("select * from IMPS_CONFIG", new RowMapper<ImpsConfig>() {
			@Override
			public ImpsConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new ImpsConfig(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		});
		final Map<String, Object> map = new HashMap<>();
		configs.stream().filter(c -> !c.getParam().startsWith("#")).forEach(c -> map.put(c.getParam(), c.getValue()));
		final MapPropertySource propertySource = new MapPropertySource("application.properties", map);
		logger.info("Configured application properties from: {}", map);
		try {
			if(dataSource instanceof AutoCloseable closeable) {
				try(closeable) {
					logger.info("temporary datasource closed.");
				} 
			}
		} catch (Exception e) {}
		return propertySource;

	}

	private static final DataSource getDataSource(Properties properties) {
		try {
	        final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.url(properties.getProperty("spring.datasource.url"));
	        dataSourceBuilder.driverClassName(properties.getProperty("spring.datasource.driver-class-name"));
	        dataSourceBuilder.username(properties.getProperty("spring.datasource.username"));
	        dataSourceBuilder.password(properties.getProperty("spring.datasource.password"));
	        return dataSourceBuilder.build();
		} catch (Exception e) {
			throw new RuntimeException("database initialization failed for property initialization");
		}
    }
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		logger.info("ApplicationInitializer initiated.");
		ConfigurableApplicationContext applicationContext = null;
		try {
			final ConfigurableEnvironment environment = applicationContext.getEnvironment();
			final MutablePropertySources propertySources = environment.getPropertySources();
			final Resource resource = new ClassPathResource("/application.properties");
			final Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			final DataSource dataSource = getDataSource(properties);
			propertySources.addFirst(getApplicationProperties(dataSource));
		} catch (Exception e) {
			throw new RuntimeException("ApplicationContextInitializer Failed", e);
		}
	}
	
}
