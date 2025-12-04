import {BaseService, axiosInstance} from './BaseService'


class CategoriaService extends BaseService {
    constructor(){
        super('/categoria')
    }
}


export const categoriaService = new CategoriaService();


