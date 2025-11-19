import axios from 'axios';

export const axiosInstance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_URL_API
});


axiosInstance.interceptors.request.use((config) => {
        const token = localStorage.getItem('TOKEN_APPLICATION');
        const authRequestToken = token ? `Bearer ${token}` : '';
        config.headers['Authorization'] = authRequestToken;
        return config;
    },
    (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use((response) => {
        return response;
    },
    async(error)=>{
        const originalRequest = error.config;
        console.log(error.response.status);
        if(error.response.status === 401 && !originalRequest._retry){
            originalRequest._retry = true;
            localStorage.removeItem('TOKEN_APPLICATION');
            localStorage.removeItem('USER_DATA');
            window.location.reload();
        }
        return Promise.reject(error);
    }

    );



export class BaseService {

    url: string;

    constructor(url: string){
        this.url = url;

    }

    async getAll(){
        try {
            const response = await axiosInstance.get(`${this.url}`);
            return response.data;
        } catch (error) {
            console.error(`Erro aou buscar a instancia na rota  ${this.url} :  ${error}`);

            throw error;
        }


    }

    create(data: any){
        const {id, ...payload} = data;
        return axiosInstance.post(`${this.url}`, payload)
    }

    update(data:any){
        const {id, ...payload} = data;
        return axiosInstance.put(`${this.url}/${id}`, payload)
    }



    delete(id: number){
        return axiosInstance.delete(`${this.url}/${id}`)
    }

    getById(id: number){
        return axiosInstance.get(`${this.url}/${id}`)
    }
}
