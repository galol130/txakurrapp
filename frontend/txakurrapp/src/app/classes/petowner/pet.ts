import { Image } from "./image";

export class Pet {

    constructor(
        private _id: number,
        private _type: string,
        private _name: string,
        private _picture: Image,
        private _breed: string,
        private _birthDate: Date,
        private _weight: number,
        private _otherInfo: string
    ){}


    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get type(): string {
        return this._type;
    }
    public set type(value: string) {
        this._type = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get picture(): Image {
        return this._picture;
    }
    public set picture(value: Image) {
        this._picture = value;
    }
    public get breed(): string {
        return this._breed;
    }
    public set breed(value: string) {
        this._breed = value;
    }
    public get birthDate(): Date {
        return this._birthDate;
    }
    public set birthDate(value: Date) {
        this._birthDate = value;
    }
    public get weight(): number {
        return this._weight;
    }
    public set weight(value: number) {
        this._weight = value;
    }
    public get otherInfo(): string {
        return this._otherInfo;
    }
    public set otherInfo(value: string) {
        this._otherInfo = value;
    }


}
