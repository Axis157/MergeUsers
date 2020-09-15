package MergeUsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountService {
	private static List<Person> persons = new ArrayList<Person>();

	public AccountService(List<Person> persons) {
		this.persons = persons;
	}
	
	public void addPerson(Person person) {
		persons.add(person);
	}
	
	public List<Person> getPersons(){
		return persons;
	}
	
	public void mergePersons() {
		for(int i = 0; i < persons.size(); i++) {
			if(i == (persons.size()-1)) break;
			for(int z = i + 1; z < persons.size(); z++) {
				if(persons.get(i).getUserName().equalsIgnoreCase(persons.get(z).getUserName())) break;
				for(int x = 0; x < persons.get(z).getUserEmail().size(); x++) {
					if(persons.get(i).getUserEmail().contains(persons.get(z).getUserEmail().get(x))) {
						persons.get(i).getUserEmail().addAll(persons.get(z).getUserEmail());
						persons.get(i).getUserEmail().sort((o1,o2) -> o1.compareTo(o2));
						persons.set(z, persons.get(i));
						
						break;
					}
				}
			}
		}
		this.persons = persons.stream().distinct().collect(Collectors.toList());
		for(Person person: persons) {
			person.setUserEmail(new ArrayList<>(person.getUserEmail().stream().distinct().collect(Collectors.toList())));
		}
	}
}
