package io;

import logic.Course;

import java.io.File;
import java.util.Optional;

public interface CourseDataReader {

    Optional<Course> readData(File file);

    static Optional<CourseDataReader> getReader(File dataFile) {
        if (dataFile.getName().endsWith(".csv")) {
            return Optional.of(new CsvDataReader());
        } else if (dataFile.getName().endsWith(".txt")) {
            return Optional.of(new TagValueDataReader(dataFile));
        }
        return Optional.empty();

    }


}