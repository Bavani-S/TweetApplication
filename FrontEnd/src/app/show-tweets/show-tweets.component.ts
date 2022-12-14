import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { NgbModal, NgbModalOptions, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TweetsService } from '../tweets.service';
import { UserService } from '../user.service';
import { UpdateDeleteTweetComponent } from '../update-delete-tweet/update-delete-tweet.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-tweets',
  templateUrl: './show-tweets.component.html',
  styleUrls: ['./show-tweets.component.scss']
})
export class ShowTweetsComponent implements OnInit, OnDestroy {

  modalReference?: NgbModalRef;
  @Input() allTweets: any;
  @Input() title: any;
  @Output() reloadPage: any = new EventEmitter();
  replies : any;
  userName: any;
  modalOptions: NgbModalOptions = {
    backdrop: 'static',
    keyboard: true,
    size: 'lg'
  }

  constructor(private tweetsService: TweetsService, private modalService: NgbModal, private userService : UserService, private _router : Router) { }

  ngOnInit(): void {
    this.userName = sessionStorage.getItem('userName') ? sessionStorage.getItem('userName') : '';
  }

  likeTweet(tweetId: any, status: any) {
    this.tweetsService.likeTweet(tweetId, status).subscribe((response) => {
      this.reloadPage.emit(true);
    },
      (error) => { console.log(error); });
  }

  replyTweet(tweetId: any, reply: any) {
    if (reply !== '') {
      this.tweetsService.replyTweet(tweetId, reply).subscribe((response: any) => {
        this.reloadPage.emit(true);
      },
        (error) => { console.log(error); });
    }
  }

  comment(id: any) {
    setTimeout(() => {
      document.getElementById(id)?.focus();
    }, 1000);
  }

  getReplies(tweetId : any){
    sessionStorage.setItem('tweetId',tweetId);
    this._router.navigate(['/replies']);
  }

  update(tweetId: any) {
    this.modalReference = this.modalService.open(UpdateDeleteTweetComponent, this.modalOptions);
    const changeTweet = this.allTweets.find((tweet: any) => {
      return tweet.id === tweetId;
    }).tweet;
    this.modalReference.componentInstance.tweet = changeTweet;
    this.modalReference.result.then((result) => {
      if (result.action === 'update') {
        this.updateTweet(tweetId, result.value);
      } else if (result.action === 'delete') {
        this.deleteTweet(tweetId);
      }
    },
      (error) => {

      })
  }

  updateTweet(tweetId: any, tweet: any) {
    this.tweetsService.updateTweet(tweetId, tweet).subscribe((response: any) => {
      this.reloadPage.emit(true);
    },
      (error) => {
        console.log(error);
      });

  }

  deleteTweet(tweetId: any) {
    this.tweetsService.deleteTweet(tweetId).subscribe((response: any) => {
      this.reloadPage.emit(true);
    },
      (error) => {
        console.log(error);
      })
  }

  ngOnDestroy() {
    if (this.modalReference) {
      this.modalReference.close();
    }
  }

}
