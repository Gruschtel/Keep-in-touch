import { Component } from "@angular/core";

import { Platform, MenuController } from "@ionic/angular";
import {
  AccountService,
  USER_KEY,
} from "src/app/Provider/Account/account.service";

import { Storage } from "@ionic/storage";
import { Router } from '@angular/router';

@Component({
  selector: "app-home",
  templateUrl: "home.page.html",
  styleUrls: ["home.page.scss"],
})
export class HomePage {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    private platform: Platform,
    private accountService: AccountService,
    private router: Router,
    private storage: Storage
  ) {
    this.platform.backButton.subscribeWithPriority(1, () => {
      console.log("exit");
      navigator["app"].exitApp();
    });

    this.storage.get(USER_KEY).then((val) => {
      console.log(val);
    });
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  ngOnInit() {}

  // =====================================================
  // Method
  // =====================================================

  logout() {
    this.accountService.logout();
  }



  // =====================================================
  // Interface
  // =====================================================
}
