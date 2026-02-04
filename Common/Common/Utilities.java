package Common;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.json.StaticInitializerCoercer;

import Constant.Constant;
import io.opentelemetry.api.trace.StatusCode;

public class Utilities {

	// Generate random string
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }
    
    public static boolean isDisplayed(String element) {
    	try {
			return Constant.WEBDRIVER.findElement(By.xpath(element)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
    }
}
