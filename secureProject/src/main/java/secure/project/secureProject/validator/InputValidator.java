package secure.project.secureProject.validator;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String specialCharactersPattern = "[%^&*()\\-+={}\\[\\]|;:\"'<>,./]";

    public static boolean lengthNotInRange(String input, int min, int max) {
        int length = 0;
        if(input != null)
            length = input.length();
        return !(length >= min && length <= max);
    }

    public static boolean containsAnySpecialCharacter(String input) {
        return Pattern.compile(specialCharactersPattern).matcher(input).find();
    }
}

