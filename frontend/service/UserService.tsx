import {BaseService} from '@/service/BaseService';


class UserService extends BaseService {

    constructor() {
        super('/user/me');
    }


}

export const userService = new UserService();
