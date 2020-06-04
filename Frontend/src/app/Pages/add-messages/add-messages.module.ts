import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddMessagesPageRoutingModule } from './add-messages-routing.module';

import { AddMessagesPage } from './add-messages.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddMessagesPageRoutingModule
  ],
  declarations: [AddMessagesPage]
})
export class AddMessagesPageModule {}
