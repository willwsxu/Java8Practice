/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8practice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.*;

/**
 *
 * @author Andy
 */
public class Java8IO {
    
    // key=value
    // ;comment
    // #comment
    public void testBufferRead(String file)
    {
        try (BufferedReader reader = 
                new BufferedReader(
                new FileReader(
                new File(file))); ) 
        {
            Stream<String> stream = reader.lines();
            stream.filter(line->!line.startsWith(";"))
                   .findFirst()
                   .ifPresent(System.out::println);
        }
        catch (IOException e)
        {
            
        }        
    }
        
    public void testPathRead(String file)
    {
        Path path = Paths.get(file);
        try (Stream<String> stream = Files.lines(path) ) 
        {
            stream.filter(line->!line.startsWith(";"))
                   .findFirst()
                   .ifPresent(System.out::println);
        }
        catch (IOException e)
        {
            
        }        
    }
    public void testPathWalk(String p)
    {
        Path path = Paths.get(p);
        try (Stream<Path> stream = Files.walk(path, 2) ) 
        {
            stream.filter(p2->p2.toFile().isDirectory())
                   .forEach( System.out::println);
        }
        catch (IOException e)
        {
            
        }           
    }
    public void readMapfile(String file)
    {  
        Path path = Paths.get(file);
        try (Stream<String> stream = Files.lines(path) ) 
        {
            stream.filter(line->!line.startsWith(";"))
                   .forEach(line->{
                       String[] elems =line.split("=");
                       for (String str : elems)
                           System.out.println(str);
                   });
        }
        catch (IOException e)
        {
            
        }      
    }
    
    private void splitOne(String file, int which, long skip, long count) throws IOException
    {        
        Stream<String> stream = Files.lines(Paths.get(file));
        // build split file name, as filename.txt to filename-1.txt
        String[] file_ext = file.split("\\.");  // regex . escape
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<file_ext.length-1; i++)
        {
            builder.append(file_ext[i]);
            if ( i<file_ext.length-2)
                builder.append('.');
        }
        builder.append('-').append(which+1);
        if ( file_ext.length>=2 )
            builder.append('.').append(file_ext[file_ext.length-1]);
        
        System.out.println(builder+" count "+count);
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(builder.toString())));
        Consumer<String> consumer = line->{
            try
            {
                writer.write(line);
                writer.write("\r\n");
            }
            catch (IOException e){}
            };
        stream.skip(skip).limit(count).forEach(consumer);
        writer.close();
    }
    
    public void splitFile(String file, int splits) throws IOException
    {
        if ( splits<2)
            return;
        Stream<String> stream = Files.lines(Paths.get(file));
        long total = stream.count();
        long count = total/splits;  // stream is closed and cannot be reused
        System.out.println("total "+total);
        for (int i=0; i<splits-1; i++)
            splitOne(file, i, i*count, count);
        splitOne(file, splits-1, count*(splits-1), total-count*(splits-1));
    }
}
