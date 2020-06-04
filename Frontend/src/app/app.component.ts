import { Component } from "@angular/core";

import { Platform } from "@ionic/angular";
import { SplashScreen } from "@ionic-native/splash-screen/ngx";
import { StatusBar } from "@ionic-native/status-bar/ngx";

@Component({
  selector: "app-root",
  templateUrl: "app.component.html",
  styleUrls: ["app.component.scss"]
})
export class AppComponent {
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
    private splashScreen: SplashScreen,
    private statusBar: StatusBar
  ) {
    this.initializeApp();
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  // =====================================================
  // Method
  // =====================================================

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleLightContent();
      this.statusBar.backgroundColorByHexString("#1972d2");
      this.splashScreen.hide();
    });
  }

  // =====================================================
  // Interface
  // =====================================================
}
