import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { authHeader, tweetBaseUrl } from 'src/globals/global-component';

//const headers=new HttpHeaders()
//.set('content-type','application/json').set('Acces-Control-Allow-Origin','*');


@Injectable({
  providedIn: 'root'
})
export class TweetsService {

  constructor(private _http: HttpClient) { }

  public getAllTweets() {
    const urlActual = tweetBaseUrl + '/all' + '/' + this.userName;
    return this._http.get(urlActual , authHeader);
  }

  public postTweet(userTweet: any) {
    const url = tweetBaseUrl + '/' + this.userName + '/add';
    return this._http.post(url, userTweet , authHeader);
  }

  public replyTweet(tweetId: String, reply: String) {
    const url = tweetBaseUrl + '/' + this.userName + '/reply/' + tweetId;
    return this._http.post(url, reply, authHeader);
  }

  public likeTweet(tweetId: String, status: Boolean) {
    const userName = sessionStorage.getItem('userName');
    const url = tweetBaseUrl + '/' + this.userName + '/like/' + tweetId;
    return this._http.put(url, status, authHeader);
  }

  public userTweets() {
    const url = tweetBaseUrl + '/' + this.userName;
    return this._http.get(url, authHeader);
  }

  public getUserTweets() {
    const url = tweetBaseUrl + '/' + this.userName;
    return this._http.get(url, authHeader);
  }

  public getReplies() {
    const url = tweetBaseUrl + '/replies/' + sessionStorage.getItem('tweetId');
    return this._http.get(url, authHeader);
  }

  public updateTweet(tweetId: any, tweet: any) {
    const url = tweetBaseUrl + '/' + this.userName + '/update/' + tweetId;
    return this._http.put(url, tweet, authHeader);
  }
  

  public deleteTweet(tweetId: any) {
    const url = tweetBaseUrl + '/' + this.userName + '/delete' + '/' + tweetId;
    return this._http.delete(url, authHeader);
  }

  get userName() {
    return sessionStorage.getItem('userName');
  }
}
