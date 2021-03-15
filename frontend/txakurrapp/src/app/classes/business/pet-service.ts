import { BusinessOwner } from "./business-owner";
import { Money } from "./money";

export class PetService {

    constructor(
        private _id: number,
        private _name: string,
        private _description: string,
        private _price: Money,
        private _available: boolean,
        private _businessOwner: BusinessOwner,
    ){}

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get price(): Money {
        return this._price;
    }
    public set price(value: Money) {
        this._price = value;
    }
    public get available(): boolean {
        return this._available;
    }
    public set available(value: boolean) {
        this._available = value;
    }

    public get businessOwner(): BusinessOwner {
        return this._businessOwner;
    }
    public set businessOwner(value: BusinessOwner) {
        this._businessOwner = value;
    }
    
}


