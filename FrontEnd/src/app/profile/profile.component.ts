import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user : any;
  constructor(private userService : UserService) { }

  ngOnInit(): void {
    this.userService.getUserDetails().subscribe((response: any) => {
      if(response.error == null) {
      this.user = response;}
    },
    (error) => {
      console.log(error);
    })
  }

}
