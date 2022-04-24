package com.janmokrackispringproject.spring;

import com.google.gson.Gson;
import com.janmokrackispringproject.beans.Book;
import com.janmokrackispringproject.helpers.BookDbAccess;
import com.janmokrackispringproject.requests.AddBookRequest;
import com.janmokrackispringproject.responses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class DashboardService {

    private BookDbAccess bookDbAccess;
    private Gson gson;

    public DashboardService() {
        this.bookDbAccess = new BookDbAccess();
        this.gson = new Gson();
    }

    public ResponseEntity GetBooks() {
        HttpStatus status = HttpStatus.OK;
        Response response = new OKResponse("[]");
        try{
            response = new OKResponse(bookDbAccess.GetBooks());
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        finally {
            return new ResponseEntity(gson.toJson(response), status);
        }
    }

    public ResponseEntity GetBook(int id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Response response = new NotFoundResponse("There is no such book");
        try{
            Book book = bookDbAccess.GetBook(id);
            if(book != null) {
                status = HttpStatus.OK;
                response = new OKResponse(book);
            }
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        finally {
            return new ResponseEntity(gson.toJson(response), status);
        }
    }

    public ResponseEntity AddBook(AddBookRequest addBookRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Response response = new BadRequestResponse("");
        try{
            status = HttpStatus.OK;
            response = new OKResponse(bookDbAccess.AddBook(addBookRequest));
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new ExceptionResponse(sw.toString());
        }
        finally {
            return new ResponseEntity(gson.toJson(response), status);
        }
    }

    public ResponseEntity RemoveBook(int id) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Response response = new BadRequestResponse("");
        try{
            Book bookToDelete = bookDbAccess.GetBook(id);
            if(bookToDelete == null) {
                status = HttpStatus.NOT_FOUND;
                response = new NotFoundResponse("There is no such book");
            }
            else if(bookDbAccess.RemoveBook(bookToDelete.getTitle())) {
                status = HttpStatus.OK;
                response = new OKResponse("");
            }
        }
        catch(Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            response = new ExceptionResponse(sw.toString());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        finally {
            return new ResponseEntity(gson.toJson(response), status);
        }
    }
}
