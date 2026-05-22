import axios from 'axios';

export const axiosInstance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_URL_API
});


export class LoginService {

   creatNewUser(user: Zeni.Usuario){
         return axiosInstance.post(`/auth/newuser`, user)
   }

   login(login: String, password: String){
        return axiosInstance.post(`/auth/login`, { login, password })
   }


}

