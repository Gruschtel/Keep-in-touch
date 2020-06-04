import { NgModule, ErrorHandler } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { RouteReuseStrategy } from "@angular/router";

import { IonicModule, IonicRouteStrategy } from "@ionic/angular";
import { SplashScreen } from "@ionic-native/splash-screen/ngx";
import { StatusBar } from "@ionic-native/status-bar/ngx";

import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";

// Provider

// HTTP
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";

// Storage
import { IonicStorageModule } from "@ionic/storage";
import { AccountService } from './Provider/Account/account.service';

// Services


@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(),
    AppRoutingModule,
    IonicStorageModule.forRoot()
  ],
  providers: [
    StatusBar,
    SplashScreen,
    IonicStorageModule,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    AccountService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
