import { Component, OnInit } from "@angular/core";
import { HttpHeaders, HttpClient, HttpParams } from "@angular/common/http";
// Storage
import { Storage } from "@ionic/storage";
import { ToastController } from "@ionic/angular";
// Platform
// Forms
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from "@angular/forms";
// User
import { User } from "src/app/Models/user";
import { PasswordValidator } from "src/app/Provider/Validation/password.validator";
import { BirthValidator } from "src/app/Provider/Validation/birth.validator";
import { Platform } from "@ionic/angular";
import { ApiService } from "src/app/Provider/HTTP/api.service";
import { Token } from "src/app/Models/Token";
import { AccountService } from "src/app/Provider/Account/account.service";
import { Router } from "@angular/router";
import { JwtLogin } from "src/app/Models/JwtLogin";
import { formatDate } from '@angular/common';

// Validation

@Component({
  selector: "app-registration",
  templateUrl: "./registration.page.html",
  styleUrls: ["./registration.page.scss"],
})
export class RegistrationPage implements OnInit {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  // Show Passwort
  passwordType: string = "password";
  passwordIcon: string = "eye";

  // Form
  ionicForm: FormGroup;

  defaultDate = "1990-01-01";
  isSubmitted = false;

  user: User = new User("", "", "", new Date(), "", "", "", "");

  //
  isMailAvaible: Boolean = false;
  isUsernameAvaible: Boolean = false;
  isSignUp: Boolean = false;

  // radio-group
  defaultSelectedRadio = "none";
  //Get value on ionChange on IonRadioGroup
  selectedRadioGroup: any;
  //Get value on ionSelect on IonRadio item
  selectedRadioItem: any;

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    private platform: Platform,
    private formBuilder: FormBuilder,
    public toastCtrl: ToastController,
    public apiService: ApiService,
    private accountservice: AccountService,
    private router: Router
  ) {
    this.platform.backButton.subscribeWithPriority(1, () => {
      this.router.navigateByUrl("/");
    });
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  ngOnInit() {
    this.ionicForm = this.formBuilder.group({
      username: new FormControl(
        "",
        Validators.compose([
          Validators.maxLength(25),
          Validators.minLength(3),
          Validators.required,
        ])
      ),
      password: [
        "",
        Validators.compose([
          PasswordValidator.validPassword,
          Validators.minLength(5),
          Validators.required,
        ]),
      ],
      email: [
        "",
        Validators.compose([
          Validators.required,
          Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}$"),
        ]),
      ],
      dob: [
        this.defaultDate,
        Validators.compose([Validators.required, BirthValidator.validBirthday]),
      ],
    });
  }

  // =====================================================
  // Method
  // =====================================================

  hideShowPassword() {
    this.passwordType = this.passwordType === "text" ? "password" : "text";
    this.passwordIcon = this.passwordIcon === "eye" ? "eye-off" : "eye";
  }

  getDate(e) {
    console.log(e.target.value);
    let date = Date.parse(e.target.value);  
    let jstoday = formatDate(date, 'MM-dd-yyyy', 'en-EN');
    console.log(jstoday);
    //console.log(date);
    this.ionicForm.get("dob").setValue(jstoday, {
      onlyself: true,
    });
  }

  get errorControl() {
    return this.ionicForm.controls;
  }

  async errorToast() {
    let toast = await this.toastCtrl.create({
      message: "Sign up failed - No Internet connection",
      duration: 5000,
    });
    toast.present();
  }

  submitForm() {
    this.isUsernameAvaible = false;
    this.isMailAvaible = false;

    this.isSubmitted = true;
    this.checkEmail();
    this.checkUsername();
  }

  checkUsername() {
    if (this.ionicForm.controls.username.valid) {
      this.apiService.checkUsername(this.ionicForm.value.username).subscribe(
        (response) => {
          //this.user = response as User;
          console.log(response);

          if (!response) {
            this.ionicForm.controls["username"].setErrors({
              validUsernameExisting: true,
            });
          } else {
            //
            if (!this.ionicForm.valid) {
              console.log("Please provide all the required values!");
              console.log(this.ionicForm.controls);
              return;
            } else {
              this.isUsernameAvaible = true;
              this.startRegistration();
            }
          }
        },
        (error) => {
          console.log("ERROR USERNAME");
        }
      );
    }
  }

  checkEmail() {
    if (this.ionicForm.controls.email.valid) {
      this.apiService.checkEmail(this.ionicForm.value.email).subscribe(
        (response) => {
          //this.user = response as User;
          console.log(response);

          if (!response) {
            this.ionicForm.controls["email"].setErrors({
              validEmailExisting: true,
            });
          } else {
            //
            if (!this.ionicForm.valid) {
              console.log("Please provide all the required values!");
              console.log(this.ionicForm.controls);
              return;
            } else {
              this.isMailAvaible = true;
              this.startRegistration();
            }
          }
        },
        (error) => {
          console.log("ERROR MAIL");
        }
      );
    }
  }

  startRegistration() {
    if (!this.isMailAvaible || !this.isMailAvaible || this.isSignUp) return;

    this.isSignUp = true;

    this.user.username = this.ionicForm.value.username;
    this.user.password = this.ionicForm.value.password;
    this.user.email = this.ionicForm.value.email;
    this.user.dob = new Date(Date.parse(this.ionicForm.value.dob));
    this.user.role = "User";

    if (this.selectedRadioItem == "none" || this.selectedRadioItem == null)
      this.user.gender = "None";
    else if (this.selectedRadioItem == "female") this.user.gender = "Female";
    else this.user.gender = "Male";
    this.user.accountStatus = "Activated";

    this.apiService.signUpUser(this.user).subscribe(
      (response) => {
        const data = response as Token;
        console.log(data.token);
        this.isSignUp = false;
        this.isSubmitted = false;

        const jwtLogin: JwtLogin = new JwtLogin(
          this.user.username,
          this.user.password
        );
        this.accountservice.storeUserdata(this.user);
        this.accountservice.storeToken(data.token);
        this.accountservice.storeLogin(jwtLogin);
        this.router.navigateByUrl("/home");
      },
      (error) => {
        console.log("ERROR SIGN UP");
        this.isSignUp = false;
        this.isSubmitted = false;
        this.user = new User("", "", "", new Date(), "", "", "", "");
      }
    );
  }

  radioGroupChange(event) {
    console.log("radioGroupChange", event.detail);
    this.selectedRadioGroup = event.detail;
  }

  radioSelect(event) {
    console.log("radioSelect", event.detail);
    this.selectedRadioItem = event.detail.value;
  }

  // =====================================================
  // Interface
  // =====================================================
}
