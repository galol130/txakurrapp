export class Money {

    constructor(
        private _currency: string,
        private _amount: number,
    ){}

    public get currency(): string {
        return this._currency;
    }
    public set currency(value: string) {
        this._currency = value;
    }
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }

}
