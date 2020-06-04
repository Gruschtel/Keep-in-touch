import { Component, OnInit } from '@angular/core';
import { Platform, ToastController, NavController } from '@ionic/angular';
import { ApiService } from 'src/app/Provider/HTTP/api.service';
import { AccountService } from 'src/app/Provider/Account/account.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-messages',
  templateUrl: './add-messages.page.html',
  styleUrls: ['./add-messages.page.scss'],
})
export class AddMessagesPage implements OnInit {


  message_field: string;

  constructor(
    private plt: Platform,
    public toastCtrl: ToastController,
    public apiService: ApiService,
    private accountservice: AccountService,
    private router: Router,
    public navCtrl: NavController
  ) {
    this.accountservice.loadUserdata();

    this.plt.backButton.subscribeWithPriority(1, () => {
      this.navCtrl.navigateRoot("/home");
    });
  }

  ngOnInit() {
  }

  async add() {

    this.apiService
      .addMessages(this.accountservice.usertoken, this.message_field)
      .subscribe(
        (response) => {
          if (response) {
            const data = response;
            console.log(data);
            console.log("Message erfolgreihc angelegt");
            this.navCtrl.navigateRoot("/home");
            //this.router.navigateByUrl("/home");
          }
        },
        (error) => {
    
          const requestError = error as HttpErrorResponse;
          console.log(error.error);

          if (error.status == 200) {
            console.log("Message erfolgreihc angelegt");
            this.navCtrl.navigateRoot("/home");
            //this.router.navigateByUrl("/home");
          } else if (requestError.error == 4) {
            this.errorTost();
          }
        }
      );
  }


  async errorTost() {
    let toast = await this.toastCtrl.create({
      message: "Something went wrong!",
      duration: 5000,
    });
    toast.present();
  }

}
