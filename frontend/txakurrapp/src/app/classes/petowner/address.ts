export class Address {

    constructor(
        private _id: number,
        private _streetName: string,
        private _number: number,
        private _apt: string,
        private _otherInfo: string,
        private _postalCode: string,
        private _city: string,
        private _province: string,
        private _country: string,
        private _owner: Object
    ){}


    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get streetName(): string {
        return this._streetName;
    }
    public set streetName(value: string) {
        this._streetName = value;
    }
    public get number(): number {
        return this._number;
    }
    public set number(value: number) {
        this._number = value;
    }
    public get apt(): string {
        return this._apt;
    }
    public set apt(value: string) {
        this._apt = value;
    }
    public get otherInfo(): string {
        return this._otherInfo;
    }
    public set otherInfo(value: string) {
        this._otherInfo = value;
    }
    public get postalCode(): string {
        return this._postalCode;
    }
    public set postalCode(value: string) {
        this._postalCode = value;
    }
    public get city(): string {
        return this._city;
    }
    public set city(value: string) {
        this._city = value;
    }
    public get province(): string {
        return this._province;
    }
    public set province(value: string) {
        this._province = value;
    }
    public get country(): string {
        return this._country;
    }
    public set country(value: string) {
        this._country = value;
    }
    public get owner(): Object {
        return this._owner;
    }
    public set owner(value: Object) {
        this._owner = value;
    }

}


