 package vttp.ssf.day12wkshop.controllers;

 import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;

 @Controller
 @RequestMapping(path = "/generate")
 public class GenerateController {

    // GET /generate?name=fred&count=4
    @GetMapping
     public String getNumbers(
        @RequestParam MultiValueMap<String, String> form, Model model){
            String name = form.getFirst("name");
            int count = 0;
            List<String> values = new LinkedList<>();

            count = toInt(form.getFirst("count"), 4);
            values = generateRandom(count).stream()
                    .map(val -> "/numbers/number%d.jpg".formatted(val))
                    .toList();

        model.addAttribute("name", name);
        model.addAttribute("count", count);
        model.addAttribute("values", values);

         return "generate";
     }

     public List<Integer> generateRandom(int count){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 31; i++) {
            numbers.add(i);
            
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, count);
     }

     public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            
            return defValue;
        }
     }
    
 }
