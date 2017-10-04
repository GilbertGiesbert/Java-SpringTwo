package joern.java.spring.two.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "treasures")
public class Treasure {
	
    private int id;
    private String name;
    private int valueInCents;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "treasure_id")
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value_in_cents")
	public int getValueInCents() {
		return valueInCents;
	}

	@Column(name = "value_in_cents")
	public void setValueInCents(int valueInCents) {
		this.valueInCents = valueInCents;
	}
}