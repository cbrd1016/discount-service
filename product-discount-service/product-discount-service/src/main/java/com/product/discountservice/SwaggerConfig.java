
/**
 * $Id$
 */
package com.product.discountservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Initializes the application&apos;s usage of Swagger to expose API
 * documentation for consumers of the service.
 * 
 * @version $Revision$
 */
@Configuration
public class SwaggerConfig {
	/**
	 * Builds Swagger API documentation containing title and description for the
	 * service.
	 * 
	 * @return APIInfo instance
	 */

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info().title("Product Discount Service")
				.description("This service applies discounts to total cost based on purchased items in the order")
				.version("1.0"));
	}
}
