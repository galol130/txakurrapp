// Matches MessageGetDTO in Edge Service
export class Message {
    
    constructor(
        private _id: number,
        private _senderId: number,
        private _recipientId: number,
        private _timeStamp: Date,
        private _messageSubject: string,
        private _messageBody: string,
    ){}
    
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get senderId(): number {
        return this._senderId;
    }
    public set senderId(value: number) {
        this._senderId = value;
    }
    public get recipientId(): number {
        return this._recipientId;
    }
    public set recipientId(value: number) {
        this._recipientId = value;
    }
    public get timeStamp(): Date {
        return this._timeStamp;
    }
    public set timeStamp(value: Date) {
        this._timeStamp = value;
    }
    public get messageSubject(): string {
        return this._messageSubject;
    }
    public set messageSubject(value: string) {
        this._messageSubject = value;
    }
    public get messageBody(): string {
        return this._messageBody;
    }
    public set messageBody(value: string) {
        this._messageBody = value;
    }

}
