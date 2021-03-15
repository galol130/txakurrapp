// This class matches OwnerGetDTO from backend
export class PetOwner {
    
    constructor(
        private _id: number,
        private _signUpDate: Date,
        private _userId: number,
        private _firstName: string,
        private _lastName: string,
        private _personalId: string,
        private _birthDate: Date,
        private _phoneNumber: number,
        private _favs: [],
        private _address: [],
        private _profilePicture: [],
        private _pets: [],
    ){}


    public get pets(): [] {
        return this._pets;
    }
    public set pets(value: []) {
        this._pets = value;
    }
    public get profilePicture(): [] {
        return this._profilePicture;
    }
    public set profilePicture(value: []) {
        this._profilePicture = value;
    }
    public get address(): [] {
        return this._address;
    }
    public set address(value: []) {
        this._address = value;
    }
    public get phoneNumber(): number {
        return this._phoneNumber;
    }
    public set phoneNumber(value: number) {
        this._phoneNumber = value;
    }
    public get birthDate(): Date {
        return this._birthDate;
    }
    public set birthDate(value: Date) {
        this._birthDate = value;
    }
    public get personalId(): string {
        return this._personalId;
    }
    public set personalId(value: string) {
        this._personalId = value;
    }
    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }
    public get firstName(): string {
        return this._firstName;
    }
    public set firstName(value: string) {
        this._firstName = value;
    }
    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }
    public get signUpDate(): Date {
        return this._signUpDate;
    }
    public set signUpDate(value: Date) {
        this._signUpDate = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    } 

    public get favs(): [] {
        return this._favs;
    }
    public set favs(value: []) {
        this._favs = value;
    }

}
