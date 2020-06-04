import { NgModule } from "@angular/core";
import { PreloadAllModules, RouterModule, Routes } from "@angular/router";

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  {
    path: "home",
    loadChildren: () =>
      import("./Pages/dashbord/home/home.module").then((m) => m.HomePageModule),
  },

  {
    path: "login",
    loadChildren: () =>
      import("./Pages/login/login.module").then((m) => m.LoginPageModule),
  },
  {
    path: "registration",
    loadChildren: () =>
      import("./Pages/registration/registration.module").then(
        (m) => m.RegistrationPageModule
      ),
  },  {
    path: 'addfriends',
    loadChildren: () => import('./Pages/addfriends/addfriends.module').then( m => m.AddfriendsPageModule)
  },
  {
    path: 'add-messages',
    loadChildren: () => import('./Pages/add-messages/add-messages.module').then( m => m.AddMessagesPageModule)
  }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
