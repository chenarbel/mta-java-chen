package com.mta.chen.model;

import java.util.Date;
import com.mta.chen.model.Portfolio.ALGO_RECOMMENDATION;

public class StockStatus extends Stock{
	/**
	 * An instance of this class represents current status of stock and reccomendation
	 * this class includes:
	 * -constants members, premitive members
	 * -setter & gettes to those members
	 * -c'tor which gets values (overloading), copy c'tor
	 * @author Chen Arbel
	 * @since 31/12/14
	 */
		private ALGO_RECOMMENDATION recommendation;
		int stockQuantity;

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

		//c'tor
		public StockStatus (String symbol, float Ask, float Bid, Date date ,ALGO_RECOMMENDATION recommendation, int stockQuantity){
			super(symbol, Ask, Bid, date);
			this.recommendation = recommendation;
			this.stockQuantity = stockQuantity;	
		}

		//copy c'tor
		public StockStatus (StockStatus stockStatus){
			super(stockStatus);
			this.recommendation =stockStatus.getRecommendation();
			this.stockQuantity = stockStatus.getStockQuantity();
		}
}
