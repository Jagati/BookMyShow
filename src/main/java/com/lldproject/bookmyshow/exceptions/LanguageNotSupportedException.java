package com.lldproject.bookmyshow.exceptions;

import com.lldproject.bookmyshow.model.Features;
import com.lldproject.bookmyshow.model.Language;

public class LanguageNotSupportedException extends Exception {
    public LanguageNotSupportedException(String message) {
        super(message);
    }
}
