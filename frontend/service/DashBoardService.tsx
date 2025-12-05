import { axiosInstance, BaseService } from './BaseService';

export class DashBoardService extends BaseService{

    constructor() {
        super('/dashboard');
    }

    async getResumoCategoria(){
        try{
            const response = await axiosInstance.get('/dashboard/resumoCategoria');
            return response.data;
        }
        catch(error){
            console.log("Erro ao Buscar resumo de categoria" + error);
        }
    }
}


export const dashBoradService = new DashBoardService();
