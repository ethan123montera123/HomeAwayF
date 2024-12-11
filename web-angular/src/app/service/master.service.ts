import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../model';
@Injectable({
  providedIn: 'root'
})
export class MasterService {

  apiUrl: string = 'http://localhost:8080/api';

  onLogin$: BehaviorSubject<boolean> = new BehaviorSubject<boolean> (false);
  user$: BehaviorSubject<User> = new BehaviorSubject<User> (null);

  constructor(private http: HttpClient) {
    let user: any = localStorage.getItem("user");
    user = user ? JSON.parse(user) : null;
    if(user && user !== null && user.id) {
      this.onLogin$.next(true);
      this.user$.next(user);
    }
   }

  login(obj: any) {
    return this.http.post(`${this.apiUrl}/auth/login`,obj)
  }

  setLoginUser(login: User) {
    if(login) {
      this.onLogin$.next(true);
      localStorage.setItem("user", JSON.stringify(login));
    } else {
      this.onLogin$.next(false);
      localStorage.setItem("user", null);
    }
  }

  signup(obj: any) {
    return  this.http.post(`${this.apiUrl}/auth/signup`,obj)
  }



}
