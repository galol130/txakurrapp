import { Component, Input, OnInit } from '@angular/core';
import { PetService } from 'src/app/classes/business/pet-service';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';

@Component({
  selector: 'app-service-item',
  templateUrl: './service-item.component.html',
  styleUrls: ['./service-item.component.css']
})
export class ServiceItemComponent implements OnInit {

  @Input() service!: PetService;
  iconPIcture1: string = '../../../assets/images/servicePetPic1.png';
  iconPIcture2: string = '../../../assets/images/servicePetPic2.png';


  constructor(
    private tokenService: TokenStorageService,
    private businessOwnerService: BusinessOwnerService,
  ) { }

  ngOnInit(): void {
  }

  getIcon(): string{
    if(this.service !== undefined && this.service.id % 2 === 0){
      return this.iconPIcture1;
    }else{
      return this.iconPIcture2;
    }
  }

  edit(serviceId: number): void {

  }

  remove(serviceId: number): void {
    let user = this.tokenService.getUser();
    console.log(user.id);
    console.log(serviceId);
    this.businessOwnerService.removeService(user.id, serviceId).subscribe(response => {
      if (response.ok) {
        console.log(response.body);
        // location.reload();
      }
    });
  }

}
