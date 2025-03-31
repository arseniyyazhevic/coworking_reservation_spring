package validation;

import exception.ValidationException;

public abstract class Validator {

    public String validateName(String name) throws ValidationException {
        if (name.length() >= 2) {
            return name;
        } else {
            throw new ValidationException("");
        }

    }

    public Long validateId(Long id) throws ValidationException {
        if (id != null) {
            return id;
        } else {
            throw new ValidationException("");
        }
    }
}
