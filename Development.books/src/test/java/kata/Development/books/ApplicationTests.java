package kata.Development.books;

import kata.Development.books.entity.DevelopmentBook;
import kata.Development.books.restController.DevelopmentBookPricingModelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {
	
	private DevelopmentBook cleanCode;
	
	private DevelopmentBook cleanCoder;

	private DevelopmentBook cleanArchitecture;

	private DevelopmentBook testDrivenDevelopmentByExample;

	private DevelopmentBook workingEffectivelyWithLegacyCode;

	private DevelopmentBookPricingModelController developmentBookPricingModelController;

	private Map<DevelopmentBook, Integer> developmentBookBasketMap;

	@BeforeEach
	void setup () {
		cleanCode = new DevelopmentBook(1L, "Clean Code", "Robert Martin");
		cleanCoder = new DevelopmentBook(2L, "Clean Coder", "Robert Martin");
		cleanArchitecture = new DevelopmentBook(3L, "Clean Architecture", "Robert Martin");
		testDrivenDevelopmentByExample = new DevelopmentBook(4L, "Test Driven Development by Example", "Kent Beck");
		workingEffectivelyWithLegacyCode = new DevelopmentBook(5L, "Working effectively with Legacy Code", "Michael C. Feathers");
		developmentBookPricingModelController = new DevelopmentBookPricingModelController();
		developmentBookBasketMap = new HashMap<>();
	}
	@Test
	void developmentBooksWithDuplicatesComputePriceTest() {

		developmentBookBasketMap.put(cleanCode, 2);
		developmentBookBasketMap.put(cleanCoder, 2);
		developmentBookBasketMap.put(cleanArchitecture, 2);
		developmentBookBasketMap.put(testDrivenDevelopmentByExample, 1);
		developmentBookBasketMap.put(workingEffectivelyWithLegacyCode, 1);

		BigDecimal expectedPrice = new BigDecimal("320").setScale(2);

		BigDecimal finalPrice = developmentBookPricingModelController.computeDevelopmentBookBasketPrice(developmentBookBasketMap);

		assertEquals(expectedPrice, finalPrice);
	}
	@Test
	public void developmentBooksWithoutDuplicatesComputePriceTest() {
		/*List<DevelopmentBook> developmentBookBasket = new ArrayList<>();
		developmentBookBasket.add(cleanCode);
		developmentBookBasket.add(cleanCoder);
		developmentBookBasket.add(cleanArchitecture);
		developmentBookBasket.add(testDrivenDevelopmentByExample);
		developmentBookBasket.add(workingEffectivelyWithLegacyCode);

		BigDecimal expectedPrice = new BigDecimal("187.50");

		BigDecimal finalPrice = developmentBookPricingModelController.computeDevelopmentBookBasketPrice(developmentBookBasket);
		assertEquals(finalPrice, expectedPrice);*/
	}



}
