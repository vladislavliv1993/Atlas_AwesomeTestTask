package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import io.cucumber.java.en.When;
import stash.StashesContainer;

public class StringUtils {

    @When("^Сгенерируем случайные строки для полей jsonBody в POST запрос и сохраним их в стеш$")
    public void generateAndSaveStringsForJson() {
        String name = getRandomString();
        String job = getRandomString();

        StashesContainer.put("NAME_FIELD", name);
        StashesContainer.put("JOB_FIELD", job);
    }

    @When("^Вставим сгенерированные ранее строки из стеша в поля jsonBody$")
    public void prepareJsonBody() throws FileNotFoundException {
        String name = (String) StashesContainer.get("NAME_FIELD");
        String job = (String) StashesContainer.get("JOB_FIELD");

        File json = new File("src/main/resources/postReqBody");
        Scanner scanner = new Scanner(json);
        String line = "";
        List<String> jsonBody = new ArrayList<>();
        int lineNumber = 1;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.contains("{$string1}")) {
                line = String.format("\"name\": \"%s\",", name);
            } else if (line.contains("{$string2}")) {
                line = String.format("\"job\": \"%s\"", job);
            }
            jsonBody.add(line);
            lineNumber++;
        }
        StashesContainer.put("JSON_BODY", jsonBody);
    }

    public static String getRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder creator = new StringBuilder();
        Random rnd = new Random();

        while (creator.length() < 7) {
            int index = (int) (rnd.nextFloat() * chars.length());
            creator.append(chars.charAt(index));
        }
        return creator.toString();
    }
}
