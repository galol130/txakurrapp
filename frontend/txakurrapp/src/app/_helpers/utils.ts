import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class Utils {

    calculateAge(inputDate: Date): number {
        const today = new Date();
        const birthDate = new Date(inputDate);
        let age = today.getFullYear() - birthDate.getFullYear();
        const m = today.getMonth() - birthDate.getMonth();

        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        return age;
    }

}


