import { Component, OnInit } from "@angular/core";
import { Platform, ToastController, NavController } from "@ionic/angular";
import { AccountService } from "src/app/Provider/Account/account.service";
import {
  FormBuilder,
  FormGroup,
  FormControl,
  Validators,
} from "@angular/forms";
import { User } from "src/app/Models/user";
import { ApiService } from "src/app/Provider/HTTP/api.service";
import { NavigationCancel, Router } from "@angular/router";
import { PasswordValidator } from "src/app/Provider/Validation/password.validator";
import { BirthValidator } from "src/app/Provider/Validation/birth.validator";
import { Token } from "src/app/Models/Token";
import { JwtLogin } from "src/app/Models/JwtLogin";
import { formatDate } from '@angular/common';

@Component({
  selector: "app-tab3",
  templateUrl: "./tab3.page.html",
  styleUrls: ["./tab3.page.scss"],
})
export class Tab3Page implements OnInit {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  // Show Passwort
  passwordType: string = "password";
  passwordIcon: string = "eye";

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

  // Form
  ionicFormUpdate: FormGroup;

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    private platform: Platform,
    private formBuilder: FormBuilder,
    private toastCtrl: ToastController,
    private apiService: ApiService,
    private accountservice: AccountService,
    private navCtrl: NavController,
    private router: Router
  ) {}

  // =====================================================
  // Super/Default Methods
  // =====================================================

  ionViewWillEnter() {
    this.loadAndSetUserDetails();
  }

  ngOnInit() {
    this.ionicFormUpdate = this.formBuilder.group({
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

    this.ionicFormUpdate.controls.username.setValue("");
    this.ionicFormUpdate.controls.password.setValue("");
    this.ionicFormUpdate.controls.email.setValue("");
    this.ionicFormUpdate.controls.dob.setValue("");
  }

  // =====================================================
  // Method
  // =====================================================

  async loadAndSetUserDetails() {
    await this.accountservice.loadUserdata();

    this.defaultSelectedRadio = this.accountservice.user.gender;

    console.log(this.accountservice.user.username);

    this.ionicFormUpdate.controls.username.setValue(
      this.accountservice.user.username
    );

    this.ionicFormUpdate.controls.password.setValue(
      this.accountservice.user.password
    );

    this.ionicFormUpdate.controls.password.setValue(
      this.accountservice.user.password
    );
    this.ionicFormUpdate.controls.email.setValue(
      this.accountservice.user.email
    );
    this.ionicFormUpdate.controls.dob.setValue(
      new Date(this.accountservice.user.dob)
    );
  }

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
    this.ionicFormUpdate.get("dob").setValue(jstoday, {
      onlyself: true,
    });
  }

  get errorControl() {
    return this.ionicFormUpdate.controls;
  }

  async errorToast() {
    let toast = await this.toastCtrl.create({
      message: "Sign up failed - No Internet connection",
      duration: 5000,
    });
    toast.present();
  }

  submitForm() {
    console.log("Update");

    this.isUsernameAvaible = false;
    this.isMailAvaible = false;

    this.isSubmitted = true;
    this.checkEmail();
    this.checkUsername();
  }

  checkUsername() {
    if (
      this.ionicFormUpdate.value.username == this.accountservice.user.username
    ) {
      if (!this.ionicFormUpdate.valid) {
        return;
      }
      console.log("checkUsername");
      this.isUsernameAvaible = true;
      this.startRegistration();
    } else {
      if (this.ionicFormUpdate.controls.username.valid) {
        this.apiService
          .checkUsername(this.ionicFormUpdate.value.username)
          .subscribe(
            (response) => {
              //this.user = response as User;
              console.log(response);

              if (!response) {
                this.ionicFormUpdate.controls["username"].setErrors({
                  validUsernameExisting: true,
                });
              } else {
                //
                if (!this.ionicFormUpdate.valid) {
                  console.log("Please provide all the required values!");
                  console.log(this.ionicFormUpdate.controls);
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
  }

  checkEmail() {
    if (this.ionicFormUpdate.value.email == this.accountservice.user.email) {
      if (!this.ionicFormUpdate.valid) {
        return;
      }
      console.log("checkEmail");
      this.isMailAvaible = true;
      this.startRegistration();
    } else {
      if (this.ionicFormUpdate.controls.email.valid) {
        this.apiService.checkEmail(this.ionicFormUpdate.value.email).subscribe(
          (response) => {
            //this.user = response as User;
            console.log(response);

            if (!response) {
              this.ionicFormUpdate.controls["email"].setErrors({
                validEmailExisting: true,
              });
            } else {
              //
              if (!this.ionicFormUpdate.valid) {
                console.log("Please provide all the required values!");
                console.log(this.ionicFormUpdate.controls);
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
  }

  startRegistration() {
    if (!this.isMailAvaible || !this.isMailAvaible || this.isSignUp) return;

    this.isSignUp = true;

    this.user.id = this.accountservice.user.id;
    this.user.username = this.ionicFormUpdate.value.username;
    this.user.password = this.ionicFormUpdate.value.password;
    this.user.email = this.ionicFormUpdate.value.email;
    this.user.dob = new Date(Date.parse(this.ionicFormUpdate.value.dob));

    this.user.gender = this.accountservice.user.gender;
    this.user.accountStatus = this.accountservice.user.accountStatus;
    this.user.role = this.accountservice.user.role;





    console.log(this.user);

    this.apiService
      .updateUser(this.user, this.accountservice.usertoken)
      .subscribe(
        (response) => {
          const data = response as Token;
          console.log(data.token);
          this.isSignUp = false;
          this.isSubmitted = false;

          const jwtLogin: JwtLogin = new JwtLogin(
            this.user.username,
            this.user.password
          );

          this.apiService
            .loadUserDatat(jwtLogin, data.token)
            .subscribe((userdata) => {
              const tempUser = userdata as User;
              //console.log(tempUser);
              this.accountservice.storeUserdata(tempUser);
              this.accountservice.storeToken(data.token);
              this.accountservice.storeLogin(jwtLogin);

              this.navCtrl.navigateRoot("/home/tab3");
              console.log("Login succsed");
            });
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

  logout() {
    this.accountservice.logout();
  }
}
