import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-messages-business-owner',
  templateUrl: './messages-business-owner.component.html',
  styleUrls: ['./messages-business-owner.component.css']
})
export class MessagesBusinessOwnerComponent implements OnInit {

  @Input() userId!: number;

  constructor() { }

  ngOnInit(): void {
  }

}
