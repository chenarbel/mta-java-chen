package com.mta.chen;

public class Bicycle {
	
	private final int WHEELS_NUMBER;
	private String ownerName;
	private Brand brand;

	public Bicycle( ) { 
		WHEELS_NUMBER = 2;
		ownerName = "Unknown";
	}
	
	public Bicycle(String name) { 
		WHEELS_NUMBER = 2;
		ownerName = name;
	}
	public String getOwnerName( ) { 
		return ownerName;
	}
	public void setOwnerName(String name) {				
		ownerName = name;
	}

	public Brand getBrand() {
		return brand;
	}
	public void setBrand(String brandName) {
		this.brand = new Brand(brandName);
		System.out.println("this.brand address: "+this.brand);
	}

	public class Brand {
		private String brandName;
		public Brand(String brandName)  {
			this.brandName = brandName;
		}
	}

	public static void main(String[] args) {
		
		Bicycle bike1, bike2;
		String owner1, owner2;
		
		bike1 = new Bicycle( );
		bike1.setOwnerName("Adam Smith");
		bike1.setBrand("Fasta");

		bike2 = bike1;
		bike2.setOwnerName("Ben Jones");
		bike2.setBrand("Shina");
		
		System.out.println("Bike 2 owner: "+bike2.getOwnerName());
		System.out.println("Bike 2 owner: "+bike2.getBrand());
	}
}
