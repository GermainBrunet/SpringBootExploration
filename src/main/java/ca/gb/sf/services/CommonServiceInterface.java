package ca.gb.sf.services;

public interface CommonServiceInterface<T> {

	public long count();
	
	public T find(String s);

	public T findById(Long id);

	public T save(String s);
	
	public T save(T t);
	
	public void deleteAll();
	
	public void deleteById(Long id);
	
}
