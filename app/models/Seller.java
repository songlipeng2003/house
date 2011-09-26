/**
 *
 */
package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import play.data.validation.MaxSize;
import play.data.validation.Phone;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

@Entity
public class Seller extends GenericModel {

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = { @Parameter(name = "property", value = "user") })
	@GeneratedValue(generator = "generator")	
	public Long userId;

	@OneToOne(cascade = CascadeType.PERSIST)
	@PrimaryKeyJoinColumn
	public User user;
	
	@ManyToOne
	//@Required
	public Category category;

	@MaxSize(value = 256)
	public String companyName;
	
	@MaxSize(value = 256)
	public String companyAddress;

	public String bank;
	
	public String bankNum;
	
	public String phone;
	
	//@Required
	public String mobile;
	
	public String card;
	
	@Enumerated(EnumType.ORDINAL)
	public SellerType sellerType = SellerType.COMPONY;
	
	public enum SellerType{
		PERSONAL,COMPONY
	}

}
