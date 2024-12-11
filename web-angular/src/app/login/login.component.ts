import { Component } from '@angular/core';
import { MasterService } from '../service/master.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  form = new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required]),
  });
  constructor(
    private router: Router,
    private masterService: MasterService,) {
    }

  async onSubmit() {
    if (this.form.invalid) {
      return;
    }
    this.masterService.login(this.form.value).subscribe((res: any)=> {
      console.log(res);
      if(res.success && res.data) {
        this.masterService.setLoginUser(res.data);
        this.router.navigateByUrl("")
      }
    }, (error)=> {
      console.log(error);
    })
  }
}
