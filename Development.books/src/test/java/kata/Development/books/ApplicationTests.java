package kata.Development.books;

import kata.Development.books.entity.DevelopmentBook;
import kata.Development.books.service.DevelopmentBookPricingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {
	
	private DevelopmentBook cleanCode;
	
	private DevelopmentBook cleanCoder;

	private DevelopmentBook cleanArchitecture;

	private DevelopmentBook testDrivenDevelopmentByExample;

	private DevelopmentBook workingEffectivelyWithLegacyCode;

	private DevelopmentBookPricingServiceImpl developmentBookPricingService;

	private Map<String, String> developmentBookBasketMap;

	@BeforeEach
	void setup () {
		cleanCode = new DevelopmentBook(1, "Clean Code", "Robert Martin");
		cleanCoder = new DevelopmentBook(2, "Clean Coder", "Robert Martin");
		cleanArchitecture = new DevelopmentBook(3, "Clean Architecture", "Robert Martin");
		testDrivenDevelopmentByExample = new DevelopmentBook(4, "Test Driven Development by Example", "Kent Beck");
		workingEffectivelyWithLegacyCode = new DevelopmentBook(5, "Working effectively with Legacy Code", "Michael C. Feathers");
		developmentBookPricingService = new DevelopmentBookPricingServiceImpl();
		developmentBookBasketMap = new HashMap<>();
	}
	@Test
	void developmentBooksWithDuplicatesComputePriceTest() {

		developmentBookBasketMap.put("1", "2");
		developmentBookBasketMap.put("2", "2");
		developmentBookBasketMap.put("3", "2");
		developmentBookBasketMap.put("4", "1");
		developmentBookBasketMap.put("5", "1");

		BigDecimal expectedPrice = new BigDecimal("320.00");
		Map<Integer, Integer> convertedMap = developmentBookPricingService.convertDevelopmentBookBasketMapToIntegerMap(developmentBookBasketMap);
		BigDecimal finalPrice = developmentBookPricingService.computeDevelopmentBookBasketPrice(convertedMap);

		assertEquals(expectedPrice, finalPrice);
	}

	@Test
	void developmentBooksWithDuplicatesComputePriceTest2() {

		developmentBookBasketMap.put("1", "3");
		developmentBookBasketMap.put("2", "3");
		developmentBookBasketMap.put("3", "3");
		developmentBookBasketMap.put("4", "2");
		developmentBookBasketMap.put("5", "1");

		BigDecimal expectedPrice = new BigDecimal("482.50");
		Map<Integer, Integer> convertedMap = developmentBookPricingService.convertDevelopmentBookBasketMapToIntegerMap(developmentBookBasketMap);
		BigDecimal finalPrice = developmentBookPricingService.computeDevelopmentBookBasketPrice(convertedMap);

		assertEquals(expectedPrice, finalPrice);
	}

	@Test
	void developmentBooksWithDuplicatesComputePriceTest3() {

		developmentBookBasketMap.put("1", "3");
		developmentBookBasketMap.put("2", "3");
		developmentBookBasketMap.put("4", "2");
		developmentBookBasketMap.put("5", "1");

		BigDecimal expectedPrice = new BigDecimal("390.00");
		Map<Integer, Integer> convertedMap = developmentBookPricingService.convertDevelopmentBookBasketMapToIntegerMap(developmentBookBasketMap);
		BigDecimal finalPrice = developmentBookPricingService.computeDevelopmentBookBasketPrice(convertedMap);

		assertEquals(expectedPrice, finalPrice);
	}
	@Test
	public void developmentBooksWithoutDuplicatesComputePriceTest() {
		developmentBookBasketMap.put("1", "1");
		developmentBookBasketMap.put("2", "1");
		developmentBookBasketMap.put("3", "1");
		developmentBookBasketMap.put("4", "1");
		developmentBookBasketMap.put("5", "1");

		BigDecimal expectedPrice = new BigDecimal("187.50");
		Map<Integer, Integer> convertedMap = developmentBookPricingService.convertDevelopmentBookBasketMapToIntegerMap(developmentBookBasketMap);
		BigDecimal finalPrice = developmentBookPricingService.computeDevelopmentBookBasketPrice(convertedMap);

		assertEquals(expectedPrice, finalPrice);
	}


}
