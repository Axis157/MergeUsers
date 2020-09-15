package MergeUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<Person> persons = new ArrayList<>();
		
		System.out.println("¬вод юзера:");
		String str = "";
		while((str = reader.readLine()) != "") {
			if(!str.contains(" -> ")) break;
			String[] s = str.split(" -> ");
			Person person = new Person();
			person.setUserName(s[0]);
			if(s[1].contains(", ")) {
				person.setUserEmail(new ArrayList<>(Arrays.asList(s[1].split(", "))));
			}
			else {
				person.setUserEmail(new ArrayList<>(Arrays.asList(s[1])));
			}
			persons.add(person);
		}
		reader.close();
		
		AccountService accountService = new AccountService(persons);
		accountService.mergePersons();
		for(Person p: accountService.getPersons()) {
			System.out.print("\n"+p);
		}

	}
}

