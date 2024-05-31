package com.esempio.demo.pages.controller;

import com.esempio.demo.pages.entity.User;
import com.esempio.demo.pages.service.UserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FormController {

    public static String stringToSHA2(String input) {
        try {
            //ottieni un'istanza di MessageDigest per l'algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Calcola l'hash della stringa di input
            byte[] hashBytes = digest.digest(input.getBytes());

            //Converte l'array di byte dell'hash in una rappresentazione esadecimale
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++) {
                byte hashByte = hashBytes[i];
                //Calcoliamo la rappresentazione esadecimale hex di hashByte come nel codice originale
                String hex = Integer.toHexString(0xff & hashByte);
                /*
                Se la lunghezza della stringa hex è 1 (cioè rappresenta un solo carattere 
                esadecimale), aggiungiamo uno zero all'inizio per assicurarci che ciascun 
                byte sia rappresentato da due caratteri esadecimali
                 */
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    private final UserService userService;

    @Autowired
    public FormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/save")
    public RedirectView saveUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User(id, name, email, stringToSHA2(password));
        userService.saveUser(user);
        return new RedirectView("/success"); // Redirect to a success page
    }

    @GetMapping("/success")
    public String success() {
        return "success"; // This should match the name of the Thymeleaf template (success.html)
    }
}
