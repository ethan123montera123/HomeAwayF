import { Component } from '@angular/core';
import { Properties } from '../model';
import { MasterService } from '../service/master.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  properties: Properties[] = [];
  searchKey= "";

  constructor(
    private masterService: MasterService,) {
  }

  get listing() {
    return this.properties ? this.properties.filter(x=>x.title.toLowerCase().includes(this.searchKey.toLowerCase())) : [];
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getAllProperty();
  }

  async getAllProperty() {
    this.masterService.getAllProperty().subscribe((res: any) => {
      console.log(res);
      if(res.success && res.data) {
        this.properties = res.data;
      }
    }, (error) => {
      console.log(error);
    })
  }

  onSearch(key) {
    this.getAllProperty().then(res=> {
      this.searchKey = key;
    });
  }
}
