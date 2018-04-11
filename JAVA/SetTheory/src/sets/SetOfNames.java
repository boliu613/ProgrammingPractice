package sets;

import java.util.*;

public class SetOfNames {

	ArrayList<String> nameSet;
	/**
	 * constructor
	 */
	public SetOfNames() {
		nameSet = new ArrayList<String>();
	}

	/**
	 * add an element to the set
	 * @param name
	 */
	public void add(String name) {
		if (!isElementOf(name)){
			nameSet.add(name);
		}
	}

	/**
	 * add an array of strings to the set
	 * @param names
	 */
	public void add(String[] names) {
		for (String name : names){
			if (!isElementOf(name)){
				nameSet.add(name);
			}
		}
	}

	/**
	 * delete a string from the set
	 * @param name
	 */
	public void delete(String name){
		nameSet.remove(name);
	}

	/**
	 * check whether a given string is an element of this set
	 * @param name
	 * @return
	 */
	public boolean isElementOf(String name){
		return nameSet.contains(name);
	}
	
	/**
	 * computes the intersection of this set with the other set that is being passed in
	 * returns a set that represents the intersection.
	 * @param otherSet
	 * @return
	 */
	SetOfNames intersect(SetOfNames otherSet){
		SetOfNames resultSet = new SetOfNames();
		for (String name : nameSet){
			if (otherSet.isElementOf(name)){
				resultSet.add(name);
			}
		}
		return resultSet;
	}
	
	
	/**
	 * computes the union of this set with the other set that is being passed in
	 * returns a set that represents the union. 
	 * @param otherSet
	 * @return
	 */
	SetOfNames union (SetOfNames otherSet){
		SetOfNames resultSet = new SetOfNames();
		for (String name : nameSet){
			resultSet.add(name);
		}
		for (String name : otherSet.nameSet){
			resultSet.add(name);
		}
		return resultSet;
	}
	
	/**
	 * computes the set that consists of elements in this set that cannot be found in the otherSet
	 * returns a set of names.
	 * @param otherSet
	 * @return
	 */
	SetOfNames difference(SetOfNames otherSet){
		SetOfNames resultSet = new SetOfNames();
		for (String name : nameSet){
			if (!otherSet.isElementOf(name)){
				resultSet.add(name);
			}
		}
		for (String name : otherSet.nameSet){
			if (!isElementOf(name)){
				resultSet.add(name);
			}
		}
		return resultSet;
	}
	
	/**
	 * returns true or false depending upon whether all the elements of otherSet can be found in this set.
	 * @param otherSet
	 * @return
	 */
	boolean isSubset(SetOfNames otherSet){
		
		for (String name : otherSet.nameSet){
			if (!isElementOf(name)){
				return false;
			}
		}		
		return true;
	}
	
	/**
	 * returns the number of elements in the set
	 * @return
	 */
	int cardinality(){
		return nameSet.size();
	}



	/**
	 *return the nameSet entries one by one.
	 */
	@Override
	public String toString() {

		if (nameSet.size() == 0){
			return "emptyset";
		}

		String s = "{";

		for (int i =0 ; i < nameSet.size()-1; i++) { 
			s += nameSet.get(i) + ", ";
		}
		s += nameSet.get(nameSet.size()-1);
		s += '}';
		return s;
	}
}
