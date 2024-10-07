package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class MapMain {

  public static void main(String[] args) {

    List<Contact> phones = ContactData.getData("phone");
    List<Contact> emails = ContactData.getData("email");

    List<Contact> fullList = new ArrayList<>(phones);
    fullList.addAll(emails);
    fullList.forEach(System.out::println);
    System.out.println("-------------------------------");
  }
}
