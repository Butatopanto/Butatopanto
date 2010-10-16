package butatopanto.sharedtest

import org.springframework.validation.Errors
import org.springframework.validation.FieldError

class ValidationUtilities {
  public static FieldError getValidationFieldError(def domainObject, String field) {
    domainObject.validate()
    Errors errors = domainObject.errors
    errors?.getFieldError(field)
  }
}
