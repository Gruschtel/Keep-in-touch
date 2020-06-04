import { FormControl } from "@angular/forms";
export class EmailValidator {
 
 
  static validEmailExisting(fc: FormControl) {
    if (fc.value.toLowerCase() === "abcabc") {
      return { validUsernameExisting: true };
    } else {
      return null;
    }
  }

  
}
