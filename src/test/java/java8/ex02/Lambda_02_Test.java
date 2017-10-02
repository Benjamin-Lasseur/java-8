package java8.ex02;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

	interface Mapper<V>{
		V map(Person p);
	}

	// tag::map[]
	private  <V> List<V> map(List<Person> personList, Mapper<V> mapper) {
		// TODO implémenter la méthode pour transformer une liste de personnes
		// en liste de comptes
		List<V> newList = new ArrayList<V>();
		for (Person p : personList) {
			newList.add(mapper.map(p));
		}
		return newList;
	}

	// end::map[]

	// tag::test_map_person_to_account[]
	@Test
	public void test_map_person_to_account() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO transformer la liste de personnes en liste de comptes
		// TODO tous les objets comptes ont un solde à 100 par défaut
		List<Account> result = map(personList, p -> {
			Account a = new Account();
			a.setOwner(p);
			a.setBalance(100);
			return a;
		});

		assert result.size() == personList.size();
		for (Account account : result) {
			assert account.getBalance().equals(100);
			assert account.getOwner() != null;
		}
	}
	// end::test_map_person_to_account[]

	// tag::test_map_person_to_firstname[]
	@Test
	public void test_map_person_to_firstname() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO transformer la liste de personnes en liste de prénoms
		List<String> result = map(personList, p -> p.getFirstname());

		assert result.size() == personList.size();
		for (String firstname : result) {
			assert firstname.startsWith("first");
		}
	}
	// end::test_map_person_to_firstname[]
}
