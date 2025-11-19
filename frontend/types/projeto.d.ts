
declare namespace Project{
    type User = {
        id?: number;
        name: string;
        email: string;
        login: string;
        password: string;
    };


    type Recurso = {
        id?: number;
        name: string;
        chave: string;
    }


    type Perfil ={
        id?: number;
        perfilUser: string;
    }

    type PerfilUser = {
        id?: number;
        perfil: Perfil;
        user: User;
    }

    type PermissaoPerfilRecurso = {
        id?: number;
        perfil: Perfil;
        recurso: Recurso;
    }
}
