import { Component, Input, OnInit } from '@angular/core';
import { timestamp } from 'rxjs/operators';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { Message } from 'src/app/classes/petowner/message';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';
import {
  slideInDownOnEnterAnimation,
  fadeInUpBigOnEnterAnimation,
} from "angular-animations";

@Component({
  selector: 'app-messages-pet-owner',
  templateUrl: './messages-pet-owner.component.html',
  styleUrls: ['./messages-pet-owner.component.css'],
  animations: [    
    slideInDownOnEnterAnimation(),
    fadeInUpBigOnEnterAnimation({delay: 500}),
    // fadeInOnEnterAnimation(),
    // rubberBandOnEnterAnimation({delay: 1500}),
  ],
})
export class MessagesPetOwnerComponent implements OnInit {

  messages: Message[] = [];
  conversations: Conversation[] = [];

  constructor(
    private businessOwnerService: BusinessOwnerService,
    private petOwnerService: PetOwnerService,
    private tokenStorageService: TokenStorageService,
  ) { }

  ngOnInit(): void {
    this.loadMessages();
  }

  ngOnChange(): void {
    this.loadMessages();
  }

  loadMessages(): void {
    let user = this.tokenStorageService.getUser();
    this.petOwnerService.getAllMessagesFromSender(user.id).subscribe(response => {
      if (response.ok) {
        console.log(response);
        this.messages = [];
        this.messages = response.body as Message[];

        if (response.body != null) {
          this.messagesIntoConversations(response.body);
        }
      }
      console.log(this.messages);
    });
  }

  messagesIntoConversations(messages: Message[]): void {
    this.conversations = [];
    let recipientSet = new Set(messages.map(item => item.recipientId));

    recipientSet.forEach(item => {
      let conMessages = messages.filter(msg => msg.recipientId === item);

      this.businessOwnerService.getBusinessOwnerById(item).subscribe(response => {
        if (response.ok && response.body != null) {
          this.conversations.push({
            recipient: response.body,
            conversation: conMessages
          });
        }
      });

    });

    console.log(this.conversations);
  }

  getDateFormatted(dateTime: Date): string {
    let timeStamp = new Date(dateTime.toString());
    let year = timeStamp.getFullYear();
    let month = timeStamp.getMonth();
    let day = timeStamp.getDate();
    let hour = timeStamp.getHours();
    let minutes = timeStamp.getMinutes();

    return `${day}/${month}/${year} - ${hour}:${minutes}`;
  }


}

interface Conversation {
  recipient: BusinessOwner;
  conversation: Message[];
}
