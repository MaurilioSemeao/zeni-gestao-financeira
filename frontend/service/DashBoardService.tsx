import { axiosInstance, BaseService } from './BaseService';

export class DashBoardService extends BaseService{

    constructor() {
        super('/dashboard');
    }

    async getResumoCategoria(periodo: string = "MENSAL"){
        try{
            const response = await axiosInstance.get(`/dashboard/resumoCategoria?periodo=${periodo}`);
            return response.data;
        }
        catch(error){
            console.log("Erro ao Buscar resumo de categoria" + error);
        }
    }
}


export const dashBoradService = new DashBoardService();
