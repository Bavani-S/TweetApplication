import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm: any;
  userStatus: Boolean = true;
  userPassword: Boolean = false;
  status: Boolean = false;
  userName: String = '';

  constructor(private loginService: LoginService, private _fb: FormBuilder, private activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.forgotPasswordForm = this._fb.group({
      userName: ['', Validators.required],
      dob:[''],
      password: ['']
    })
  }

  userNameValidation() {
    this.loginService.getUser(this.forgotPasswordForm.controls.userName.value).subscribe((response: any) => {
      this.userStatus = response.status;
      this.userPassword = response.status;
      if(this.userPassword) {
        this.userName = this.forgotPasswordForm.controls.userName.value;
      this.forgotPasswordForm.get('userName').disable();
      this.forgotPasswordForm.controls.password.setValidators([Validators.required]);
      this.forgotPasswordForm.controls.password.updateValueAndValidity();
      }
    },
    (error) => {
    })
  }

  submit() {
    
    this.loginService.forgotPassword(this.userName, this.forgotPasswordForm.get('password').value, this.forgotPasswordForm.get('dob').value).subscribe((res) => {
      this.closeModal();
    })
  }

  closeModal() {
    this.status = true;
    setTimeout(() => {
      this.activeModal.close();
    }, 2000);
  }

  closeModalButton() {
    this.activeModal.close();
  } 
}
