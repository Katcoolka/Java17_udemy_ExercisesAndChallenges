package dev.lpa.util;

import dev.lpa.model.Student;

import java.util.ArrayList;
import java.util.List;

//any type that uses this class, must be a Student or subtype of the Student class, and it also must implement the QueryItem interface.
public class QueryList<T extends Student & QueryItem> {

    private List<T> items;

    public QueryList(List<T> items) {
        this.items = items;
    }
    // if we want this functionality for any List implementation.
    //this T, this type parameter, is a totally different type, completely separate from the type
    //parameter, declared for the class itself. In fact, this type either gets specified or inferred, when you invoke this static method on the class.
    public static <S extends QueryItem> List<S> getMatches(List<S> items, String field, String value) {
        List<S> matches = new ArrayList<>();
        for (var item : items) {
            if (item.matchfieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }

    public List<T> getMatches(String field, String value) {
        List<T> matches = new ArrayList<>();
        for (var item : items) {
            if (item.matchfieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }
}
