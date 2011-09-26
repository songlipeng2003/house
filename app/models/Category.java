package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import utils.StringUtils;

@Entity
public class Category extends Model {
	public String key;

	@Required
	@MaxSize(value = 32)
	public String name;

	@Required
	public Integer sortOrder = 0;

	@ManyToOne
	public Category parent;

	@PreUpdate
	@PrePersist
	void updateKeyName() {
		key = StringUtils.toUpperCaseName(name);
	}

	public static List<Category> findTop() {
		return find("parent is null").fetch();
	}

	@Override
	public String toString() {
		return name;
	}
}
