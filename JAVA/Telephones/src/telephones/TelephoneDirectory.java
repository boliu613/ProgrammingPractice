package telephones;

import java.util.*;

public class TelephoneDirectory {
	HashMap<String, String> directory;

	/**
     *constructor
     */
	public TelephoneDirectory() {
		//allocate space for HashMap
		this.directory = new HashMap<String, String>();
	}

	/**
	 *add a single new person to the directory.
	 *phone number will be a string in the xxx-xxx-xxxx format.
	 */
	public void addEntry(String name, String phone){
		this.directory.put(name, phone);
	}

	/**
	 *get the number given a name
	 */
	public String getNumber(String name){
		if (this.directory.containsKey(name)){
			return this.directory.get(name);
		}
		return "This person is not in the directory.";
	}

	/**
	 *given a phone number(as a string) return who it belongs to
	 */
	public String whoCalled(String search_phone){
		Set<String> names = this.directory.keySet();
		for (String name : names) {
			if (this.directory.get(name) == search_phone) {
				return name;
			}
		}
		return "not found";
	}

	/**
	 *given a source directory, copy all names to this directory.
	 *make sure any existing information is not destroyed.
	 */
	public void copyDirectory(TelephoneDirectory sourceDirectory){
		Set<String> names = sourceDirectory.directory.keySet();
		for (String name : names) {
			this.directory.put(name, sourceDirectory.directory.get(name));
		}
	}
	/**
	 *return the directory entries one by one.
	 *Ensure that the directory is returned in sorted order.
	 */
	@Override
	public String toString() {
		String s = "";

		Set<String> names = this.directory.keySet();
		ArrayList<String> nameList = new ArrayList<String>();
		for (String name : names) {
			nameList.add(name);
		}         

		Collections.sort(nameList);

		for (int i =0 ; i < nameList.size(); i++) {            
			String name = nameList.get(i);
			s += name + " : " + this.directory.get(name) + '\n';
		}

		return s;
	}

	/**
	 *main function
	 */
	public static void main(String[] args) {
		TelephoneDirectory td = new TelephoneDirectory();
		td.addEntry("Dawn","551-421-4032");
		td.addEntry("Arvind","266-123-1111");
		TelephoneDirectory td1 = new TelephoneDirectory();
		td1.addEntry("Tyke","913-232-4851");
		td1.copyDirectory(td);
		System.out.println("Here are all the entries in the directory");
		System.out.println(td1);
		System.out.println(td.getNumber("Arvind"));
		System.out.println(td.whoCalled("913-232-4851"));
		System.out.println(td.whoCalled("551-421-4032"));

	}

}
