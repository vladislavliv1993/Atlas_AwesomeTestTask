package utils;

import java.util.List;
import java.util.stream.Collectors;

import models.pajeobject.Datum;

public class StreamApiUtils {

    public static List<String> getAllFirstNames(List<Datum> notes) {
        return notes.stream()
                .map(Datum::getFirst_name)
                .collect(Collectors.toList());
    }
}
