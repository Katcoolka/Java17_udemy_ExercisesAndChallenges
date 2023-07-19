package dev.lpa;

import java.util.Arrays;

public record PersonRecord(String name, String dob, PersonRecord[] kids) {
    public PersonRecord(String name, String dob) {
        this(name, dob, new PersonRecord[20]); // 0 was changed to 20 in order to have a placeholder for 20 kids
    }

    @Override
    public String toString() {
        String kidString = "n/a";
        if (kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + dob + ", kids = " + kidString;
    }

    @Override
    public PersonRecord[] kids() {
        return kids == null ? null : Arrays.copyOf(kids, kids.length);
    }
}
