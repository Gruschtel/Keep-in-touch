import { Component, OnInit } from "@angular/core";
import { Platform, ToastController, NavController } from "@ionic/angular";
import {
  AccountService,
  USER_KEY,
} from "src/app/Provider/Account/account.service";
import { Router, NavigationStart, NavigationEnd } from "@angular/router";
import { ApiService } from "src/app/Provider/HTTP/api.service";
import { JwtLogin } from "src/app/Models/JwtLogin";
import { User } from "src/app/Models/user";
import { Storage } from "@ionic/storage";
import { HttpErrorResponse } from "@angular/common/http";
import { filter } from "rxjs/operators";
import { Subscription } from "rxjs/internal/Subscription";







@Component({
  selector: "app-tab1",
  templateUrl: "./tab1.page.html",
  styleUrls: ["./tab1.page.scss"],
})

export class Tab1Page implements OnInit {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  friends: any;
  private subscription: Subscription;

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    private platform: Platform,
    public toastCtrl: ToastController,
    public apiService: ApiService,
    private accountservice: AccountService,
    private router: Router,
    public navCtrl: NavController
  ) {
    this.platform.backButton.subscribeWithPriority(1, () => {
      this.router.navigateByUrl("/");
    });
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  public async ngOnInit(): Promise<void> {
    await this.onEnter();

    this.subscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd && event.url === "/tabs/(home:home)") {
        this.onEnter();
      }
    });
  }

  ionViewWillEnter() {
    console.log("HomePage: ionViewWillEnter");
    this.refreshFriends();
  }

  public async onEnter(): Promise<void> {
    this.refreshFriends();
  }

  public ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  // =====================================================
  // Method
  // =====================================================

  async refreshFriends() {
    await this.accountservice.loadUserdata();

    await this.apiService
      .loadAllFriendData(
        this.accountservice.user.id,
        this.accountservice.usertoken
      )
      .subscribe(
        (response) => {
          if (response) {
            this.friends = response;

            //this.junk = JSON.parse(response);

            console.log(this.friends);
          }
        },
        (error) => {
          console.log(error);
        }
      );
  }

  /** This function will get all the (click) event data including the element that was clicked*/
  async delete(event: any) {
    console.log(event);
    await this.apiService
      .deleteFollower(event, this.accountservice.usertoken)
      .subscribe(
        (response) => {
          if (response) {
            console.log(response);
            this.refreshFriends();
          }
        },
        (error) => {
          const requestError = error as HttpErrorResponse;
          console.log(error.error);

          if (error.status == 200) {
            console.log("Nutzer wurde gelöscht");
            this.refreshFriends();
          }
        }
      );
  }

  logout() {
    this.accountservice.logout();
  }

  addFriend() {
    this.navCtrl.navigateRoot("/addfriends");
  }
}
