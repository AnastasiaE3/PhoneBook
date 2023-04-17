package com.zipcodewilmington.phonebook;

import java.util.List;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by leon on 1/23/18.
 * Made WAY better by kristofer 6/16/20
 */
public class PhoneBook {

    private final Map<String, List<String>> phonebook; //declares an instance variable phonebook of type Map<String,
                                                        // List<String>> that will hold the phone book data.

    public PhoneBook(Map<String, List<String>> map) {
        /*
        This is a constructor of the PhoneBook class that takes a Map<String,
        List<String>> as an argument.
        It initializes the phonebook instance variable with the provided map if it is not null,
        otherwise it initializes it with a new instance of LinkedHashMap.
         */
        this.phonebook = map != null ? map : new LinkedHashMap<>();//LinkedHashMap is used here to maintain the order of entries as they were added to the map.

    }

    public PhoneBook() {
        this(null);
    }

    public void add(String name, String phoneNumber) { //This method is responsible for adding the phone number to the phone book under the given name.
        phonebook.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
        /*
        This code retrieves the list of phone numbers for the given name from the phonebook map.
         If the name is not already present in the map, it creates a new empty list and adds it to the map.
         This is done using the computeIfAbsent() method,
         which takes in the name as the key and a lambda expression as the value.
         The lambda expression creates a new ArrayList if the key is not present in the map.
         add method adds given phoneNumber to the list of numbers for the given name.
         */
    }

    public void addAll(String name, String... phoneNumbers) { //adding all the phone numbers in the array
        //iterates through each element in the phoneNumbers array.
        //declares a new phoneNumber variable and assigns each element of the array to it on each iteration.
        for (String phoneNumber : phoneNumbers) {
            //adding the phoneNumber to the list of phone numbers for the given name.
            add(name, phoneNumber);
        }
    }

    public void remove(String name) {
        phonebook.remove(name);
    }

    public Boolean hasEntry(String name) {
        //this method checks if the given name exists in the phone book and returns true if it does and false if it does not.
        return phonebook.containsKey(name);
    }

    public List<String> lookup(String name) {
        // returns a List of phone numbers associated with the given name in the phone book
        // or an empty ArrayList if the key is not present in the phone book.
        return phonebook.getOrDefault(name, new ArrayList<>());
    }

    public String reverseLookup(String phoneNumber)  {
        // For each entry, the method checks if the list of phone numbers associated with the entry
        // (accessed with entry.getValue()) contains the phoneNumber passed as a parameter.
        // if it does, the method returns the key of the entry -
        // which represents the name associated with the phone number - with entry.getKey().
        for (Map.Entry<String, List<String>> entry : phonebook.entrySet()) {
            if (entry.getValue().contains(phoneNumber)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<String> getAllContactNames() {

        return new ArrayList<>(phonebook.keySet());
    }

    public Map<String, List<String>> getMap() {

        return new LinkedHashMap<>(phonebook);
    }

    public boolean hasEntry(String expectedName, String phoneNumber) { //checks whether the phone book contains a specific entry consisting of the given name and phone number.
        //It does so by first getting the list of phone numbers
        // for the given name from the phonebook map using the get() method.
        List<String> phoneNumbers = phonebook.get(expectedName);
        //If the list of phone numbers is not null and contains the given phone number,
        // the method returns true. Otherwise, it returns false.
        return phoneNumbers != null && phoneNumbers.contains(phoneNumber);
    }
}
