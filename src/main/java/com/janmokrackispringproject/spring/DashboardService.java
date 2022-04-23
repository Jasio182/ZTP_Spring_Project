package com.janmokrackispringproject.spring;

import com.google.gson.Gson;
import com.janmokrackispringproject.beans.Book;
import com.janmokrackispringproject.helpers.dataaccess.BookDbAccess;
import com.janmokrackispringproject.requests.AddBookRequest;
import com.janmokrackispringproject.responses.*;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

@Service
public class DashboardService {

    private BookDbAccess bookDbAccess;
    private Gson gson;

    public DashboardService() {
        this.bookDbAccess = new BookDbAccess();
        this.gson = new Gson();
    }

    public String GetBooks() {
        Response response = new OKResponse("[]");
        try{
            response = new OKResponse(bookDbAccess.GetBooks());
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
        }
        finally {
            return gson.toJson(response);
        }
    }

    public String GetBook(int id) {
        Response response = new OKResponse("[]");
        try{
            Book book = bookDbAccess.GetBook(id);
            if(book != null)
                response = new OKResponse(book);
            else
                response = new NotFoundResponse("There is no such book");
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
        }
        finally {
            return gson.toJson(response);
        }
    }

    public String AddBook(AddBookRequest addBookRequest) {
        Response response = new BadRequestResponse("");
        try{
            response = new OKResponse(bookDbAccess.AddBook(addBookRequest));
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
        }
        finally {
            return gson.toJson(response);
        }
    }

    public String RemoveBook(int id) {
        Response response = new BadRequestResponse("");
        try{
            Book bookToDelete = bookDbAccess.GetBook(id);
            if(bookDbAccess.RemoveBook(bookToDelete.getTitle()))
                response = new OKResponse("");
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
        }
        finally {
            return gson.toJson(response);
        }
    }
}
