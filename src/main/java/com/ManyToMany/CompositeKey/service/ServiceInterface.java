package com.ManyToMany.CompositeKey.service;

import java.util.List;

public interface ServiceInterface<T> {
	
	public List<T> getAll ();
	
	public T getById (Integer id);
	
	public T add (T itemToBeAdded);
	
	public T edit (T itemToBeEdited);
	
	public boolean delete (int id);
}
