package com.example.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.Iterator;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void insertData() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
						.post("/brand")
						.content(brandData())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string("Success"));
	}

	@Test
	public void tests() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
				.post("/brand")
				.content(brandData())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		Iterator<String> request = Arrays.stream(requests()).iterator();
		Iterator<String> expectedResult = Arrays.stream(expectedResults()).iterator();

		while(request.hasNext() && expectedResult.hasNext()){
			mockMvc.perform(MockMvcRequestBuilders
							.get("/request")
							.content(request.next())
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.content().json(expectedResult.next()));
		}
	}

	private String[] requests(){
		return new String[]{
			"{\n" +
					"  \"productId\": 35455,\n" +
					"  \"brandId\": 1,\n" +
					"  \"date\": \"2020-06-14 10:00\"\n" +
					"}",
			"{\n" +
					"  \"productId\": 35455,\n" +
					"  \"brandId\": 1,\n" +
					"  \"date\": \"2020-06-14 16:00\"\n" +
					"  }",
			"{\n" +
					"  \"productId\": 35455,\n" +
					"  \"brandId\": 1,\n" +
					"  \"date\": \"2020-06-14 21:00\"\n" +
					"}",
			"{\n" +
					"  \"productId\": 35455,\n" +
					"  \"brandId\": 1,\n" +
					"  \"date\": \"2020-06-15 10:00\"\n" +
					"}",
			"{\n" +
					"    \"productId\": 35455,\n" +
					"    \"brandId\": 1,\n" +
					"    \"date\": \"2020-06-15 21:00\"\n" +
					"}"
		};
	}

	private String[] expectedResults(){
		return new String[]{
				"{\n" +
						"    \"errors\": [\n" +
						"        \"Product 35455 is not available for sale\"\n" +
						"    ]\n" +
						"}",
				"{\n" +
						"    \"productId\": 35455,\n" +
						"    \"brandId\": 1,\n" +
						"    \"tax\": 0.5,\n" +
						"    \"startDate\": \"2020-06-14 15:00\",\n" +
						"    \"endDate\": \"2020-06-14 18:30\",\n" +
						"    \"price\": 25.45\n" +
						"}",
				"{\n" +
						"    \"productId\": 35455,\n" +
						"    \"brandId\": 1,\n" +
						"    \"tax\": 14.0,\n" +
						"    \"startDate\": \"2020-06-14 16:00\",\n" +
						"    \"endDate\": \"2020-12-31 23:59\",\n" +
						"    \"price\": 38.95\n" +
						"}",
				"{\n" +
						"    \"productId\": 35455,\n" +
						"    \"brandId\": 1,\n" +
						"    \"tax\": 14.0,\n" +
						"    \"startDate\": \"2020-06-14 16:00\",\n" +
						"    \"endDate\": \"2020-12-31 23:59\",\n" +
						"    \"price\": 38.95\n" +
						"}",
				"{\n" +
						"    \"productId\": 35455,\n" +
						"    \"brandId\": 1,\n" +
						"    \"tax\": 14.0,\n" +
						"    \"startDate\": \"2020-06-14 16:00\",\n" +
						"    \"endDate\": \"2020-12-31 23:59\",\n" +
						"    \"price\": 38.95\n" +
						"}"
		};
	}

	private String brandData(){
		return "{\n" +
				"  \"name\": \"theloop\",\n" +
				"  \"products\" : [\n" +
				"    {\n" +
				"      \"id\": 35455,\n" +
				"      \"name\": \"zerocups\",\n" +
				"      \"basePrice\": 24.95,\n" +
				"      \"curr\": \"EUR\",\n" +
				"      \"prices\": [\n" +
				"        {\n" +
				"          \"startDate\": \"2020-06-15 00:00\",\n" +
				"          \"endDate\": \"2020-12-31 23:59\",\n" +
				"          \"tax\": 10.55,\n" +
				"          \"priority\": 0\n" +
				"        },\n" +
				"        {\n" +
				"          \"startDate\": \"2020-06-14 15:00\",\n" +
				"          \"endDate\": \"2020-06-14 18:30\",\n" +
				"          \"tax\": 0.5,\n" +
				"          \"priority\": 1\n" +
				"        },\n" +
				"        {\n" +
				"          \"startDate\": \"2020-06-15 00:00\",\n" +
				"          \"endDate\": \"2020-06-15 11:00\",\n" +
				"          \"tax\": 5.55,\n" +
				"          \"priority\": 1\n" +
				"        },\n" +
				"        {\n" +
				"          \"startDate\": \"2020-06-14 16:00\",\n" +
				"          \"endDate\": \"2020-12-31 23:59\",\n" +
				"          \"tax\": 14,\n" +
				"          \"priority\": 1\n" +
				"        }\n" +
				"      ]\n" +
				"    }\n" +
				"  ]\n" +
				"}";
	}
}
