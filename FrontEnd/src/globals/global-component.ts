import { HttpHeaders } from "@angular/common/http";

export class GlobalComponent {
}
// With localhost
//  export const baseUrl = `http://localhost:8085/api/v1.0/tweets`;
//  export const userBaseUrl = `http://localhost:8085/api/v1.0/tweets/user`;
//  export const authBaseUrl = `http://localhost:8084/api/v1.0/tweets/auth`;
//  export const tweetBaseUrl = `http://localhost:8086/api/v1.0/tweets/tweet`;

 //With Spring cloud Gateway - 
//  export const userBaseUrl = `http://localhost:8080/api/v1.0/tweets/user`;
//  export const authBaseUrl = `http://localhost:8080/api/v1.0/tweets/auth`;
//  export const tweetBaseUrl = `http://localhost:8080/api/v1.0/tweets/tweet`;

export const baseUrl = `http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets`;
 export const userBaseUrl = `http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets/user`;
 export const authBaseUrl = `http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets/auth`;
 export const tweetBaseUrl = `http://tweetapp-lb-789571150.us-east-1.elb.amazonaws.com/api/v1.0/tweets/tweet`;

 var token = sessionStorage.getItem('token');
    console.log(token);
    var header = {
      headers : new HttpHeaders().set('Authorization', '${token}')
    }
export const authHeader = header;
