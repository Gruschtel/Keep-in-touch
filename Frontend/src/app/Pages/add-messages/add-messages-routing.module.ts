import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddMessagesPage } from './add-messages.page';

const routes: Routes = [
  {
    path: '',
    component: AddMessagesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddMessagesPageRoutingModule {}
