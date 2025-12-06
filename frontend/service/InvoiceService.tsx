import  { BaseService } from './BaseService';

export class InvoiceService extends BaseService{

    constructor() {
        super('/extratomensal');
    }
}


export const invoiceService = new InvoiceService();
