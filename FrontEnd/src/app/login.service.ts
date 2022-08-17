import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { authBaseUrl, authHeader, userBaseUrl } from 'src/globals/global-component';



@Injectable({
  providedIn: 'root'
})
export class LoginService {
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  private loggedIn = new BehaviorSubject<Boolean>(false);
  $loggedIn = this.loggedIn.asObservable();
  public modalOpened = false;

  constructor(private _http: HttpClient) { }

  public login(loginForm: any) {
    const url = authBaseUrl + '/login';
    return this._http.post(url, loginForm);
  }

  public forgotPassword(userName: String, password: String, dob: String) {
    const url = authBaseUrl + '/forgot/'+userName+'/' + dob;
    return this._http.put(url, password);
  }

  public getAllUsers() {
    const url = userBaseUrl + '/' + 'users/all';
    return this._http.get(url, authHeader);
  }

  public getUser(userName: any) {
    const url = userBaseUrl + '/users/' + userName;
    //return this._http.get(url, { headers: this.headers });
    return this._http.get(url, authHeader);
  }

  public register(signupForm: any) {
    const url = userBaseUrl + '/register';
    return this._http.post(url, signupForm, authHeader);
  }

  public users() {
    const url = userBaseUrl + '/users/all';
    return this._http.get(url, authHeader);
  }

  public setLoggedIn(value: any) {
    return this.loggedIn.next(value);
  }

  get loggedInStatus() {
    if (sessionStorage.getItem('token')) {
      this.loggedIn.next(true);
    }
    return this.$loggedIn;
  }
  public getProfileName(){
    return sessionStorage.getItem('userName');
  }
  

}
