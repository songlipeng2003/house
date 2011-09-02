package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
public class OrderProduct extends Model {
	@ManyToOne
	public Order order;

	@ManyToOne
	public Product product;

	@MaxSize(256)
	public String note;
}
