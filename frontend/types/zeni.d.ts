
export declare namespace Zeni {
    type User = {
        id: number;
        username: string;
        email: string;
        password: string;
    };

    type Transaction = {
        id: number;
        description: string;
        price: number;
        data: string;
        cardId: number;
    };

    type Invoice = {
        id: number;
        valorTotal: number;
        status: string;
        mesReferencia: string;
    };

   type  Card ={
        id: number;
        surname: string;
        finalNumber: string;
        expenses: number;
        quantityOfPurchases: number;
        limitValue: number;
    }





}
