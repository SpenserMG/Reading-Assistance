package com.example.Reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Math.*;

@RestController
public class ReadingController
{
    // This function/method takes in the user's text and bolds up to 60% of the letters in each word of the string given
    public String boulderReader(String message) {
        // Creates a string array called words that is the user input split at each space
        String[] words = message.split(" ");

        // Creates a string builder that we will push all of the words into after they've had their letters bolded
        StringBuilder result = new StringBuilder();

        // foreach loop that iterates over the words array at each word
        for(String word : words) {

            // Create a variable that is used to rebuild each word after it's been bolded
            String modifiedWord = "";

            // Variable that is used to iterate up to 60% of each word
            Integer wordNumber = (int)Math.floor(word.length() * .6);

            // For loop that iterates over each word by wordNumber number of times
            for(int i = 0; i < wordNumber; i++) {

                // The modifiedWord variable is taking in each character with HTML bold tags at position i
                modifiedWord += "<b>" + word.charAt(i) + "</b>";
            }

            // Adds in the unbolded portion of each word onto the end of the bolded portion to make a complete word
            modifiedWord += word.substring(wordNumber);

            // .append is used in conjunction with the Stringbuilder method, and pushes each word into the result variable with a space because .split removed it at the beginning
            result.append(modifiedWord + " ");
        }

        // Converts result into a String because that is what our function is returning and how the Javascript fetch recognizes the HTML bold tags
        return result.toString();
    }

    // CrossOrigin origins = * takes care of the CORS error about headers, * means all
    @CrossOrigin(origins="*")
    @GetMapping("/hello")
    // Creates a method that calls the reading response file
    public ReadingResponse Hello(@RequestParam String message) {

        // Instantiates ReadingResponse as a new variable readingResponse that is going to accept the affected user input given by the boulderReader method
        ReadingResponse readingResponse = new ReadingResponse(boulderReader(message));

        // This is what the fetch request is fetching as json
        return readingResponse;
    }

    // The original method that was being used as an example in order to get the fetch request working before figuring out bolding text
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