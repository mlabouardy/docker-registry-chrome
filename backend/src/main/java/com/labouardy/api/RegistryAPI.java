package com.labouardy.api;

import com.labouardy.model.Message;
import com.labouardy.model.Registry;
import com.labouardy.model.Repository;

public interface RegistryAPI {
	public Repository repositories(Registry registry);
	public Message ping(Registry registry);
}
