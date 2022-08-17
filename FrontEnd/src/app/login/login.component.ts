import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: any;
  ngbModalOptions: NgbModalOptions = {
    backdrop: 'static',
    keyboard: true,
    ariaLabelledBy: 'modal_title',
    container: '#login'
  };
  invalidCredentials: Boolean = false;
  logIn: Boolean = false;

  constructor(private _fb: FormBuilder, private loginService: LoginService, private modalService: NgbModal, private _router: Router) { }

  ngOnInit(): void {
    this.loginForm = this._fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  submit() {
    this.logIn = true;
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value).subscribe((response: any) => {
        console.log("error: ",response.errorMessage);
        if (response.errorMessage !== undefined) {
          this.invalidCredentials = true;
        } else {
          sessionStorage.setItem('token', response.token);
          sessionStorage.setItem('userName', response.userName);
          sessionStorage.setItem('emailId', response.emailId);
          this.loginService.setLoggedIn(true);
          this._router.navigate(['/home-page']);
       }
      },
        (error) => {
          console.log(error);
        })
    }
  }

  forgotPassword() {
    this.modalService.open(ForgotPasswordComponent);
  }

}
