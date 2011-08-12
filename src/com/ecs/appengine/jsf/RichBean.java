package com.ecs.appengine.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RichBean implements Serializable {

    private static final long serialVersionUID = -2403138958014741653L;
    private String name;

    public RichBean() {
        System.out.println("post construct: initialize");
        name = "John";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Person> getPeople() {
    	List<Person> people = new ArrayList<Person>();
    	
    	for (int i=0; i< 100 ; i++) {
    		people.add(new Person("firstname " + i,"lastname " + i,"email " + i));
    	}
    	return people;
    }
}
