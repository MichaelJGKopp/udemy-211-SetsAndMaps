package dev.lpa;

import java.util.*;

public class MapViewsMain {

  public static void main(String[] args) {

    Map<String, Contact> contacts = new HashMap<>();
    ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
    // put adds first and then replaces if key exists already
    ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

    var keysView = contacts.keySet(); // key view modifiable and backed by map
    System.out.println(keysView);

    Set<String> copyOfKeys = new TreeSet<>(keysView);
    System.out.println(copyOfKeys);

    if (contacts.containsKey("Linus Van Pelt")) {
      System.out.println("Linus and I go way back, so of course I have info.");
    }

//    System.out.println("-----------------------------------------------------");
//    contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
    keysView.remove("Daffy Duck");
    System.out.println("-----------------------------------------------------");
    System.out.println(keysView);
    contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
    System.out.println("-----------------------------------------------------");

    copyOfKeys.remove("Linus Van Pelt");
    System.out.println(copyOfKeys);
    contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
    System.out.println("-----------------------------------------------------");

    keysView.retainAll(List.of("Linus Van Pelt", "Charlie Brown", "Robin Hood", "Mickey Mouse"));
    System.out.println(keysView);
    contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
    System.out.println("-----------------------------------------------------");

    keysView.clear();
    System.out.println(contacts);

    // keysView.add("Daffy Duck"); // can't be used on a key set, since map values are missing

    ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
    ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
    System.out.println(keysView); // view refreshed automatically with underlying map

    var values = contacts.values();
    values.forEach(System.out::println);
    System.out.println("-----------------------------------------------------");

    values.retainAll(ContactData.getData("email"));
    System.out.println(keysView);
    contacts.forEach((k, v) -> System.out.println(v));

    System.out.println("-----------------------------------------------------");
    List<Contact> list = new ArrayList<>(values);
    list.sort(Comparator.comparing(Contact::getNameLastFirst));
    list.forEach(c -> System.out.println(c.getNameLastFirst() + ": " + c));

    System.out.println("-----------------------------------------------------");
    Contact first = list.get(0);
    contacts.put(first.getNameLastFirst(), first);
    values.forEach(System.out::println);
    keysView.forEach(System.out::println);

    HashSet<Contact> set = new HashSet<>(values);
    set.forEach(System.out::println);
    if (set.size() < contacts.keySet().size()) {
      System.out.println("Duplicate Values are in my Map");
    }

    var nodeSet = contacts.entrySet();
    for (var node : nodeSet) {
      System.out.println(nodeSet.getClass().getName());
      if (!node.getKey().equals(node.getValue().getName())) {
        System.out.println(node.getClass().getName());
        System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
      }
    }
  }

}
