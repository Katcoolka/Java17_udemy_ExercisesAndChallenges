package dev.lpa;

public record Course(String courseCode, String title, int lectureCount) {

    //compact constructor

    public Course {
        if (lectureCount <= 0) {
            lectureCount = 1;
        }
    }

    //custom constructor
    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
}
