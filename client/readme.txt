


--- RestClientException: no suitable HttpMessageConverter

fix
RestClientException: Could not extract response: no suitable HttpMessageConverter found for response type [interface java.util.List] and content type [application/json;charset=UTF-8]
@
ResponseEntity<List> response = new RestTemplate().exchange(url, HttpMethod.GET, request, List.class);

by adding jackson to pom:

		<!-- jackson -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.1</version>
		</dependency>