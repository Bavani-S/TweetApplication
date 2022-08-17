import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../login.service';
import { TweetsService } from '../tweets.service';

@Component({
  selector: 'app-replies',
  templateUrl: './replies.component.html',
  styleUrls: ['./replies.component.scss']
})
export class RepliesComponent implements OnInit {
  replies:any;

  constructor(private tweetsService: TweetsService,private modalService: NgbModal,private loginService: LoginService, private _router: Router) { }


  ngOnInit(): void {
    this.tweetsService.getReplies().subscribe((response : any)=>{
          this.replies=response.allTweets;
    },
    (error) => {
      console.log(error);
    });
}

reload(event: any) {
  this.ngOnInit();
}

}
