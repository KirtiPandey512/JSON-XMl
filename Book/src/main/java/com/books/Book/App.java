package com.books.Book;

import java.util.List;

import com.books.entity.Book;
import com.books.service.ServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceImpl ser=new ServiceImpl();
       List<Book>books= ser.readFromJson();
        ser.print(books);
        ser.writeToXml(books);
    }
}
