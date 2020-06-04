import { Injectable } from "@angular/core";
import { Platform, NavController } from "@ionic/angular";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Storage } from "@ionic/storage";

import { Router } from "@angular/router";
import { User } from "src/app/Models/user";
import { Observable, from } from "rxjs";
import { switchMap, map } from "rxjs/operators";
import { JwtLogin } from "src/app/Models/JwtLogin";
import { Token } from "src/app/Models/Token";

//
//
//

export const TOKEN_KEY = "jwt-token";
export const USER_KEY = "user-token";
export const LOGIN_KEY = "login-token";

//
//
//

@Injectable({
  providedIn: "root",
})

//
//
//
export class AccountService {
  public user: User;
  public usertoken: Token;

  constructor(
    private storage: Storage,
    private http: HttpClient,
    private plt: Platform,
    private router: Router,
    private navCtrl: NavController
  ) {
    this.loadUserdata();
  }

  storeToken(token: String): any {
    this.storage.set(TOKEN_KEY, token);
  }

  loadStoredToken(): any {
    this.storage.get(TOKEN_KEY).then((val) => {
      this.usertoken = val as Token;
      return val;
    });
  }

  storeUserdata(user: User) {
    //this.user = user;
    this.storage.set(USER_KEY, user);
  }

  storeLogin(jwtLogin: JwtLogin) {
    this.storage.set(LOGIN_KEY, jwtLogin);
  }

  async loadUserdata() {
    await this.loadStoredToken();

    await this.storage.get(USER_KEY).then((val) => {
      if (val) {
        this.user = val;
        console.log("User erfolgreich geladen");
      } else {
        this.user = null;
        this.router.navigateByUrl("/");
        console.log("User konnte nicht geladen");
      }
    });

    await this.storage.get(LOGIN_KEY).then((val) => {
      this.user.password = val.password;
    });

    console.log(this.user);
  }

  async logout(): Promise<any> {
    await this.storage.remove(TOKEN_KEY).then(() => {
      this.user = null;
      this.storage.remove(USER_KEY).then((val) => {
        this.storage.remove(LOGIN_KEY).then((val) => {
          // User delete
          this.storage.set(LOGIN_KEY, "");
          this.storage.set(USER_KEY, "");
          this.storage.set(TOKEN_KEY, "");
          this.navCtrl.navigateRoot("/");
        });
      });
    });
  }
}
