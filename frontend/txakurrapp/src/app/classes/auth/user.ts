export class User {

    constructor(
        private _id: number,
        private _username: string,
        private _roles: string[],
        private _accessToken: string,
        private _tokenType: string,
    ){}

    public get tokenType(): string {
        return this._tokenType;
    }
    public set tokenType(value: string) {
        this._tokenType = value;
    }
    public get accessToken(): string {
        return this._accessToken;
    }
    public set accessToken(value: string) {
        this._accessToken = value;
    }
    public get roles(): string[] {
        return this._roles;
    }
    public set roles(value: string[]) {
        this._roles = value;
    }
    public get username(): string {
        return this._username;
    }
    public set username(value: string) {
        this._username = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }


}
