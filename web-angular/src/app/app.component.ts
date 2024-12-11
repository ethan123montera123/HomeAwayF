import {Component, inject, OnInit} from '@angular/core';
import { MasterService } from './service/master.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  menuOpen = false;
  constructor(
    private router: Router,
    private masterService: MasterService,) {
    }

    get isLogIn() {
      return this.masterService.onLogin$.value;
    }
  ngOnInit(): void {
  }
  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  logout() {
    this.masterService.setLoginUser(null);
    window.location.href = ""
  }

}
