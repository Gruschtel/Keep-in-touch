import { FormControl } from "@angular/forms";
export class UsernameValidator {
 
 
  static validUsernameExisting(fc: FormControl) {
    if (fc.value.toLowerCase() === "abcabc") {
      return { validUsernameExisting: true };
    } else {
      return null;
    }
  }

  
}
