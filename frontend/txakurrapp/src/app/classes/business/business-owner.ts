import { Image } from "./image";
import { Address } from "./address";
import { PetService } from "./pet-service";

export class BusinessOwner {

    constructor(
        private _id: number,
        private _signUpDate: Date,
        private _userId: number,
        private _firstName: string,
        private _lastName: string,
        private _personalId: string,
        private _birthDate: Date,
        private _phoneNumber: number,
        private _address: Address,
        private _profilePicture: Image,
        private _services: PetService[]
    ){}


    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get signUpDate(): Date {
        return this._signUpDate;
    }
    public set signUpDate(value: Date) {
        this._signUpDate = value;
    }
    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }
    public get firstName(): string {
        return this._firstName;
    }
    public set firstName(value: string) {
        this._firstName = value;
    }
    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }
    public get personalId(): string {
        return this._personalId;
    }
    public set personalId(value: string) {
        this._personalId = value;
    }
    public get birthDate(): Date {
        return this._birthDate;
    }
    public set birthDate(value: Date) {
        this._birthDate = value;
    }
    public get phoneNumber(): number {
        return this._phoneNumber;
    }
    public set phoneNumber(value: number) {
        this._phoneNumber = value;
    }
    public get address(): Address {
        return this._address;
    }
    public set address(value: Address) {
        this._address = value;
    }
    public get profilePicture(): Image {
        return this._profilePicture;
    }
    public set profilePicture(value: Image) {
        this._profilePicture = value;
    }
    public get services(): PetService[] {
        return this._services;
    }
    public set services(value: PetService[]) {
        this._services = value;
    }

}


