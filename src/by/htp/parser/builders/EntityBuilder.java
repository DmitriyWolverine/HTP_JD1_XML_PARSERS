package by.htp.parser.builders;

import by.htp.parser.bean.Entity;
import by.htp.parser.tags.MyTags;

public abstract class EntityBuilder {
	protected Entity entity;
	
	public EntityBuilder(Entity entity) {
		super();
		this.entity = entity;
	}

	public abstract Entity buildEntity(MyTags tags,StringBuilder text);
	public abstract void setParametr(MyTags tags,StringBuilder text);
	
}
