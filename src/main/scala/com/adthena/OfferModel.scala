package com.adthena

object OfferModel {

	trait SpecialOffers {
		def offerType: String
		def description: Option[String]
		def minimumUnits: Option[Int]
	}

	case class Offers(offerType: String, description: Option[String], minimumUnits: Option[Int],
										offerItem: String,OfferDiscount: Option[Double] = None,offerUnits: Option[Int] = None) extends SpecialOffers

	case class OfferProduct(price: Double, offer: Option[Offers])

	def productsOnOffer(): Map[String, OfferProduct] = {
		// This could be an API call to a NoSQL DB or cached in Redis
		Map("Milk" -> OfferProduct(1.30,None),
			"Bread" -> OfferProduct(0.80, None),
			"Soup" -> OfferProduct(0.65, Some(Offers("Multi buy", Some("Buy 2 and get Bread half price"), Some(2), "Bread", Some(0.5), Some(1)))),
			"Apples" -> OfferProduct(1.00,Some(Offers("Reduced",Some("Apples 10% off"),Some(1),"Apples",Some(0.1)))))
	}




}
