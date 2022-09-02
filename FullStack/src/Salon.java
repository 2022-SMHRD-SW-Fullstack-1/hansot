
public class Salon extends Store {

	double technology; // 기술점수 저장
	double kindness; // 친절점수

	public Salon(String name, String event, double technology, double kindness, double price) {
		this.name = name;
		this.event = event;
		this.technology = technology;
		this.kindness = kindness;
		this.price = price;
	}

	public double grade() {
		double t = this.technology;
		double k = this.technology;
		double avg = Math.round((t + k + price) / 3 * 100) / 100.0;
		return avg;
	}

	public double getTechnology() {
		return technology;
	}

	public void setTechnology(double technology) {
		this.technology = technology;
	}

	public double getKindness() {
		return kindness;
	}

	public void setKindness(double kindness) {
		this.kindness = kindness;
	}

	
}
