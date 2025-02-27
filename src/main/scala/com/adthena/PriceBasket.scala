package com.adthena
import OfferModel.*
import PriceFunction.*

object PriceBasket {

	def priceBasket(items: Array[String]): Unit = {
		val stockItems = productsOnOffer()
		// check if at least one item in items is in stock
		val checkStock = stockItems.filter { case (item, stockItem) => items.contains(item) }
		if (checkStock.nonEmpty) { // Price the order basket only if there is at least one item in the basket
			val itemPrice = checkStock.view.mapValues(_.price).toMap
			val itemQuantity = itemBasket(items)
			val basketPrice = itemQuantity.collect {
				case (item, quantity) if itemPrice.contains(item) => item -> (quantity * itemPrice(item))
			}
			val offerDescriptions = collection.mutable.ListBuffer[String]()
			val updatedPrice = collection.mutable.Map[String, Double]()
			// check for products on offer
			for ((item, stockItem) <- stockItems if stockItem.offer.isDefined && items.contains(item)) {
				val currentOffers = stockItem.offer.get
				if (currentOffers.offerType.contains("Reduced")) {
					val discount = currentOffers.OfferDiscount.getOrElse(0.0)
					updatedPrice(item) = calculatePriceAmount(stockItem.price, itemQuantity(item), Some(discount))
					val discountAmount = formatPrice(itemPrice(item) * discount)
					offerDescriptions += s"${currentOffers.description.get}: $discountAmount"
				}
				else if (currentOffers.offerType.contains("Multi buy")) {
					// check if the offer product is in the order items
					val offerProduct = currentOffers.offerItem
					if (itemQuantity.contains(offerProduct)) {
						// check if the minimum order quantity is satisfied
						val minUnits = currentOffers.minimumUnits.getOrElse(1)
						val orderQuantity = itemQuantity.get(item).get.intValue()
						// if order quantity is greater or equal to the minimumUnits then apply discount to the offerUnit
						if (orderQuantity >= minUnits) {
							val offerDiscount = currentOffers.OfferDiscount.getOrElse(0.0)
							val discount = currentOffers.OfferDiscount.getOrElse(0.0)
							val offerProductOrderQuantity = itemQuantity(offerProduct)
							val offerUnit = currentOffers.offerUnits.getOrElse(1) // minimum item quantity to apply offer discount
							if (offerProductOrderQuantity == offerUnit) {
								val offerProductPrice = itemPrice.get(offerProduct)
								val multiBuyPrice = calculatePriceAmount(itemPrice(offerProduct), offerProductOrderQuantity, Some(discount))
								updatedPrice(offerProduct) = calculatePriceAmount(itemPrice(offerProduct), offerProductOrderQuantity, Some(discount))
								offerDescriptions += currentOffers.description.get
							}
							else if (offerProductOrderQuantity > offerUnit) {
								// get the quantity of offer product to apply 10% discount
								val inOfferQuantity = offerUnit
								val inOfferPrice = calculatePriceAmount(itemPrice(offerProduct), inOfferQuantity, Some(discount))
								// get quantity to not apply discount
								val outOfferQuantity = offerProductOrderQuantity - offerUnit
								val outOfferPrice = calculatePriceAmount(itemPrice(offerProduct), outOfferQuantity)
								updatedPrice(offerProduct) = inOfferPrice + outOfferPrice
								offerDescriptions += currentOffers.description.get
							}
							else updatedPrice(offerProduct) = calculatePriceAmount(itemPrice(offerProduct),offerProductOrderQuantity )
						}
					}
				}
			}
			val newBasketPrice = basketPrice.map {
				case (item, price) =>
					updatedPrice.get(item) match {
						case Some(newPrice) if newPrice != price => item -> newPrice
						case _ => item -> price
					}
			}
			val subTotal = formatPrice(basketPrice.values.sum)
			val totalPrice = formatPrice(newBasketPrice.values.sum)
			val basketPriceOutput = s"Subtotal: $subTotal\n" +
				(if (offerDescriptions.nonEmpty) offerDescriptions.mkString("\n") else "(No offers available)") +
				s"\nTotal price: $totalPrice"
			println(basketPriceOutput)
		}

	}

	def main(args: Array[String]): Unit = {
		priceBasket(args)

	}

}
