package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Order extends Model{
	@Required
	@ManyToOne
	public User user;
	
	//地址信息
	@Required
	@ManyToOne
	public Country country;

	@Required
	@MaxSize(value = 256)
	public String province;

	@Required
	@MaxSize(value = 256)
	public String city;

	@Required
	@MaxSize(value = 256)
	public String address;

	@Required
	@MaxSize(value = 64)
	public String firstName;

	@Required
	@MaxSize(value = 64)
	public String lastName;

	@Required
	@MaxSize(value = 32)
	public String phone;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updated;
	
	public enum OrderStatus{
		
	}
}
