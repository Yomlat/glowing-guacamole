import com.adthena.PriceFunction
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.wordspec.AnyWordSpec
import com.adthena.PriceFunction.*

object PriceBasketTest

class ItemSuite extends AnyFunSuite {
	val expectedOutput: Map[String, Int] = Map("Apples" -> 2, "Bread" -> 1)
	val input: Array[String] = Array("Apples","Bread", "Apples")
	assert(itemBasket(input)== expectedOutput)
}

class ItemQuantity extends AnyWordSpec {
	"A function" should {
		"return a Map with input Array of String" in {
			val expectedOutput: Map[String, Int] = Map("Apples" -> 2, "Bread" -> 1, "Soup" -> 2)
			val input: Array[String] = Array("Apples", "Apples", "Bread", "Soup", "Soup")
			assert(itemBasket(input) == expectedOutput)
		}
	}
}

class CalculateAmount extends AnyWordSpec {
		"A function should return a Double with discount" in {
			val expectedOutput: Double = 2.34
			val price: Double = 1.30
			val quantity: Int = 2
			val discount: Option[Double] = Some(0.1)
			assert(calculatePriceAmount(price, quantity, discount) == expectedOutput)
		}
		"A function should return a Double without discount" in {
			val expectedOutput: Double = 2.60
			val price: Double = 1.30
			val quantity: Int = 2
			assert(calculatePriceAmount(price, quantity) == expectedOutput)

		}
	}

class FormatPrice extends AnyWordSpec {
		"A function should format input value in British Pence" in {
			val expectedOutput: String = "50p"
			val inputValue: Double = 0.5
			assert(formatPrice(inputValue) == expectedOutput)
		}

		"A function should format input value in British Pounds" in {
			val expectedOutput: String = "£2.30"
			val inputValue: Double = 2.3
			assert(formatPrice(inputValue) == expectedOutput)
		}
	}

