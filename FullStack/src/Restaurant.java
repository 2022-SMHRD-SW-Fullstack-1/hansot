
public class Restaurant extends Store {

	double service;
	double flavor;
	double clean;
	
	public Restaurant(String name, String event, double service, double flavor, double clean, double price ){
	this.name = name;
	this.event = event;
	this.service = service;
	this.flavor = flavor;
	this.clean = clean;
	this.price = price;
	
	}
	
	public double grade() {
		double grade = (service + flavor + clean) / 3;
		
		return Math.round((grade*100)/100.0);
	}

	public double getService() {
		return service;
	}

	public void setService(double service) {
		this.service = service;
	}

	public double getFlavor() {
		return flavor;
	}

	public void setFlavor(double flavor) {
		this.flavor = flavor;
	}

	public double getClean() {
		return clean;
	}

	public void setClean(double clean) {
		this.clean = clean;
	}
	
	
	
}
