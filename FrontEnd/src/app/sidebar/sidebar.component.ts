import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { AllUsersComponent } from '../all-users/all-users.component';
import { LoginService } from '../login.service';
import { TweetsService } from '../tweets.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  ngbBackdrop: NgbModalOptions = {
    backdrop: 'static',
    keyboard: true,
    ariaLabelledBy: 'modal_title',
    size: 'lg'
  }

  constructor(private modalService: NgbModal,private loginService: LoginService,private userService: UserService, private _router: Router, private tweetService: TweetsService) { }

  ngOnInit(): void {
  }

  allUsers() {
    this.modalService.open(AllUsersComponent, this.ngbBackdrop);
  }

  logOut() {
    sessionStorage.clear();
    this.loginService.setLoggedIn(false);
    this._router.navigate(['/login']);
  }

  deleteAccount(){
    if(window.confirm('Click OK if you want to delete this account permanently')){
      this.userService.deleteAccount();
     }
  }

  userDetails(){

  }


}
