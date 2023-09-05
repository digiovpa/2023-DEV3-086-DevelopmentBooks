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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {
	
	private DevelopmentBook cleanCode;
	
	private DevelopmentBook cleanCoder;

	private DevelopmentBook cleanArchitecture;

	private DevelopmentBook testDrivenDevelopmentByExample;

	private DevelopmentBook workingEffectivelyWithLegacyCode;

	private DevelopmentBookPricingModelController developmentBookPricingModelController;

	@BeforeEach
	void setup () {
		cleanCode = new DevelopmentBook(1L, "Clean Code", "Robert Martin");
		cleanCoder = new DevelopmentBook(2L, "Clean Coder", "Robert Martin");
		cleanArchitecture = new DevelopmentBook(3L, "Clean Architecture", "Robert Martin");
		testDrivenDevelopmentByExample = new DevelopmentBook(4L, "Test Driven Development by Example", "Kent Beck");
		workingEffectivelyWithLegacyCode = new DevelopmentBook(5L, "Working effectively with Legacy Code", "Michael C. Feathers");
		developmentBookPricingModelController = new DevelopmentBookPricingModelController();
	}
	@Test
	void developmentBooksWithDuplicatesComputePriceTest() {
     /*

    	2 different books -> 5% discount on those 2 books.
		3 different books -> 10% discount on those 3 books.
		4 different books -> 20% discount on those 4 books.
		All 5 -> 25% discount on those 5 books.

		2 copies of the “Clean Code” book
		2 copies of the “Clean Coder” book
		2 copies of the “Clean Architecture” book
		1 copy of the “Test Driven Development by Example” book
		1 copy of the “Working effectively with Legacy Code” book

		Answer :

		(4 * 50 EUR) - 20% [first book, second book, third book, fourth book]
		(4 * 50 EUR) - 20% [first book, second book, third book, fifth book]
        = 160 EUR + 160 EUR = 320 EUR

      */
		List<DevelopmentBook> developmentBookBasket = new ArrayList<>();

		developmentBookBasket.add(cleanCode);
		developmentBookBasket.add(cleanCode);
		developmentBookBasket.add(cleanCoder);
		developmentBookBasket.add(cleanCoder);
		developmentBookBasket.add(cleanArchitecture);
		developmentBookBasket.add(cleanArchitecture);
		developmentBookBasket.add(testDrivenDevelopmentByExample);
		developmentBookBasket.add(workingEffectivelyWithLegacyCode);

		BigDecimal expectedPrice = new BigDecimal("320");

		BigDecimal finalPrice = developmentBookPricingModelController.computeDevelopmentBookBasketPrice(developmentBookBasket);

		assertEquals(finalPrice, expectedPrice);
	}
	@Test
	public void developmentBooksWithoutDuplicatesComputePriceTest() {
		List<DevelopmentBook> developmentBookBasket = new ArrayList<>();

		developmentBookBasket.add(cleanCode);
		developmentBookBasket.add(cleanCoder);
		developmentBookBasket.add(cleanArchitecture);
		developmentBookBasket.add(testDrivenDevelopmentByExample);
		developmentBookBasket.add(workingEffectivelyWithLegacyCode);

		BigDecimal expectedPrice = new BigDecimal("187.50");

		BigDecimal finalPrice = developmentBookPricingModelController.computeDevelopmentBookBasketPrice(developmentBookBasket);
		assertEquals(finalPrice, expectedPrice);
	}



}
