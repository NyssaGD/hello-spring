package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("hello")
public class HelloController {

    //    // Handle request at path /hello
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello, Spring!";
//    }

    // lives at /hello/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    // Handles requests of the form /hello?name=LaunchCode
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam String name, Model model) {
        String greeting = "Hello, " + name+ "!";
        model.addAttribute("greeting",greeting);
        return "hello";
    }

    // Handles requests of the form /hello/LaunchCode
    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name, Model model) {
       String greeting = "hello, " +name+ "!";
        model.addAttribute("greeting",greeting);
        return "hello";
    }

    // /hello/form
    @GetMapping("form")
    public String helloForm() {
        return "form";
    }
    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("JavaScript");
        model.addAttribute("names", names);
        return "hello-list";
    }

    @GetMapping("exercise")
    public String helloExcercise() {
        return "<html>" +
                "<body>" +
                "<form method = 'post' action = '/form'>" +
                "<input type = 'text' name = 'coder' placeholder = 'Enter Name' />" +
                "<select id=language name=language>" +
                "<option value=english>English</option>" +
                "<option value=french>French</option>" +
                "<option value=italian>Italian</option>" +
                "<option value=spanish>Spanish</option>" +
                "<option value=german>German</option>" +
                "</select>" +
                "<input type = 'submit' value = 'Greet Me!' >" +
                "</form>" +
                "</body>" +
                "</html>";

    }
    @RequestMapping(value="hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(@RequestParam String name, @RequestParam String language) {
        if (name == null) {
            name = "World";
        }

        return createMessage(name, language);

        // For a bonus mission, students can change this response text to look nicer.
        // This is subjective, but students should be modifying the HTML of the response string.
    }

    public static String createMessage(String n, String l) {
        String greeting = "";

        if (l.equals("english")) {
            greeting = "Hello";
        }
        else if (l.equals("french")) {
            greeting = "Bonjour";
        }
        else if (l.equals("italian")) {
            greeting = "Bonjourno";
        }
        else if (l.equals("spanish")) {
            greeting = "Hola";
        }
        else if (l.equals("german")) {
            greeting = "Hallo";
        }

        return greeting + " " + n;
    }
}