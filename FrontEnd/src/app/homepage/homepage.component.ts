import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { AnonymousSubject } from 'rxjs/internal/Subject';
import { AllUsersComponent } from '../all-users/all-users.component';
import { LoginService } from '../login.service';
import { TweetsService } from '../tweets.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {

  allTweets: any;
  liked: any;
  reply: FormControl = new FormControl('');
  userTweet: any = new FormControl('');
   
  constructor(private tweetsService: TweetsService, private modalService: NgbModal,private loginService: LoginService, private _router: Router) { }

  ngOnInit(): void {
    this.tweetsService.getAllTweets().subscribe((response: any) => {
      this.allTweets = response.allTweets;
     // this.allTweets.reverse();
      this.allTweets.sort(function(a: any, b: any) {
        let y:any;
        let aDate=a.postedDate.replace(" at ","-").replace(":","-");
        console.log("adate="+aDate);
        let bDate=a.postedDate.replace(" at ","-").replace(":","-");
        console.log("bdate="+bDate);
        return bDate - aDate;
       });
    })
  }

  postTweet() {
    if(this.userTweet.value) {
      const tweets = {
        tweet: this.userTweet.value
      }
      this.tweetsService.postTweet(tweets).subscribe((response: any) => {
        this.ngOnInit();
        this.userTweet.setValue('');
      },
      (error) => {console.log(error)});
    }
  }

  reload(event: any) {
    this.ngOnInit();
  }
}
