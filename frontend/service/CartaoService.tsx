import  { BaseService, axiosInstance } from './BaseService';


class CartaoService extends BaseService{

    constructor() {
        super('/cartoes');
    }

    getAllById(id: string){
        return axiosInstance.get(`${this.url}/${id}`)
    }
}

export const cartaoService = new CartaoService();
