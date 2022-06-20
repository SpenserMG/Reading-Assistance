package com.example.Reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadingController
{

    public String upperReader(String message) {
        // stores each characters to a char array
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        int counter = 0;

        for (int i = 0; i < charArray.length; i++) {

            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {

                // check space is present before the letter
                if (foundSpace) {

                    //for (i=0; i<3; i++) {
                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    if(counter < 3) {
                        foundSpace = false;
                    }
                    //}
                }
            }

            else {
                // if the new character is not character
                foundSpace = true;
            }
        }

        // convert the char array to the string
        message = String.valueOf(charArray);
        return "<b>" + message + "</b>";
    }

    @CrossOrigin(origins="*")
    @GetMapping("/hello")
    public ReadingResponse Hello(@RequestParam String message) {
        // create a string
        //message = "everyone loves java";
        ReadingResponse readingResponse = new ReadingResponse(upperReader(message));

        return readingResponse;
    }
}