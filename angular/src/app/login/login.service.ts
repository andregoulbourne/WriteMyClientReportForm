
import { User } from './../models/user';
import { Injectable } from '@angular/core';
import axios from 'axios';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private user:User | undefined;
  msg:string = '';

  constructor(private router: Router){}

  setUser(user: User) { this.user = user;}

  loginUserFromRemote = (user: User) => {
    axios
      .post(`http://localhost:8081/users/login`, user)
      .then((response) => {
        if(response.data != "") {
          this.setUser(response.data);
          console.log("All green");
          this.router.navigate(['/loginSuccess'])
        } else {
          alert("Login failed ...");
        }
      })
      .catch((error) => {
        console.error(error);
        this.msg= 'Bad Request, please enter a valid emailId and Password';
      });
  };

  
}
