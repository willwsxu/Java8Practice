/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andy
 */
public class Java8Lamda {
    class Person {
        int age;
        String firstName, lastName;
        String  desc;
        
        public Person(String fn, String ln, int a)
        {
            age=a;
            firstName=fn;
            lastName=ln;
            desc = "["+firstName+" "+lastName+","+age+"]";
        }
        
        public int getAge()
        {
            return age;
        }
        
        public String getFistName()
        {
            return firstName;
        }
        
        public String getLastName()
        {
            return lastName;
        }        
        
        public String toString()
        {
            return desc;
        }
    }
    
    public void sortPerson()
    {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Andy", "Xu", 18));
        people.add(new Person("Annie", "Xu", 15));
        people.add(new Person("Annie", "Smith", 15));
        people.add(new Person("Anna", "Smith", 15));
        people.stream().forEach(System.out::println);
        Comparator<Person> cmp1=Comparator.comparing(Person::getAge);
        Comparator<Person> cmp2=Comparator.comparing(Person::getFistName);
        Comparator<Person> cmp3=Comparator.comparing(Person::getLastName);
        Comparator<Person> cmp=cmp1.thenComparing(cmp2).thenComparing(cmp3);
        people.sort(cmp);
        people.forEach(System.out::println);  // new iterable in Java 8
        people.removeIf(p->p.getAge()>15);
        System.out.println("size after removeIf: "+people.size());
        people.forEach(System.out::println);
        people.replaceAll(p->new Person(p.getFistName(), p.getLastName().toUpperCase(), p.getAge()));
        people.forEach(System.out::println);
    }
    
    public void testMap()
    {
        Map<String, List<Person>> map=new HashMap<>();
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Andy", "Xu", 18));
        people.add(new Person("Annie", "Xu", 15));
        map.put("Lincolnshire", people);
        map.putIfAbsent("Lincolnshire", Collections.emptyList());
        map.forEach(
        (city, p)->System.out.println(city+" has "+p.size()+" people"));
        Map<String, List<Person>> map2=new HashMap<>();
        map2.computeIfAbsent("Chicago", k->new ArrayList<>()).add(new Person("Anna", "Xu", 15));
        map2.computeIfAbsent("Lincolnshire", k->new ArrayList<>()).add(new Person("Hua", "Wang", 48));
        map2.computeIfAbsent("Lincolnshire", k->new ArrayList<>()).add(new Person("Will", "Xu", 50));
        // merge map2 into map
        map2.forEach(
        (c,p)->map.merge(c, p, 
                (p1, p2)->{
                    p1.addAll(p2);
                    return p1;
                }
            )
        );
        map.forEach(
        (c, p)->System.out.println(c+" : "+p));
    }
    
}
