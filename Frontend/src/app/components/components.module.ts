import { NgModule } from "@angular/core";
import { MessagesComponent } from "./messages/messages.component";
import { FollowerComponent } from "./follower/follower.component";

@NgModule({
  declarations: [MessagesComponent, FollowerComponent],
  exports: [MessagesComponent, FollowerComponent],
})
export class ComponentsModule {}
