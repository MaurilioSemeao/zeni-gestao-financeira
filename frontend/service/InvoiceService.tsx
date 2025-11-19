import  { BaseService } from './BaseService';

export class InvoiceService extends BaseService{

    constructor() {
        super('/fatura');
    }
}


export const invoiceService = new InvoiceService();
