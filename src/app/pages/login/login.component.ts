import { Component, OnInit } from '@angular/core';
import { User } from '../login/user.model';
import { LoginService } from '../login/login.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  data: User;
  isLoggedin = false;

  constructor(
    private loginService: LoginService,
    private router: Router,
    public SpinnerService: NgxSpinnerService
  ) {
    this.data = new User();
  }

  ngOnInit() {
  }

  submitForm() {
    this.isLoggedin = true;
    this.SpinnerService.show();
    this.loginService.login('user/login', this.data).subscribe(
      res => {
        console.log(res);
        this.SpinnerService.hide();
        this.router.navigate(['/dashboard']);
      },
      error => {
        console.log(error);
        alert('Username or password is incorrect');
        this.SpinnerService.hide();
      }
    );
  }

}
