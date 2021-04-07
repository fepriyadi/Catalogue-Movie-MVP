package com.dicoding.course.cataloguemoviempv.util;

/**
 * Creates a Presenter object.
 * @param <T> presenter type
 */
public interface PresenterFactory<T> {
    T create();
}
