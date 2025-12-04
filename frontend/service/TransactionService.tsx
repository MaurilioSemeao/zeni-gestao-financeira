import  { BaseService, axiosInstance } from './BaseService';

class TransactionService extends BaseService{

    constructor() {
        super('/transacoes');
    }

    async getAllById(id: string){
        try{
            const response = await axiosInstance.get(`${this.url}/${id}`)
            return response.data;
        }
        catch(error){
            console.log(`Erro ao buscar instancia na rota ${this.url} :  ${ error }`);
        }


    }
}

export const transactionService  = new TransactionService();
