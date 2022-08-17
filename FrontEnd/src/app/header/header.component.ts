import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { AllUsersComponent } from '../all-users/all-users.component';
import { LoginService } from '../login.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  ngbBackdrop: NgbModalOptions = {
    backdrop: 'static',
    keyboard: true,
    ariaLabelledBy: 'modal_title',
    size: 'lg'
  }

  loggedIn: Boolean = false;
  profilename : any ="";

  constructor(private loginService: LoginService, private _router: Router, private modalService: NgbModal,private userService : UserService) { }

  ngOnInit(): void {
    this.profilename=this.loginService.getProfileName(),
    console.log("profile" ,this.profilename),
    this.loginService.loggedInStatus.subscribe((response) => {
      this.loggedIn = response
    },
    (error) => {
      console.log(error);
    })
  }

  allUsers() {
    this.modalService.open(AllUsersComponent, this.ngbBackdrop);
  }

  logOut() {
    sessionStorage.clear();
    this.loginService.setLoggedIn(false);
    this._router.navigate(['/login']);
  }

}
