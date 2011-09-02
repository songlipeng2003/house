package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class EmailLog extends Model{
	@Temporal(TemporalType.TIMESTAMP)
	public Date created;
	
	public String from;
	
	public String to;
	
	public String subject;
	
	public String body;
}
