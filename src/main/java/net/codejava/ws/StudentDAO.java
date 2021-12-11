package net.codejava.ws;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO 
{
	private static StudentDAO instance;
	private static List<Student> data = new ArrayList<>();
	
	static
	{
		data.add(new Student(101,"Rajat Kulkarni","raj-01"));
		data.add(new Student(102,"Sam Thomson","sam=01"));
	}
	private StudentDAO() {}
	
	public static StudentDAO getInstance()
	{
		if (instance == null)
		{
			instance = new StudentDAO();
		}
		return instance;
	}
	
	public List<Student> listAll()
	{
		return new ArrayList<Student>(data);
	}
	
	public int add(Student student)
	{
		int newId = data.size()+1;
		student.setId(newId);
		data.add(student);
		
		return newId;
	}
	
	public Student get(int id)
	{
		Student studenttoFind = new Student(id);
		int index = data.indexOf(studenttoFind);
		if(index >= 0)
		{
			return data.get(index);
		}
		return null;
	}
	
	public boolean update(Student student)
	{
		int index = data.indexOf(student);
		if(index >= 0)
		{
			data.set(index, student);
			
			return true;
		}
		return false;
	}
	
	public boolean delete(int id)
	{
		Student studentToDelete = new Student(id);
		
		int index = data.indexOf(studentToDelete);
		if(index >= 0)
		{
			data.remove(index);
			
			return true;
		}
		return false;
	}
}
