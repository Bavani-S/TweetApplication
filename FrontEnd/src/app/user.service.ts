import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SelectMultipleControlValueAccessor } from '@angular/forms';
import { Router } from '@angular/router';
import { authBaseUrl, authHeader, tweetBaseUrl, userBaseUrl } from 'src/globals/global-component';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  constructor(private _http: HttpClient,private _router: Router,private loginService: LoginService) { }

  public getNameOfUser(userName: String) {
    const urlActual = userBaseUrl + '/getUserDetails' + '/' + userName;
    return this._http.get(urlActual , authHeader).subscribe((response : any)=> {
      return response.firstName + " " + response.lastName;
    });
  }

  public getUserDetails() {
    const urlActual = userBaseUrl + '/getUserDetails' + '/' + sessionStorage.getItem('userName');
    return this._http.get(urlActual , authHeader);
  }


  public deleteAccount(){
    const urlActual=userBaseUrl + '/deleteUser/' + sessionStorage.getItem('userName');
    this._http.delete(urlActual,authHeader).subscribe((response:any)=>{
            console.log("response: "+response);
    });
    const authActual = authBaseUrl + '/deleteUser/' + sessionStorage.getItem('userName');
    this._http.delete(authActual, authHeader);
    const tweetActual = tweetBaseUrl + '/deleteUser/' + sessionStorage.getItem('userName');
    this._http.delete(tweetActual, authHeader);
    sessionStorage.clear();
    this.loginService.setLoggedIn(false);
    this._router.navigate(['/']);
  }

 

}
