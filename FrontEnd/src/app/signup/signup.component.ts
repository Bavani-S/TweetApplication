import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  signupForm: any;
  passwordError: Boolean = false;
  userNameError: Boolean = false;
  emailIdError: Boolean = false;
  submitted: Boolean = false;

  constructor(private loginService: LoginService, private _fb: FormBuilder, private _router: Router) { }

  ngOnInit(): void {
    this.signupForm = this._fb.group({
      userName: ['', Validators.required],
      emailId: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      lastName: ['', Validators.required],
      firstName: ['', Validators.required],
      dob: ['',Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{0,12}$')]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    })
  }

  submit() {
    this.submitted = true;
    this.passwordError = this.passwordCheck;
    this.emailIdError = false;
    this.userNameError = false;
    if (this.signupForm.valid && !this.passwordError) {
      this.confirmPasswordSwitch(false);
      this.loginService.register(this.signupForm.value).subscribe((response: any) => {
        this.confirmPasswordSwitch(true);
        if(response.errorMessage && response.errorMessage.errorCode === 101) {
          this.emailIdError = true;
        } else if(response.errorMessage && response.errorMessage.errorCode === 102) {
          this.userNameError = true;
        } else if (response.errorMessage && response.errorMessage.errorCode === 103) {
          this.emailIdError = true;
          this.userNameError = true;
        } else {
          this._router.navigate(['/login']);
        }
      }, (error) => {
        this.confirmPasswordSwitch(true);
      })
    }
  }

  get passwordCheck() {
    if (this.signupForm.get('password').value === this.signupForm.get('confirmPassword').value) {
      return false;
    }
    return true;
  }

  confirmPasswordSwitch(status: any) {
    if (status == true) {
      this.signupForm.controls.confirmPassword.enable();
      this.signupForm.controls.confirmPassword.updateValueAndValidity();
    } else {
      this.signupForm.controls.confirmPassword.disable();
      this.signupForm.controls.confirmPassword.updateValueAndValidity();
    }
  }

}
