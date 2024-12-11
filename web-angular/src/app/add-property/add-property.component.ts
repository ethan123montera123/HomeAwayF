import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MasterService } from '../service/master.service';

@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrl: './add-property.component.scss'
})
export class AddPropertyComponent {

  form = new FormGroup({
    title: new FormControl(null, [Validators.required]),
    description: new FormControl(null, [Validators.required]),
    address: new FormControl(null, [Validators.required]),
    pricePerNight: new FormControl(null, [Validators.required]),
    type: new FormControl(null, [Validators.required]),
    bedCount: new FormControl(null, [Validators.required]),
  });
  constructor(
    private router: Router,
    private masterService: MasterService,) {
    }

  async onSubmit() {
    if (this.form.invalid) {
      return;
    }
    const data = {
      ...this.form.value,
      ownerId: this.masterService.user$.value.id
    };
    this.masterService.addProperty(data).subscribe((res: any)=> {
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
