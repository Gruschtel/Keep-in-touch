import { Component, OnInit } from "@angular/core";
import { Platform, ToastController, NavController } from "@ionic/angular";
import {
  AccountService,
  USER_KEY,
} from "src/app/Provider/Account/account.service";
import { Router } from "@angular/router";
import { ApiService } from "src/app/Provider/HTTP/api.service";
import { JwtLogin } from "src/app/Models/JwtLogin";
import { User } from "src/app/Models/user";
import { Storage } from "@ionic/storage";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-addfriends",
  templateUrl: "./addfriends.page.html",
  styleUrls: ["./addfriends.page.scss"],
})
export class AddfriendsPage implements OnInit {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  hideFound = false;
  hideNotFound = false;
  friend_name: string;
  such_ergebniss: string;
  newFollower: User;

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    private plt: Platform,
    private storage: Storage,
    public toastCtrl: ToastController,
    public apiService: ApiService,
    private accountservice: AccountService,
    public navCtrl: NavController
  ) {
    this.navCtrl.pop();
    this.accountservice.loadUserdata();
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  ngOnInit() {}

  // =====================================================
  // Method
  // =====================================================

  async search() {
    this.hideFound = false;
    this.hideNotFound = false;

    if (this.friend_name == null) {
      this.errorNotEmpty();
      this.hideNotFound = true;
      return;
    }

    console.log(this.friend_name);

    await this.apiService
      .loadFriendData(this.friend_name, this.accountservice.usertoken)
      .subscribe(
        (response) => {
          if (response) {
            const data = response as User;
            console.log(data);

            this.newFollower = data;
            this.such_ergebniss = data.username;

            this.hideFound = true;
            this.hideNotFound = false;
          }
        },
        (error) => {
          this.hideFound = false;
          this.hideNotFound = true;

          const requestError = error as HttpErrorResponse;
          console.log(error.error);

          if (requestError.error == 1) {
            this.errorAuthenticate();
            this.hideNotFound = true;
          } else if (requestError.error == 2) {
            this.errorCurrentUser();
          } else if (requestError.error == 3) {
            this.errorNoUser();
          } else if (requestError.error == 4) {
            this.errorSchonVorhanden();
          } else if (requestError.status == 401) {
            this.errorNoConnection();
          } else if (requestError.status == 0) {
            this.errorAuthenticate();
          } else {
            this.errorStandard();
          }
        }
      );
  }

  async add() {
    this.hideFound = false;
    this.hideNotFound = false;

    this.apiService
      .addFollower(this.newFollower, this.accountservice.usertoken)
      .subscribe(
        (response) => {
          if (response) {

            this.hideFound = true;
            this.hideNotFound = false;

            const data = response;
            console.log(data);
            this.navCtrl.navigateBack("/home/tab1");
          }
        },
        (error) => {
          this.hideFound = false;
          this.hideNotFound = true;


          const requestError = error as HttpErrorResponse;
          console.log(error.error);

          if (error.status == 200) {
            console.log("Nutzer wird nun gefolgt");
            this.navCtrl.navigateBack("/home/tab1");
          } else if (requestError.error == 4) {
            this.errorSchonVorhanden();
          }
        }
      );
  }

  async errorSchonVorhanden() {
    let toast = await this.toastCtrl.create({
      message: "You already follow this person!",
      duration: 5000,
    });
    toast.present();
  }

  async errorNoUser() {
    let toast = await this.toastCtrl.create({
      message: "User not exist",
      duration: 5000,
    });
    toast.present();
  }

  async errorStandard() {
    let toast = await this.toastCtrl.create({
      message: "Something went wrong!",
      duration: 5000,
    });
    toast.present();
  }

  async errorAuthenticate() {
    let toast = await this.toastCtrl.create({
      message: "Account does not exist",
      duration: 5000,
    });
    toast.present();
  }

  async errorNoConnection() {
    let toast = await this.toastCtrl.create({
      message: "Server is not responding",
      duration: 5000,
    });
    toast.present();
  }

  async errorCurrentUser() {
    let toast = await this.toastCtrl.create({
      message: "You can't follow yourself",
      duration: 5000,
    });
    toast.present();
  }

  async errorNotEmpty() {
    let toast = await this.toastCtrl.create({
      message: "Input must not be empty!",
      duration: 5000,
    });
    toast.present();
  }
}
