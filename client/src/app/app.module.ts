import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NbThemeModule, NbLayoutModule } from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { HttpClientModule } from '@angular/common/http';
import { NbAuthJWTToken, NbAuthModule, NbPasswordAuthStrategy } from '@nebular/auth';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    NbThemeModule.forRoot({ name: 'default' }),
    NbLayoutModule,
    NbEvaIconsModule,
    HttpClientModule,
    NbAuthModule.forRoot({
      strategies: [
        NbPasswordAuthStrategy.setup({
          name: 'email',
          baseEndpoint: 'http://localhost:8080/api/auth',
          login: {
            endpoint: '/sign-in',
            method: 'post',
            redirect: {
              success: '/home',
              failure: null,
            },
          },
          register: {
            endpoint: '/sign-up',
            method: 'post',
            redirect: {
              success: '/auth/login',
              failure: null,
            },
            requireValidToken: false,
          },
          token: {
            class: NbAuthJWTToken,
            key: 'token'
          }
        }),
      ],
      forms: {
        login: {
          rememberMe: false,
        },
        register: {
          terms: false,
        },
      },
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
