import { FormControl } from "@angular/forms";
import { REGEX_PATTERN } from "src/app/Config/Const";
export class PasswordValidator {
  static validPassword(fc: FormControl) {
    var isValid = false;

    if (REGEX_PATTERN.PASSWORD_1.test(fc.value)) {
      if (REGEX_PATTERN.PASSWORD_2.test(fc.value)) {
        if (REGEX_PATTERN.PASSWORD_3.test(fc.value)) {
          if (REGEX_PATTERN.PASSWORD_4.test(fc.value)) {
            return null;
          }
        }
      }
    }
    return ({validPassword: true});
  }
}
