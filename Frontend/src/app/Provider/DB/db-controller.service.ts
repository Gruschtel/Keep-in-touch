import { Injectable } from "@angular/core";
import { Platform } from "@ionic/angular";

@Injectable({
  providedIn: "root"
})
export class DbControllerService {
  constructor(private storage: Storage, private plt: Platform) {}
}
