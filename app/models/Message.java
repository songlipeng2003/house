package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Range;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Message extends Model {

	@ManyToOne
	public User user;

	@ManyToOne
	public User sender;

	@Required
	@MaxSize(255)
	@MinSize(6)
	public String title;

	@Required
	public String content;

	public Integer userdel = 0;// 0未删除，1删除

	public Integer senderdel = 0;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@Temporal(TemporalType.TIMESTAMP)
	public Date lasttime;

	@PrePersist
	void onPrePersist() {
		created = new Date();
		userdel = 0;
		senderdel = 0;
	}

	@PreUpdate
	void onPreUpdate() {
		lasttime = new Date();
	}
}
