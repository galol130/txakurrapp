export class Image {

    constructor(
        private _id: number,
        private _name: string,
        private _type: string,
        private _picByte: [],
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
    public get type(): string {
        return this._type;
    }
    public set type(value: string) {
        this._type = value;
    }
    public get picByte(): [] {
        return this._picByte;
    }
    public set picByte(value: []) {
        this._picByte = value;
    }

}



// private Long id;
// private String name;
// private String type;
// private byte[] picByte;

// @OneToOne(mappedBy = "profilePicture", orphanRemoval = true)
// @JsonIgnore
// private Owner owner;

// @OneToOne(mappedBy = "picture", orphanRemoval = true)
// @JsonIgnore
// private Pet pet;

