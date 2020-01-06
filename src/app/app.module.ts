import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
// import { ToastrModule } from "ngx-toastr";
// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { SidebarModule } from './sidebar/sidebar.module';
import { HeaderModule } from './shared/header/header.module';
import { FooterModule } from './shared/footer/footer.module';
import { AppComponent } from './app.component';
import { routes } from './app-routing.module';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes, {
      useHash: false
    }),
    SidebarModule,
    HeaderModule,
    // ToastrModule.forRoot(),
    FooterModule,
    // FontAwesomeModule
    HttpClientModule,
    NgxSpinnerModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
