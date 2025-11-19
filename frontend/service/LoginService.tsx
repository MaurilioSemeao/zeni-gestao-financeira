import axios from 'axios';
import { Zeni } from '@/types/zeni';

export const axiosInstance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_URL_API
});


export class LoginService {

   creatNewUser(user: Zeni.User){
         return axiosInstance.post(`/auth/newuser`, user)
   }

   login(login: String, password: String){
        return axiosInstance.post(`/auth/login`, { login, password })
   }


}
