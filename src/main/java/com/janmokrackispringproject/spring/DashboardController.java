package com.janmokrackispringproject.spring;

import com.janmokrackispringproject.requests.AddBookRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    private DashboardService dashboardService;
    private UserService userService;

    public DashboardController(DashboardService dashboardService, UserService userService) {
        this.dashboardService = dashboardService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getBooks()
    {
        return dashboardService.GetBooks();
    }

    @GetMapping("/dashboard/{id}")
    public String getBook(@PathVariable int id)
    {
        return dashboardService.GetBook(id);
    }

    @PostMapping("/dashboard")
    public String addBook(@RequestBody AddBookRequest addBookRequest)
    {
        return dashboardService.AddBook(addBookRequest);
    }

    @DeleteMapping("/dashboard/{id}")
    public String removeBook(@PathVariable int id)
    {
        return dashboardService.RemoveBook(id);
    }
}
