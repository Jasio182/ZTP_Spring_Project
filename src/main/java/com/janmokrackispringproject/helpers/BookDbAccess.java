package com.janmokrackispringproject.helpers;

import com.janmokrackispringproject.beans.Book;
import com.janmokrackispringproject.requests.AddBookRequest;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDbAccess extends DbAccess  {

    public Book AddBook(AddBookRequest addBookRequest) throws Exception {
        Statement statement = GetConnection().createStatement();
        String query = "INSERT INTO Books VALUES ('"+addBookRequest.getTitle()+"', '"+addBookRequest.getAuthor()+"', '"+ addBookRequest.getYear() +"');";
        statement.executeUpdate(query);
        return new Book(addBookRequest.getTitle(), addBookRequest.getAuthor(), addBookRequest.getYear());
    }

    public boolean RemoveBook(String title) throws Exception {
            Statement statement = GetConnection().createStatement();
            String query = "DELETE FROM Books Where title = '"+title+"';";
            statement.executeUpdate(query);
            return true;
    }

    public ArrayList<Book> GetBooks() throws Exception {
        Statement statement = GetConnection().createStatement();
        String query = "SELECT * FROM Books";
        ResultSet result = statement.executeQuery(query);
        ArrayList<Book> books = new ArrayList<Book>();
        while (result.next()) {
            books.add(new Book(result.getString("title"), result.getString("author"), result.getInt("year")));
        }
        return books;
    }

    public Book GetBook(int id) throws Exception {
        Statement statement = GetConnection().createStatement();
        String query = "SELECT * FROM Books";
        ResultSet result = statement.executeQuery(query);
        Book book = null;
        int i = 1;
        while (result.next() && i<=id) {
            if(i == id)
            {
                book = new Book(result.getString("title"), result.getString("author"), result.getInt("year"));
                break;
            }
            i++;
        }
        return book;
    }
}