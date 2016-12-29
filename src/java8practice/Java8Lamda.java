/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    
    public void testStreamMapReduce()
    {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Andy", "Xu", 18));
        people.add(new Person("Annie", "Xu", 15));
        // map will change to stream of int in following example
        people.stream().map(p->p.getAge()).filter(age->age>15).forEach(System.out::println);
        // use intStream to aboid bosing/unboxing performance losss
        IntStream istream =people.stream().mapToInt(p->p.getAge());
        System.out.println("average age: "+istream.average()); // return optional double
        // intermediate calls return a stream, no actual work is done
        // terminal calls return other types, or void
        // skip () skip from beginning of the stream
        // limit() keep # item from beginning, discard the rest
        boolean b = people.stream().allMatch(p->p.getAge()>15); // false;
        Optional<Person> opt = people.stream().filter(p->p.getAge()>20).findFirst();
        Random rand = new Random();
        Stream<Integer> stream = Stream.generate(()->rand.nextInt());
        stream.limit(3).forEach(System.out::println);
        
        stream = Stream.iterate(1, a->a*2);
        stream.limit(5).forEach(System.out::println);
        
        istream = ThreadLocalRandom.current().ints();
        istream.limit(3).forEach(System.out::println);
    }
    
    public void testFlatMapper()
    {
        //http://introcs.cs.princeton.edu/java/home/
        //http://introcs.cs.princeton.edu/java/data/
        //http://onlinebooks.library.upenn.edu/webbin/gutbook/lookup?num=74
        try {
            Stream<String> s1 = Files.lines(Paths.get("TomSawyer-1.txt"));
            Stream<String> s2 = Files.lines(Paths.get("TomSawyer-2.txt"));
            Stream<String> s3 = Files.lines(Paths.get("TomSawyer-3.txt"));
            Stream<String> s4 = Files.lines(Paths.get("TomSawyer-4.txt"));
            Function<String, Stream<String>> lineSplitter = line->Pattern.compile(" ").splitAsStream(line);
            Function<String, Stream<String>> lineSplitter2 = line->Pattern.compile("-").splitAsStream(line);
            Stream<String> lineStream = Stream.of(s1, s2, s3, s4).flatMap(Function.identity());
            // REGEX ^[\\[\"'-(]+
            //[\\]\"'-(),_:;!.?]+$
            Stream<String> wordStream = lineStream.flatMap(lineSplitter).flatMap(lineSplitter2)
                    .map(word->word.toUpperCase().replaceAll("^\\W+", "").replaceAll("\\W+$", ""));//.distinct().sorted().peek(System.out::println);
            //System.out.println("Total word count :"+wordStream.count());
            // count of word used
            Map<String, Integer> wordMap = wordStream.collect(Collectors.toMap(k->k, v->1, (v1,v2)->v1+v2));
            System.out.println("map word count :"+wordMap.size());
            // print sorted map by value
            wordMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue((v1,v2)->v2.compareTo(v1)))  // sort from large to small
                    .forEach(entry->System.out.println(entry.getKey()+" count "+entry.getValue()));
        }
        catch (IOException e)
        {
            
        }
    }
    public void scrabbleScore() throws IOException
    {
        // official scrable words
        Set<String> scrabbleWords = Files.lines(Paths.get("ospd.txt"))
                .map(word->word.toLowerCase()).collect(Collectors.toSet());
        Set<String> shakespearWords = Files.lines(Paths.get("words.shakespeare.txt"))
                .map(word->word.toLowerCase()).collect(Collectors.toSet());
        // letter A-Z scrabble value
        final int[] scrabbleENScore={1,3,3,2,1,4,2,4,1,8,5,1,3,1,3,3,10,1,1,1,1,4,4,8,4,10};
        Function<String,Integer> score = word->word.chars().map(letter->scrabbleENScore[letter-'a']).sum();
        // use int to avoid auto boxing as chars() is IntStream
        ToIntFunction<String> intScore = word->word.chars().map(letter->scrabbleENScore[letter-'a']).sum();
        System.out.println("Score of Hello: "+intScore.applyAsInt("hello"));
        String bestWord = shakespearWords.stream()
                .filter(word->scrabbleWords.contains(word))
                .max(Comparator.comparing(score)).get();
        System.out.println("Best Shakespear scrabble word: "+bestWord);
        IntSummaryStatistics summary = shakespearWords.stream().parallel()
                .filter(scrabbleWords::contains)
                .mapToInt(intScore)
                .summaryStatistics();
        System.out.println("Shakespear scrabble word summary: "+summary);
    }
}
