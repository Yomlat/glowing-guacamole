package com.adthena

object PriceFunction {
	

	def itemBasket(items: Array[String]): Map[String, Int] = {
		items.groupBy(identity).map{case (x, y) => x -> y.length}
	}

	def calculatePriceAmount(productCost: Double, quantity: Int, discount: Option[Double] = None): Double = {
		val totalAmount = productCost * quantity
		discount.map(d => totalAmount - (totalAmount * d)).getOrElse(totalAmount)
	}

	def formatPrice(amount: Double): String = {
		if (amount >= 1.00)  f"£$amount%.2f" else f"${(amount * 100).toInt}p"
	}

}
