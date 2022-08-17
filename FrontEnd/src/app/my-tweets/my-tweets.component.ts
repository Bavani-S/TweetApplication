import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { AllUsersComponent } from '../all-users/all-users.component';
import { LoginService } from '../login.service';
import { TweetsService } from '../tweets.service';

@Component({
  selector: 'app-my-tweets',
  templateUrl: './my-tweets.component.html',
  styleUrls: ['./my-tweets.component.scss']
})
export class MyTweetsComponent implements OnInit {
  tweets: any;
  ngbBackdrop: NgbModalOptions = {
    backdrop: 'static',
    keyboard: true,
    ariaLabelledBy: 'modal_title',
    size: 'lg'
  }

  constructor(private tweetsService: TweetsService,private modalService: NgbModal,private loginService: LoginService, private _router: Router) { }

  ngOnInit(): void {
    this.tweetsService.getUserTweets().subscribe((response: any) => {
      if(response.error == null) {
      this.tweets = response.allTweets;}
    },
    (error) => {
      console.log(error);
    })
  }

  reload(event: any) {
    this.ngOnInit();
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
