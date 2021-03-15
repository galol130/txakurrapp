import { Component, OnInit } from '@angular/core';
import { heartBeatOnEnterAnimation } from "angular-animations";

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css'],
  animations: [heartBeatOnEnterAnimation({ delay: 1500 })],
})
export class NotFoundComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
