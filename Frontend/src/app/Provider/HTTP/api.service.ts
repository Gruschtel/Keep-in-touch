import { Injectable } from "@angular/core";

import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { retry, catchError } from "rxjs/operators";
import { Token } from "src/app/Models/Token";
import { User } from "src/app/Models/user";
import { JwtLogin } from "src/app/Models/JwtLogin";

//
//
//

const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
  }),
};

// URLs
export const URL_PATH = {
  basic_path: "http://127.0.0.1:8082/",
  basic_path2: "http://172.17.35.65:8082/",
  usernameAvaible: "/isUsernameAvailable",
  emailAvaible: "/isEmailAvailable",
  signup: "/registration",
  loaddata: "/user/getUserData",
  // follower
  loadFollowerData: "/follower/getFollowerData",
  addFollower: "/follower/addFollower",
  findAllFriends: "/follower/findAllFollower",
  findAllFriendsData: "/follower/findAllFollowerDataByID",
  findFriends: "/follower/findFollower",
  deleteFriend: "/follower/deleteFollowerById",
  // message
  loadMessages: "/message/loadMessages",
  addMessage: "/message/add",
  // account update
  updateUser: "/user/updateUser",
  login: "/login",
};

// URL values
export const URL_VALUE = {
  username: "username=",
  password: "password=",
  name: "name=",
  firstname: "firstname=",
  email: "email=",
  dob: "dob=",
};

export const JWT_TOKEN_AUTHORIZATION_VALUE = "TOUCH ";
export const JWT_TOKEN_AUTHORIZATION_KEY = "Authorization";
export const API_VERSION = "v1";

//
//
//

@Injectable({
  providedIn: "root",
})

//
//
//
export class ApiService {
  requestOptions;

  // Constructor
  constructor(private http: HttpClient) {}

  // Handle API errors
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error("An error occurred:", error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // return an observable with a user-facing error message
    return throwError(error.status);
  }

  //
  //
  login(jwtLogin: JwtLogin): Observable<Token> {
    return this.http
      .post<Token>(
        URL_PATH.basic_path + API_VERSION + URL_PATH.login,
        JSON.stringify(jwtLogin),
        httpOptions
      )
      .pipe(
        // retry(2),
        catchError(this.handleError)
      );
  }

  //
  //
  checkUsername(username): Observable<Boolean> {
    return this.http
      .get<Boolean>(
        URL_PATH.basic_path +
          API_VERSION +
          URL_PATH.usernameAvaible +
          "?" +
          URL_VALUE.username +
          username
      )
      .pipe(
        // retry(2),
        catchError(this.handleError)
      );
  }

  //
  //
  checkEmail(email): Observable<Boolean> {
    return this.http
      .get<Boolean>(
        URL_PATH.basic_path +
          API_VERSION +
          URL_PATH.emailAvaible +
          "?" +
          URL_VALUE.email +
          email
      )
      .pipe(
        // retry(2),
        catchError(this.handleError)
      );
  }

  //
  // Create a new item
  signUpUser(item): Observable<Token> {
    //console.log(JSON.stringify(item));
    return this.http
      .post<Token>(
        URL_PATH.basic_path + API_VERSION + URL_PATH.signup,
        JSON.stringify(item),
        httpOptions
      )
      .pipe(
        // retry(2),
        catchError(this.handleError)
      );
  }

  //
  // Create a new item
  updateUser(item: User, token): Observable<Token> {
    const httpOptions_ = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    //console.log(JSON.stringify(item));
    return this.http.post<Token>(
      URL_PATH.basic_path + API_VERSION + URL_PATH.updateUser,
      JSON.stringify(item),
      httpOptions_
    );
  }

  //
  //
  loadUserDatat(user: JwtLogin, token: String): Observable<User> {
    const httpOptions_userData = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http
      .post<User>(
        URL_PATH.basic_path + API_VERSION + URL_PATH.loaddata,
        JSON.stringify(user),
        httpOptions_userData
      )
      .pipe(
        // retry(2),
        catchError(this.handleError)
      );
  }

  //
  //
  loadFriendData(friend_name: String, token: Token): Observable<User> {
    const httpOptions_friendData = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http
      .post<User>(
        URL_PATH.basic_path + API_VERSION + URL_PATH.loadFollowerData,
        JSON.stringify(friend_name),
        httpOptions_friendData
      )
      .pipe
      // retry(2),
      //catchError(this.handleError)
      ();
  }

  //
  //
  //
  loadAllFriendData(account_id, token: Token): Observable<String> {
    const httpOptions_friendDataAll = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http.post<String>(
      URL_PATH.basic_path + API_VERSION + URL_PATH.findAllFriendsData,
      account_id,
      httpOptions_friendDataAll
    );
  }

  //
  // Create a new item
  addFollower(friend: User, token: Token): Observable<String> {
    console.log(friend);

    const httpOption = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http.post<String>(
      URL_PATH.basic_path + API_VERSION + URL_PATH.addFollower,
      JSON.stringify(friend),
      httpOption
    );
  }

  //
  //
  //
  deleteFollower(friend_id, token: Token): Observable<String> {
    const httpOptions_deleteFriend = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http.post<String>(
      URL_PATH.basic_path + API_VERSION + URL_PATH.deleteFriend,
      friend_id,
      httpOptions_deleteFriend
    );
  }

  //
  //
  //
  loadMessages(token: Token): Observable<String> {
    const httpOptions_ = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http.post<String>(
      URL_PATH.basic_path + API_VERSION + URL_PATH.loadMessages,
      "",
      httpOptions_
    );
  }

  //
  //
  //
  addMessages(token: Token, text: String): Observable<String> {
    const httpOptions_ = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "TOUCH " + token,
      }),
    };

    return this.http.post<String>(
      URL_PATH.basic_path + API_VERSION + URL_PATH.addMessage,
      text,
      httpOptions_
    );
  }
}
