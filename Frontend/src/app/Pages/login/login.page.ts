import { Component, OnInit } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Platform, ToastController } from "@ionic/angular";
import { ApiService } from "src/app/Provider/HTTP/api.service";
import {
  AccountService,
  LOGIN_KEY,
} from "src/app/Provider/Account/account.service";
import { Router } from "@angular/router";
import { User } from "src/app/Models/user";
import { Storage } from "@ionic/storage";
import { JwtLogin } from "src/app/Models/JwtLogin";
import { Token } from "src/app/Models/Token";

@Component({
  selector: "app-login",
  templateUrl: "./login.page.html",
  styleUrls: ["./login.page.scss"],
})
export class LoginPage implements OnInit {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  passwordType: string = "password";
  passwordIcon: string = "eye";
  userfield: string;
  passwdfield: string;

  public user: User;

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    private plt: Platform,
    private storage: Storage,
    public toastCtrl: ToastController,
    public apiService: ApiService,
    private accountservice: AccountService,
    private router: Router
  ) {
    this.plt.backButton.subscribeWithPriority(1, () => {
      console.log("exit");
      navigator["app"].exitApp();
    });

    this.storage.get(LOGIN_KEY).then((val) => {
      if (val) {
        const jwtLogin: JwtLogin = val;

        this.passwdfield = jwtLogin.password;
        this.userfield = jwtLogin.username;

        this.login();
        //
      } else {
        this.user = null;
        console.log("User konnte nicht geladen");
      }
    });
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  ngOnInit() {}

  // =====================================================
  // Method
  // =====================================================

  hideShowPassword() {
    this.passwordType = this.passwordType === "text" ? "password" : "text";
    this.passwordIcon = this.passwordIcon === "eye" ? "eye-off" : "eye";
  }

  login() {
    if (this.userfield == "") {
      this.errorNoUsername();
      return;
    }

    if (this.passwdfield == "") {
      this.errorNoPassword();
      return;
    }

    const jwtLogin: JwtLogin = new JwtLogin(this.userfield, this.passwdfield);
    //console.log(jwtLogin);

    this.apiService.login(jwtLogin).subscribe(
      (response) => {
        if (response) {
          const data = response as Token;
          console.log(data.token);
          this.accountservice.storeToken(data.token);
          this.accountservice.storeLogin(jwtLogin);

          
          this.apiService.loadUserDatat(jwtLogin, data.token).subscribe(
            userdata => {
              const tempUser = userdata as User;
              //console.log(tempUser);
              this.accountservice.storeUserdata(tempUser)
              this.router.navigateByUrl("/home");
              console.log("Login succsed");
            });
            
        }
      },
      (error) => {
        console.log("Error Login: ", error);
        if (error == 0) {
          this.errorNoConnection();
        } else if (error == 401) {
          this.errorAuthenticate();
        } else {
          this.errorlogin();
        }
      }
    );
  }

  // =====================================================
  // Errors
  // =====================================================

  async errorNoUsername() {
    let toast = await this.toastCtrl.create({
      message: "Login failed - No Username",
      duration: 5000,
    });
    toast.present();
  }

  async errorlogin() {
    let toast = await this.toastCtrl.create({
      message: "Login failed - Check your entries",
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

  async errorNoPassword() {
    let toast = await this.toastCtrl.create({
      message: "Login failed - No Password",
      duration: 5000,
    });
    toast.present();
  }
}
