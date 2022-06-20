package com.example.Reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadingController
{

    public String boulderReader(String message) {
        String[] words = message.split(" ");

        StringBuilder result = new StringBuilder(32);

        for(String word : words) {
            String modifiedWord = "";

            modifiedWord += "<b>" + word.charAt(0) + "</b>";

            modifiedWord += word.substring(1);

            result.append(modifiedWord + " ");
        }

        return result.toString();
    }

    @CrossOrigin(origins="*")
    @GetMapping("/hello")
    public ReadingResponse Hello(@RequestParam String message) {

        ReadingResponse readingResponse = new ReadingResponse(boulderReader(message));

        return readingResponse;
    }

    /* public String upperReader(String message) {
        // stores each characters to a char array
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {

            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {

                // check space is present before the letter
                if (foundSpace) {

                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            }

            else {
                // if the new character is not character
                foundSpace = true;
            }
        }

        // convert the char array to the string
        // Interpolate bold HTML tags will carry through java, the javascript fetch request, and land on the HTML page with the correct format
        message = String.valueOf(charArray);
        return "<b>" + message + "</b>";
    }*/
}