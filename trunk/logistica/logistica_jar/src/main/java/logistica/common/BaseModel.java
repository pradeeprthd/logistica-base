package logistica.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable{

	public abstract Long getID();

	public abstract void setID(Long id);

	public boolean equals(Object arg0) {
		BaseModel o = (BaseModel) arg0;
		return getID().compareTo(o.getID()) == 0;
	}

}
