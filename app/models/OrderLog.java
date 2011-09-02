package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import models.Order.OrderStatus;
import play.db.jpa.Model;

@Entity
public class OrderLog extends Model {
	@ManyToOne
	public Order order;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@Enumerated(EnumType.ORDINAL)
	public OrderStatus changedStatus;

	@Enumerated(EnumType.ORDINAL)
	public OrderStatus status;
}
