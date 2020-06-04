import { Component, OnInit, OnDestroy } from "@angular/core";
import { Platform, ToastController, NavController } from "@ionic/angular";
import { AccountService } from "src/app/Provider/Account/account.service";
import { ApiService } from "src/app/Provider/HTTP/api.service";
import { Router, NavigationStart, NavigationEnd } from "@angular/router";
import { filter } from "rxjs/operators";
import { Subscription } from "rxjs/internal/Subscription";

@Component({
  selector: "app-tab2",
  templateUrl: "./tab2.page.html",
  styleUrls: ["./tab2.page.scss"],
})
export class Tab2Page implements OnInit {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  messages: any;

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
    //this.refreshMessage();
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  public async ngOnInit() {
    //this.refreshMessage();
  }

  ngAfterViewInit(){
    this.refreshMessage();
  }

  ionViewWillEnter() {
    this.refreshMessage();
  }



  // =====================================================
  // Method
  // =====================================================

  logout() {
    this.accountservice.logout();
  }

  newMessage() {
    this.navCtrl.navigateRoot("/add-messages");
  }

  async refreshMessage() {
    await this.accountservice.loadUserdata();

    await this.apiService.loadMessages(this.accountservice.usertoken).subscribe(
      (response) => {
        if (response) {
          this.messages = response;
          console.log(this.messages);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
